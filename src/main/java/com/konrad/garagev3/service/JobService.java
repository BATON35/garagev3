package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.JobMapper;
import com.konrad.garagev3.mapper.JobResponseMapper;
import com.konrad.garagev3.model.dao.Job;
import com.konrad.garagev3.model.dto.JobResponseDto;
import com.konrad.garagev3.repository.*;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    private WorkerRepository workerRepository;
    private PartRepository partRepository;
    private CarServiceRepository carServiceRepository;
    private JobRepository jobRepository;
    private VehicleRepository vehicleRepository;
//    private final JobMapper jobMapper;
    private final JobResponseMapper jobResponseMapper;

    @Autowired
    public JobService(WorkerRepository workerRepository,
                      PartRepository partRepository,
                      CarServiceRepository carServiceRepository,
                      JobRepository jobRepository,
                      VehicleRepository vehicleRepository,
                      JobResponseMapper jobResponseMapper,
                      JobMapper jobMapper
                              ) {
        this.workerRepository = workerRepository;
        this.partRepository = partRepository;
        this.carServiceRepository = carServiceRepository;
        this.jobRepository = jobRepository;
        this.vehicleRepository = vehicleRepository;
//        this.jobMapper = jobMapper;
        this.jobResponseMapper = jobResponseMapper;
    }

    public Job saveJob(Long workerId, List<Long> partIds, Long serviceID, String numberPlate) {
        return jobRepository.save(Job
                .builder()
                .worker(workerRepository.findById(workerId)
                        .orElseThrow(() -> new EntityNotFoundException("worker id " + workerId + " doesn't exist")))
                .carService(carServiceRepository.findById(serviceID).orElseThrow(() -> new EntityNotFoundException("carService id " + serviceID + " doesn't exist")))
                .vehicle(vehicleRepository.findByNumberPlate(numberPlate).orElseThrow(()->new EntityNotFoundException("Vehicle with number plate " + numberPlate  +" doesn't exist"))) //can return optional??
                .parts(partRepository.findByIdIn(partIds))
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
