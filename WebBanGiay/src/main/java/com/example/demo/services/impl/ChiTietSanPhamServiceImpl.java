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
        return repository.getAll(pageable);
    }

    @Override
    public Page<ChiTietSanPham> getAll1(Pageable pageable) {
        return repository.getAll1(pageable);
    }

    @Override
    public List<ChiTietSanPham> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ChiTietSanPham> findAll0() {
        return repository.findAll0();
    }

    @Override
    public List<ChiTietSanPham> search0(String search) {
        return repository.search(search);
    }

    @Override
    public List<ChiTietSanPham> search1(String search) {
        return repository.search1(search);
    }

    @Override
    public List<ChiTietSanPham> loc(String locSP, String locMS, String locSize, String locDe) {
        return repository.loc(locSP, locMS, locSize, locDe);
    }

    @Override
    public List<ChiTietSanPham> loc1(String locSP, String locMS, String locSize, String locDe) {
        return repository.loc1(locSP, locMS, locSize, locDe);
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
    public void updateTT() {
        repository.updateTT();
    }

}
