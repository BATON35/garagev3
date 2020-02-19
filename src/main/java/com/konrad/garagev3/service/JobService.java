package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.JobResponseMapper;
import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dao.Part;
import com.konrad.garagev3.model.dto.JobResponseDto;
import com.konrad.garagev3.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private WorkerRepository workerRepository;
    private PartRepository partRepository;
    private CarServiceRepository carServiceRepository;
    private JobRepository jobRepository;
    private VehicleRepository vehicleRepository;
    private final JobResponseMapper jobResponseMapper;

    @Autowired
    public JobService(WorkerRepository workerRepository,
                      PartRepository partRepository,
                      CarServiceRepository carServiceRepository,
                      JobRepository jobRepository,
                      VehicleRepository vehicleRepository,
                      JobResponseMapper jobResponseMapper
    ) {
        this.workerRepository = workerRepository;
        this.partRepository = partRepository;
        this.carServiceRepository = carServiceRepository;
        this.jobRepository = jobRepository;
        this.vehicleRepository = vehicleRepository;
        this.jobResponseMapper = jobResponseMapper;
    }

    public Job saveJob(Long workerId, List<Long> partIds, Long serviceID, String numberPlate) {
//        Map<Long, Long> collect = partIds.stream().collect(Collectors.groupingBy(partId -> partId, Collectors.counting()));
//        List<Part> parts = new LinkedList<>();
//        collect.entrySet().stream().forEach(e -> {
//            for (int i = 0; i < e.getValue(); i++) {
//                parts.add(partRepository.findById(e.getKey()).orElseThrow(() -> new EntityNotFoundException("dupa blada")));
//            }
//        });
        List<Part> byIdIn = partRepository.findByIdIn(partIds);
        List<Part> partsList = partIds.stream()
                .map(id -> byIdIn.stream()
                        .filter(part -> part.getId().equals(id))
                        .collect(Collectors.toList()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        return jobRepository.save(Job
                .builder()
                .worker(workerRepository.findById(workerId)
                        .orElseThrow(() -> new EntityNotFoundException("worker id " + workerId + " doesn't exist")))
                .carService(carServiceRepository.findById(serviceID).orElseThrow(() -> new EntityNotFoundException("carService id " + serviceID + " doesn't exist")))
                .vehicle(vehicleRepository.findByNumberPlate(numberPlate).orElseThrow(() -> new EntityNotFoundException("Vehicle with number plate " + numberPlate + " doesn't exist"))) //can return optional??
                //todo partRepository.findByIdIn(). do not return duplicate
//                .parts(partRepository.findByIdIn(partIds))
//                .parts(partRepository.findByIdIn(partIds))
                .parts(partsList)
                .build());
    }

    public Job findById(Long id) {
        return jobRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("job id " + id + " doesn't exist"));
    }

    public Page<Job> findAll(@PageableDefault Pageable pageable) {
        Page<Job> serviceParts = jobRepository.findAll(pageable);
        Page<Job> pageServiceParts = new PageImpl<>(serviceParts.getContent(), serviceParts.getPageable(), serviceParts.getContent().size());
        return pageServiceParts;
    }

    public void deleteServicePart(Long id) {
        jobRepository.deleteById(id);
    }

    public List<JobResponseDto> getHistory(Long vehicleId) {
        return jobRepository.findByVehicleId(vehicleId)
                .stream()
                .map(jobResponseMapper::toServicePartResponse)
                .collect(Collectors.toList());
    }

}
