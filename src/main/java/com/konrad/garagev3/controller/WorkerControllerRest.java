package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.WorkerMapper;
import com.konrad.garagev3.model.dao.Worker;
import com.konrad.garagev3.model.response.WorkerStatisticSell;
import com.konrad.garagev3.model.request.StatisticScope;
import com.konrad.garagev3.model.dto.WorkerDto;
import com.konrad.garagev3.service.WorkerService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workers")
public class WorkerControllerRest {

    private final WorkerService workerService;
    private final WorkerMapper workerMapper;


    @Autowired
    public WorkerControllerRest(WorkerService workerService) {
        this.workerService = workerService;
        workerMapper = Mappers.getMapper(WorkerMapper.class);
    }

    @GetMapping("/search")
    public Page<WorkerDto> searchWorker(@RequestParam String searchText, @RequestParam Integer page, @RequestParam Integer size) {
        return workerService.searchWorkers(searchText, PageRequest.of(page, size));
    }

    @GetMapping("{id}")
    public WorkerDto getWorkerById(@PathVariable Long id) {
        return workerMapper.toWorkerDto(workerService.findById(id));
    }

    @GetMapping("/autoComplete")
    public List<WorkerDto> autocompleteWorker(@RequestParam String text) {
        return workerService.autocompleteWorker(text);
    }

    @GetMapping("/{page}/{size}/{hasRole}")
    public Page<WorkerDto> getWorkerList(@PathVariable Integer page, @PathVariable Integer size, @PathVariable Boolean hasRole) {
        return workerService.findAll(PageRequest.of(page, size), hasRole).map(workerMapper::toWorkerDto);
    }

    //modify do get method??
    @PostMapping("/statistic")
    public List<WorkerStatisticSell> getStatistic(@RequestBody StatisticScope statisticScope) {
        return workerService.getStatistic(statisticScope);
    }

    @PostMapping
    public WorkerDto saveWorker(@RequestBody WorkerDto workerDto) {
        Worker worker = workerMapper.toWorker(workerDto);
        return workerMapper.toWorkerDto(workerService.saveWorker(worker));
    }

    @PutMapping
    public Worker updateWorker(@RequestBody WorkerDto workerDto) {
        return workerService.saveWorker(workerMapper.toWorker(workerDto));
    }

    @DeleteMapping("{id}")
    public void deleteWorker(@PathVariable Long id) {
        workerService.deleteWorker(id);
    }

}
