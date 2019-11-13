package com.konrad.garagev3.service;

import com.konrad.garagev3.model.dao.Part;
import com.konrad.garagev3.model.dao.ServicePart;
import com.konrad.garagev3.repository.PartRepository;
import com.konrad.garagev3.repository.ServicePartRepository;
import com.konrad.garagev3.repository.ServiceRepository;
import com.konrad.garagev3.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ServicePartService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServicePartRepository servicePartRepository;

    public ServicePart saveServicePart(Long workerId, List<Long> partIds, Long serviceID) {
        return workerRepository.findById(workerId)
                .map(worker -> serviceRepository.findById(serviceID)
                        .map(serviceCar -> {
                            List<Part> parts = partRepository.findByIdIn(partIds);
                            return servicePartRepository.save(ServicePart
                                    .builder()
                                    .worker(worker)
                                    .parts(parts)
                                    .build());
                        }).orElseThrow(() -> new EntityNotFoundException("serviceCar id " + serviceID + " doesn't exist")))
                .orElseThrow(() -> new EntityNotFoundException("worker id " + workerId + " doesn't exist"));
    }
}
