package com.konrad.garagev3.service;

import com.konrad.garagev3.model.Owner;
import com.konrad.garagev3.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {
    private final OwnerRepository ownerRepository;

    @Autowired
    OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner findOwnerByEmail(String email) {
        return ownerRepository.findOwnerByEmail(email);
    }
}
