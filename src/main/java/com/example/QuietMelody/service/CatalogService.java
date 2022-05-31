package com.example.QuietMelody.service;

import com.example.QuietMelody.domain.Catalog;
import com.example.QuietMelody.repos.CatalogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CatalogService {
    @Autowired
    private CatalogRepo catalogRepo;
    public Catalog getOne(Long id) {
        return catalogRepo.getById(id);
    }
    public Page<Catalog> findAvailable(PageRequest pageRequest) {
        return catalogRepo.findAvailable(pageRequest);
    }
}
