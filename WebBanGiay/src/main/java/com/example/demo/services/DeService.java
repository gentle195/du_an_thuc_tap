package com.example.demo.services;

import com.example.demo.models.De;
import com.example.demo.models.MauSac;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DeService {
    public Page<De> getAll(Pageable pageable);

    public List<De> findAll();

    public List<De> search(String search);

    public De findById(UUID id);

    public De add(De de );

    public De update(UUID id, De de);

    public Boolean delete(UUID id);
}
