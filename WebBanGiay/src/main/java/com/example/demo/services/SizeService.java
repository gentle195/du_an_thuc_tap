package com.example.demo.services;

import com.example.demo.models.MauSac;
import com.example.demo.models.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SizeService {
    public Page<Size> getAll(Pageable pageable);

    public List<Size> findAll();

    public List<Size> search(String search);

    public Size findById(UUID id);

    public Size add(Size size);

    public Size update(UUID id, Size size);

    public Boolean delete(UUID id);
}
