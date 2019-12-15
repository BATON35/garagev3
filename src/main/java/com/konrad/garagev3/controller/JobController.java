package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.JobMapper;
import com.konrad.garagev3.model.dto.JobDto;
import com.konrad.garagev3.model.dto.ServicePartResponseDto;
import com.konrad.garagev3.service.JobService;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-part")
public class JobController {

    private final JobService jobService;
    private final JobMapper jobMapper;

    public JobController(JobService jobService) {
        this.jobService = jobService;
        this.jobMapper = Mappers.getMapper(JobMapper.class);

    }

    @GetMapping("/{id}")
    public JobDto getServicePartById(@PathVariable Long id) {
        return jobMapper.toJobDto(jobService.findById(id));
    }

    @GetMapping
    public Page<JobDto> getServicePartList(@RequestParam Integer page, @RequestParam Integer size) {
        return jobService.findAll(PageRequest.of(page, size)).map(jobMapper::toJobDto);
    }

    @PostMapping
    public JobDto saveServicePart(@RequestBody JobDto jobDto) {
        return jobMapper.toJobDto(
                jobService.saveServicePart(
                        jobDto.getWorkerId(),
                        jobDto.getPartIds(),
                        jobDto.getServiceId(),
                        jobDto.getVehicleNumberPlate()));
    }

    @PutMapping
    public JobDto updateServicePart(@RequestBody JobDto jobDto) {
        return jobMapper.toJobDto(
                jobService.saveServicePart(
                        jobDto.getWorkerId(),
                        jobDto.getPartIds(),
                        jobDto.getServiceId(),
                        jobDto.getVehicleNumberPlate()));
    }

    @DeleteMapping("{id}")
    public void deleteServicePart(@PathVariable Long id) {
        jobService.deleteServicePart(id);
    }

    @GetMapping("/history")
    public List<ServicePartResponseDto> getServicePartHistory(Long vehicleId) {
        return jobService.getHistory(vehicleId);
    }

}
