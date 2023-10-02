package com.example.demo.services.impl;

import com.example.demo.models.HinhAnh;
import com.example.demo.repositories.HinhAnhRepository;
import com.example.demo.services.HinhAnhService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HinhAnhServiceImpl implements HinhAnhService {
    @Autowired
    HinhAnhRepository hinhAnhRepository;

    @Override
    public Page<HinhAnh> getAll(Pageable pageable) {
        return hinhAnhRepository.getAll(pageable);
    }

    @Override
    public Page<HinhAnh> getAll1(Pageable pageable) {
        return hinhAnhRepository.getAll1(pageable);
    }

    @Override
    public List<HinhAnh> findAll() {
        return hinhAnhRepository.findAll();
    }

    @Override
    public HinhAnh findById(UUID id) {
        return hinhAnhRepository.findById(id).orElse(null);
    }

    @Override
    public HinhAnh add(HinhAnh anh) {
        return hinhAnhRepository.save(anh);
    }

    @Override
    public HinhAnh update(UUID id, HinhAnh anh) {
        if (id != null) {
            HinhAnh hinhAnhUd = hinhAnhRepository.findById(id).orElse(null);
            if (hinhAnhUd != null) {
                BeanUtils.copyProperties(anh, hinhAnhUd);
                hinhAnhRepository.save(hinhAnhUd);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {
        hinhAnhRepository.updateTT();
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            HinhAnh hinhAnhDl = hinhAnhRepository.findById(id).orElse(null);
            if (hinhAnhDl != null) {
                hinhAnhRepository.delete(hinhAnhDl);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<HinhAnh> search0(String ten) {
        return hinhAnhRepository.sreach0(ten);
    }

    @Override
    public List<HinhAnh> search1(String ten) {
        return hinhAnhRepository.sreach1(ten);
    }
}
