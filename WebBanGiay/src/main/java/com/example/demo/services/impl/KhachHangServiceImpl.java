package com.example.demo.services.impl;

import com.example.demo.models.KhachHang;
import com.example.demo.repositories.KhachHangRepository;
import com.example.demo.services.KhachHangService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class KhachHangServiceImpl implements KhachHangService {
    @Autowired
    KhachHangRepository khachHangRepository;

    @Override
    public Page<KhachHang> getAll(Pageable pageable) {
        return khachHangRepository.getAll(pageable);
    }

    @Override
    public Page<KhachHang> getAll1(Pageable pageable) {
        return khachHangRepository.getAll1(pageable);
    }

    @Override
    public List<KhachHang> findAll() {
        return khachHangRepository.findAll();
    }

    @Override
    public List<KhachHang> findAll0() {
        return khachHangRepository.findAll0();
    }

    @Override
    public KhachHang findById(UUID id) {
        return khachHangRepository.findById(id).orElse(null);
    }

    @Override
    public KhachHang add(KhachHang chip) {
        return khachHangRepository.save(chip);
    }

    @Override
    public KhachHang update(UUID id, KhachHang chip) {
        if (id != null) {
            KhachHang chipUpdate = khachHangRepository.findById(id).orElse(null);
            if (chipUpdate != null) {
                BeanUtils.copyProperties(chip, chipUpdate);
                khachHangRepository.save(chipUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {
        khachHangRepository.updateTT();
    }

    @Override
    public List<KhachHang> search0(String ten) {
        return khachHangRepository.search0(ten);
    }

    @Override
    public List<KhachHang> search1(String ten) {
        return khachHangRepository.search1(ten);
    }
}
