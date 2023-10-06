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
    KhuyenMaiRepository repository;

    @Override
    public Page<KhuyenMai> getAll(Pageable pageable) {
        return repository.getAll(pageable);
    }

    @Override
    public Page<KhuyenMai> getAll1(Pageable pageable) {
        return repository.getAll1(pageable);
    }

    @Override
    public List<KhuyenMai> findAll() {
        return repository.findAll();
    }

    @Override
    public List<KhuyenMai> findAll0() {
        return repository.findAll0();
    }

    @Override
    public KhuyenMai findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public KhuyenMai add(KhuyenMai chip) {
        return repository.save(chip);
    }

    @Override
    public KhuyenMai update(UUID id, KhuyenMai chip) {
        if (id != null) {
            KhuyenMai chipUpdate = repository.findById(id).orElse(null);
            if (chipUpdate != null) {
                BeanUtils.copyProperties(chip, chipUpdate);
                repository.save(chipUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {
        repository.updateTT();
    }

    @Override
    public List<KhuyenMai> search0(String ten) {
        return repository.search0(ten);
    }

    @Override
    public List<KhuyenMai> search1(String ten) {
        return repository.search1(ten);
    }
}
