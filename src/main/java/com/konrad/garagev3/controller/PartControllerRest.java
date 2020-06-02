package com.konrad.garagev3.controller;

import com.konrad.garagev3.mapper.PartMapper;
import com.konrad.garagev3.model.dao.Part;
import com.konrad.garagev3.model.dto.PartDto;
import com.konrad.garagev3.service.PartService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parts")
public class PartControllerRest {
    private final PartService partService;
    private final PartMapper partMapper;

    @Autowired
    public PartControllerRest(PartService partService) {
        this.partService = partService;
        partMapper = Mappers.getMapper(PartMapper.class);
    }

    @GetMapping
    public Page<PartDto> getPartPage(@RequestParam Integer page, @RequestParam Integer size) {
        return partService.getPage(PageRequest.of(page, size)).map(partMapper::toPartDto);
    }

    @GetMapping("/auto-complete")
    public List<PartDto> autocompletePart(@RequestParam String text) {
        return partService.autocompleteParts(text);
    }

    @GetMapping("/{id}")
    public PartDto getPartById(@RequestParam Long id) {
        return partMapper.toPartDto(partService.findById(id));
    }
    @PostMapping
    public PartDto savePart(@RequestBody PartDto partDto) {
        return partService.savePart(partMapper.toPart(partDto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public PartDto updatePart(@RequestBody PartDto partDto) {
        return partService.savePart(partMapper.toPart(partDto));
    }

    @DeleteMapping("/{id}")
    public void deletePart(@PathVariable Long id) {
        partService.deletePart(id);
    }


}
