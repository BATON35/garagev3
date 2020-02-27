package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.WorkerMapper;
import com.konrad.garagev3.model.dao.Worker;
import com.konrad.garagev3.model.dao.WorkerStatisticSell;
import com.konrad.garagev3.model.dto.StatisticDto;
import com.konrad.garagev3.model.dto.WorkerDto;
import com.konrad.garagev3.repository.WorkerRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class WorkerService {

    private final WorkerRepository workerRepository;
    private final WorkerMapper workerMapper;

    @Autowired
    public WorkerService(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
        workerMapper = Mappers.getMapper(WorkerMapper.class);
    }

    public Worker saveWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    public void deleteWorker(Long id) {
        workerRepository.deleteById(id);
    }

    public Page<WorkerDto> searchWorkers(String searchText, PageRequest pageRequest) {
        Page<Worker> workers = workerRepository.findByNameContainsOrEmailContains(searchText, searchText, pageRequest);
        return new PageImpl<>(workers.getContent()
                .stream()
                .map(workerMapper::toWorkerDto)
                .collect(Collectors.toList()), workers.getPageable(), workers.getTotalElements());
    }

    public Worker findById(Long id) {
        return workerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Worker with id " + id + " doesn't exist"));
    }

    public Page<Worker> findAll(@PageableDefault Pageable pageable, Boolean hasRole) {
        Page<Worker> workers;
        if (hasRole) {
            workers = workerRepository.findByRoleIsNull(pageable);
        } else {
            workers = workerRepository.findAll(pageable);
        }
        Page<Worker> pageWorkers = new PageImpl<>(workers.getContent(), workers.getPageable(), workers.getTotalElements());
        return pageWorkers;
    }

    public List<WorkerDto> autocompleteWorker(String searchText) {
        return workerRepository.findByNameContainingOrSurnameContaining(searchText, searchText)
                .stream()
                .map(workerMapper::toWorkerDto)
                .collect(Collectors.toList());

    }

    public List<WorkerStatisticSell> getStatistic(StatisticDto statisticDto) {
        List<WorkerStatisticSell> workerStatisticSells = workerRepository.getStatisticByWorker(statisticDto.getStart(), statisticDto.getEnd())
                .stream()
                .map(r -> WorkerStatisticSell.builder()
                        .date(r.getDate())
                        .price(r.getPrice())
                        .name(r.getName())
                        .build())
                .collect(Collectors.toList());

        Map<String, List<WorkerStatisticSell>> statisticGrouped = workerStatisticSells
                .stream()
                .collect(Collectors.groupingBy(WorkerStatisticSell::getName));

        statisticGrouped.forEach((key, value) -> {
            Period intervalPeriod = Period.between(statisticDto.getStart(), statisticDto.getEnd());
            int periodOfTimeInMonths = intervalPeriod.getMonths() + intervalPeriod.getYears() * 12 + (int) Math.ceil(intervalPeriod.getDays() / 31.0);
            Map<String, Boolean> workerMonth = new HashMap<>();
            for (int i = 0; i < periodOfTimeInMonths; i++) {
                workerMonth.put(statisticDto.getStart().plusMonths(i).toString().substring(0, 7), false);
            }
            value.forEach(statisticSell -> {
                if (workerMonth.containsKey(statisticSell.getDate())) {
                    workerMonth.put(statisticSell.getDate(), true);
                }
            });
            workerMonth.entrySet().forEach(entry -> {
                if (!entry.getValue()) {
                    value.add(WorkerStatisticSell
                            .builder()
                            .price(new BigDecimal(0))
                            .date(entry.getKey())
                            .name(key)
                            .build());
                }
            });
        });
        return statisticGrouped.entrySet()
                .stream()
                .flatMap(element -> element.getValue()
                        .stream().sorted(Comparator.comparing(WorkerStatisticSell::getName)
                                .thenComparing(WorkerStatisticSell::getDate)))
                .collect(Collectors.toList());
    }
}
