package com.example.demo.services;

import com.example.demo.models.HinhAnh;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface HinhAnhService {
    public Page<HinhAnh> getAll(Pageable pageable);

    public Page<HinhAnh> getAll1(Pageable pageable);

    public List<HinhAnh> findAll();

    public HinhAnh findById(UUID id);

    public HinhAnh add(HinhAnh anh);

    public HinhAnh update(UUID id, HinhAnh anh);

    public void updateTT();

    public Boolean delete(UUID id);

    public List<HinhAnh> search0(String ten);

    public List<HinhAnh> search1(String ten);
}
