package com.example.demo.services.impl;

import com.example.demo.models.SanPhamGiamGia;
import com.example.demo.repositories.SanPhamGiamGiaRepository;
import com.example.demo.services.SanPhamGiamGiaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SanPhamGiamGiaServiceImpl implements SanPhamGiamGiaService {
    @Autowired
    private SanPhamGiamGiaRepository repository;

    @Override
    public Page<SanPhamGiamGia> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<SanPhamGiamGia> findAll() {
        return repository.findAll();
    }

    @Override
    public List<SanPhamGiamGia> search(String search) {
        return null;
    }

    @Override
    public SanPhamGiamGia findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public SanPhamGiamGia add(SanPhamGiamGia sanPhamGiamGia) {
        return repository.save(sanPhamGiamGia);
    }

    @Override
    public SanPhamGiamGia update(UUID id, SanPhamGiamGia sanPhamGiamGia) {
        if(id != null){
            SanPhamGiamGia spgg = repository.findById(id).orElse(null);
            if(spgg != null){
                BeanUtils.copyProperties(sanPhamGiamGia, spgg);
                repository.save(spgg);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if(id != null){
            SanPhamGiamGia spgg = repository.findById(id).orElse(null);
            if(spgg != null){
                repository.delete(spgg);
                return true;
            }
        }
        return false;
    }
}
