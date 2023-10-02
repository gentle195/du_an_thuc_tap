package com.example.demo.services.impl;

import com.example.demo.models.DiaChi;
import com.example.demo.models.DiaChi;
import com.example.demo.repositories.DiaChiRepository;
import com.example.demo.services.DiaChiService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DiaChiServiceImpl implements DiaChiService {

    @Autowired
    DiaChiRepository diaChiRepository;

    @Override
    public Page<DiaChi> getAll(Pageable pageable) {
        return diaChiRepository.getAll(pageable);
    }

    @Override
    public Page<DiaChi> getAll1(Pageable pageable) {
        return diaChiRepository.getAll1(pageable);
    }

    @Override
    public List<DiaChi> findAll() {
        return diaChiRepository.findAll();
    }

    @Override
    public List<DiaChi> findAll0() {
        return diaChiRepository.findAll0();
    }

    @Override
    public DiaChi findById(UUID id) {
        return diaChiRepository.findById(id).orElse(null);
    }

    @Override
    public DiaChi add(DiaChi diaChi) {
        return diaChiRepository.save(diaChi);
    }

    @Override
    public DiaChi update(UUID id, DiaChi diaChi) {
        if (id != null) {
            DiaChi diaChiUpdate = diaChiRepository.findById(id).orElse(null);
            if (diaChiUpdate != null) {
                BeanUtils.copyProperties(diaChi, diaChiUpdate);
                diaChiRepository.save(diaChiUpdate);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {
        diaChiRepository.updateTT();
    }

    @Override
    public List<DiaChi> search0(String ten) {
        return diaChiRepository.search0(ten);
    }

    @Override
    public List<DiaChi> search1(String ten) {
        return diaChiRepository.search1(ten);
    }
}
