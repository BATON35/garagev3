package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.WorkerMapper;
import com.konrad.garagev3.model.dao.Worker;
import com.konrad.garagev3.model.dao.WorkerStatisticSell;
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
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        worker.setActive(1);
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

    public List<WorkerStatisticSell> getStatistic() {
        List<WorkerStatisticSell> workerStatisticSells = workerRepository.getStatisticByWorker()
                .stream()
                .map(r -> WorkerStatisticSell.builder()
                        .date(r.getDate())
                        .price(r.getPrice())
                        .name(r.getName())
                        .build())
                .collect(Collectors.toList());
        Map<String, List<WorkerStatisticSell>> statisticGrouped = workerStatisticSells
                .stream()
                .collect(Collectors.groupingBy(workerStatisticSell -> workerStatisticSell.getName()));

        Map<String, List<WorkerStatisticSell>> maps = new HashMap<>();
        statisticGrouped.forEach((key, value) -> {
            Map<String, Boolean> workerMounth = new HashMap<>();
            workerMounth.put("01", false);
            workerMounth.put("02", false);
            workerMounth.put("03", false);
            workerMounth.put("04", false);
            workerMounth.put("05", false);
            workerMounth.put("06", false);
            workerMounth.put("07", false);
            workerMounth.put("08", false);
            workerMounth.put("09", false);
            workerMounth.put("10", false);
            workerMounth.put("11", false);
            workerMounth.put("12", false);
            value.forEach(element -> {
                if (workerMounth.containsKey(element.getDate().split("-")[1])) {
                    workerMounth.put(element.getDate().split("-")[1], true);
                }
            });
            workerMounth.entrySet().forEach(entry -> {
                if (!entry.getValue()) {
                    value.add(WorkerStatisticSell
                            .builder()
                            .price(new BigDecimal(0))
                            .date(LocalDate.now().getYear() + "-" + entry.getKey())
                            .name(key)
                            .build());
                }
            });
        });
//        return statisticGrouped.entrySet()
//                .stream()
//                .flatMap(element -> element.getValue().sort(Comparator.comparing(stats -> stats.getName())
//                        .thenComparing(data -> data.))
//                        .stream()).collect(Collectors.toList());
        return statisticGrouped.entrySet().stream().flatMap(element->element.getValue().stream()).collect(Collectors.toList());
    }
}
