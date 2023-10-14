package com.example.demo.services.impl;

import com.example.demo.models.HangSanPham;
import com.example.demo.models.KhachHang;
import com.example.demo.repositories.HangSanPhamRepository;
import com.example.demo.services.HangSanPhamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HangSanPhamServiceImpl implements HangSanPhamService {
    @Autowired
    HangSanPhamRepository hangSanPhamRepository;


    @Override
    public Page<HangSanPham> getAll(Pageable pageable) {
        return hangSanPhamRepository.getAll(pageable);
    }

    @Override
    public Page<HangSanPham> getAll1(Pageable pageable) {
        return hangSanPhamRepository.getAll1(pageable);
    }

    @Override
    public List<HangSanPham> findAll() {
        return hangSanPhamRepository.findAll();
    }

    @Override
    public List<HangSanPham> findAll0() {
        return null;
    }


    @Override
    public HangSanPham findById(UUID id) {
        return hangSanPhamRepository.findById(id).orElse(null);
    }

    @Override
    public HangSanPham add(HangSanPham hangSanPham) {
        return hangSanPhamRepository.save(hangSanPham);
    }

    @Override
    public HangSanPham update(UUID id, HangSanPham hangSanPham) {
        if (id != null) {
            HangSanPham hangSanPhamUpadte = hangSanPhamRepository.findById(id).orElse(null);
            if (hangSanPham != null){
                BeanUtils.copyProperties(hangSanPham, hangSanPhamUpadte);
                hangSanPhamRepository.save(hangSanPhamUpadte);

            }


        }
        return null;
    }

    @Override
    public void updateTT() {
        hangSanPhamRepository.updateTT();
    }

    @Override
    public List<HangSanPham> search0(String ten) {
        return hangSanPhamRepository.search0(ten);
    }

    @Override
    public List<HangSanPham> search1(String ten) {
        return hangSanPhamRepository.search1(ten);
    }
}
