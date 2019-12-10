package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.ServicePartMapper;
import com.konrad.garagev3.mapper.ServicePartResponseMapper;
import com.konrad.garagev3.model.dao.ServicePart;
import com.konrad.garagev3.model.dto.ServicePartResponseDto;
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
public class ServicePartService {

    private WorkerRepository workerRepository;
    private PartRepository partRepository;
    private CarServiceRepository carServiceRepository;
    private ServicePartRepository servicePartRepository;
    private VehicleRepository vehicleRepository;
    private final ServicePartMapper servicePartMapper;
    private final ServicePartResponseMapper servicePartResponseMapper;

    @Autowired
    public ServicePartService(WorkerRepository workerRepository,
                              PartRepository partRepository,
                              CarServiceRepository carServiceRepository,
                              ServicePartRepository servicePartRepository,
                              VehicleRepository vehicleRepository,
                              ServicePartResponseMapper servicePartResponseMapper
                              ) {
        this.workerRepository = workerRepository;
        this.partRepository = partRepository;
        this.carServiceRepository = carServiceRepository;
        this.servicePartRepository = servicePartRepository;
        this.vehicleRepository = vehicleRepository;
        this.servicePartMapper = Mappers.getMapper(ServicePartMapper.class);
        this.servicePartResponseMapper = servicePartResponseMapper;
    }

    public ServicePart saveServicePart(Long workerId, List<Long> partIds, Long serviceID, String numberPlate) {
//        return workerRepository.findById(workerId)
//                .map(worker -> serviceRepository.findById(serviceID)
//                        .map(serviceCar -> {
//                            List<Part> parts = partRepository.findByIdIn(partIds);
//                            return servicePartRepository.save(ServicePart
//                                    .builder()
//                                    .worker(worker)
//                                    .parts(parts)
//                                    .build());
//                        }).orElseThrow(() -> new EntityNotFoundException("serviceCar id " + serviceID + " doesn't exist")))
//                .orElseThrow(() -> new EntityNotFoundException("worker id " + workerId + " doesn't exist"));
        return servicePartRepository.save(ServicePart
                .builder()
                .worker(workerRepository.findById(workerId)
                        .orElseThrow(() -> new EntityNotFoundException("worker id " + workerId + " doesn't exist")))
                .carService(carServiceRepository.findById(serviceID).orElseThrow(() -> new EntityNotFoundException("carService id " + serviceID + " doesn't exist")))
                .vehicle(vehicleRepository.findByNumberPlate(numberPlate)) //can return optional??
                .parts(partRepository.findByIdIn(partIds))
                .build());
    }

    public ServicePart findById(Long id) {
        return servicePartRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("servicePart id " + id + " doesn't exist"));
    }

    public Page<ServicePart> findAll(@PageableDefault Pageable pageable) {
        Page<ServicePart> serviceParts = servicePartRepository.findAll(pageable);
        Page<ServicePart> pageServiceParts = new PageImpl<>(serviceParts.getContent(), serviceParts.getPageable(), serviceParts.getContent().size());
        return pageServiceParts;
    }

    public void deleteServicePart(Long id) {
        servicePartRepository.deleteById(id);
    }

    public List<ServicePartResponseDto> getHistory(Long vehicleId) {
        return servicePartRepository.findByVehicleId(vehicleId)
                .stream()
                .map(servicePartResponseMapper::toServicePartResponse)
                .collect(Collectors.toList());
    }

}
