package com.example.demo.services.impl;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.repositories.ChiTietSanPhamRepository;
import com.example.demo.services.ChiTietSanPhamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChiTietSanPhamServiceImpl implements ChiTietSanPhamService {
    @Autowired
    private ChiTietSanPhamRepository repository;

    @Override
    public Page<ChiTietSanPham> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<ChiTietSanPham> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ChiTietSanPham> search(String search) {
        return repository.search(search);
    }

    @Override
    public ChiTietSanPham findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ChiTietSanPham add(ChiTietSanPham chiTietSanPham) {
        return repository.save(chiTietSanPham);
    }

    @Override
    public ChiTietSanPham update(UUID id, ChiTietSanPham chiTietSanPham) {
        if(id != null){
            ChiTietSanPham ctsp = repository.findById(id).orElse(null);
            if(ctsp != null){
                BeanUtils.copyProperties(chiTietSanPham, ctsp);
                repository.save(ctsp);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if(id != null){
            ChiTietSanPham ctsp = repository.findById(id).orElse(null);
            if(ctsp != null){
                repository.delete(ctsp);
                return true;
            }
        }
        return false;
    }
}
