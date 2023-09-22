package com.example.demo.services.impl;

import com.example.demo.models.KhuyenMai;
import com.example.demo.repositories.KhuyenMaiRepository;
import com.example.demo.services.KhuyenMaiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KhuyenMaiServiceImpl implements KhuyenMaiService {
    @Autowired
    private KhuyenMaiRepository repository;

    @Override
    public Page<KhuyenMai> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public List<KhuyenMai> findAll() {
        return repository.findAll();
    }

    @Override
    public List<KhuyenMai> search(String search) {
        return repository.search(search);
    }

    @Override
    public KhuyenMai findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public KhuyenMai add(KhuyenMai khuyenMai) {
        return repository.save(khuyenMai);
    }

    @Override
    public KhuyenMai update(UUID id, KhuyenMai khuyenMai) {
        if (id != null) {
            KhuyenMai km = repository.findById(id).orElse(null);
            if (km != null) {
                BeanUtils.copyProperties(khuyenMai, km);
                repository.save(km);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            KhuyenMai km = repository.findById(id).orElse(null);
            if (km != null) {
                repository.delete(km);
                return true;
            }
        }
        return false;
    }
}
