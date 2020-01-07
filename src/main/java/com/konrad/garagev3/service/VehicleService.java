package com.konrad.garagev3.service;

import com.konrad.garagev3.exeption.DuplicateEntryException;
import com.konrad.garagev3.mapper.VehicleMapper;
import com.konrad.garagev3.model.dao.Client;
import com.konrad.garagev3.model.dao.Photo;
import com.konrad.garagev3.model.dao.Vehicle;
import com.konrad.garagev3.model.dto.VehicleDto;
import com.konrad.garagev3.repository.ClientRepository;
import com.konrad.garagev3.repository.PhotoRepository;
import com.konrad.garagev3.repository.VehicleRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final ClientRepository clientRepository;
    private final VehicleMapper vehicleMapper;
    private final PhotoRepository photoRepository;

    @Autowired
    VehicleService(VehicleRepository vehicleRepository, ClientRepository clientRepository, PhotoRepository photoRepository) {
        this.vehicleRepository = vehicleRepository;
        this.clientRepository = clientRepository;
        this.vehicleMapper = Mappers.getMapper(VehicleMapper.class);
        this.photoRepository = photoRepository;
    }

    public VehicleDto findVehicleByNumberPlate(String numberPlate) {
        return vehicleMapper.toVehicleDto(vehicleRepository.findByNumberPlate(numberPlate).orElseThrow(()-> new EntityNotFoundException("Vehicle with number plate " + numberPlate  +" doesn't exist")));
    }

    public Vehicle saveVehicle(Vehicle vehicle, Long clientId) throws DuplicateEntryException {
        //todo doczytac SecurityContextHolder
        // String clientName = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Vehicle> present = vehicleRepository.findByNumberPlate(vehicle.getNumberPlate());
        if(present.isPresent() && !present.get().getId().equals(vehicle.getId())){
            throw new DuplicateEntryException("vehicle with number plate " + vehicle.getNumberPlate() + " exist in database");
        }
        return clientRepository.findById(clientId).map(client -> {
            vehicle.setClient(client);
            return vehicleRepository.save(vehicle);
        }).orElseThrow(() -> new EntityNotFoundException("client with id " + clientId + " doesn't exist"));

    }

    public Vehicle update(Vehicle vehicle) throws DuplicateEntryException {
        Optional<Vehicle> vehicleFromDatabase = vehicleRepository.findByNumberPlate(vehicle.getNumberPlate());
        if(vehicleFromDatabase.isPresent() && !vehicleFromDatabase.get().getId().equals(vehicle.getId())){
            throw new DuplicateEntryException("vehicle.duplicate.number");
        }
        Client client = clientRepository.findByVehicles(vehicle);
        vehicle.setClient(client);
        return vehicleRepository.save(vehicle);
    }

    public List<VehicleDto> findVehicleByClientMail(String email) {
        Client client = clientRepository.findByEmail(email);
        List<Vehicle> vehicles = vehicleRepository.findByClientId(client.getId());
        ArrayList vehiclesDto = new ArrayList();
        for (Vehicle vehicle : vehicles) {
            vehiclesDto.add(vehicleMapper.toVehicleDto(vehicle));
        }
        return vehicles
                .stream()
                .map(vehicleMapper::toVehicleDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteByClientId(long id) {
        vehicleRepository.deleteByClientId(id);
    }

    public Vehicle findById(Long id) {
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User with id" + id + " doesn't exist"));
    }

    public Page<Vehicle> findAll(@PageableDefault Pageable pageable) {
        Page<Vehicle> vehicles = vehicleRepository.findAll(pageable);
        Page<Vehicle> pageVehicles = new PageImpl<>(vehicles.getContent(), vehicles.getPageable(), vehicles.getContent().size());
        return pageVehicles;
    }


    public VehicleDto SaveVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleRepository.save(vehicleMapper.toVehicle(vehicleDto));
        return vehicleMapper.toVehicleDto(vehicle);
    }

    public void deleteVehicle(Long id) {
        vehicleRepository.deleteById(id);
    }

    public void toggleNotification(Long vehicleId) {
        vehicleRepository.findById(vehicleId).ifPresent(vehicle -> {
            vehicle.setNotification(!vehicle.isNotification());
            vehicleRepository.save(vehicle);
        });
    }

    public List<VehicleDto> autocompleteWorker(String text) {
        return vehicleRepository.findByNumberPlateContaining(text)
                .stream()
                .map(vehicleMapper::toVehicleDto)
                .collect(Collectors.toList());
    }

    public List<byte[]> getPhotosPaths(Long id) {
        List<Photo> photos = photoRepository.findByVehicleId(id);
        List<byte[]> photosInBytes = new ArrayList<>();
            photos.forEach(photo -> {
                try {
                    photosInBytes.add(Files.readAllBytes(Paths.get(photo.getPath())));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        return photosInBytes;
    }
}
