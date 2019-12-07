package com.konrad.garagev3.service;

import com.konrad.garagev3.mapper.PartMapper;
import com.konrad.garagev3.model.dao.Part;
import com.konrad.garagev3.model.dto.PartDto;
import com.konrad.garagev3.repository.PartRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PartService {

    private final PartRepository partRepository;
    private final PartMapper partMapper;

    public PartService(PartRepository partRepository) {
        this.partRepository = partRepository;
        this.partMapper = Mappers.getMapper(PartMapper.class);
    }

    public Page<Part> getPage(PageRequest pageRequest) {
        return partRepository.findAll(pageRequest);
    }

    public PartDto savePart(Part part) {
        return partMapper.toPartDto(partRepository.save(part));
    }

    public void deletePart(Long id) {
        partRepository.deleteById(id);
    }

    public Part findById(Long id) {
        return partRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Part with id" + id + " doesn't exist"));
    }
    public List<PartDto> autocompleteParts(String searchText) {
       return partRepository.findByNameContaining(searchText)
               .stream()
               .map(partMapper::toPartDto)
               .collect(Collectors.toList());

    }
}
