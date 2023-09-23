package com.example.demo.services.impl;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.MauSac;
import com.example.demo.repositories.MauSacRepository;
import com.example.demo.services.MauSacService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MauSacServiceImpl implements MauSacService {
    @Autowired
    MauSacRepository mauSacRepository;
    @Override
    public Page<MauSac> getAll(Pageable pageable) {
        return mauSacRepository.findAll(pageable);
    }

    @Override
    public List<MauSac> findAll() {
        return mauSacRepository.findAll();
    }

    @Override
    public List<MauSac> search(String search) {
        return mauSacRepository.search(search);
    }

    @Override
    public MauSac findById(UUID id) {
        return mauSacRepository.findById(id).orElse(null);
    }

    @Override
    public MauSac add(MauSac mauSac) {
        return null;
    }

    @Override
    public MauSac update(UUID id, MauSac mauSac) {
            if(id != null){
                MauSac mauSac1 = mauSacRepository.findById(id).orElse(null);
                if(mauSac1 != null){
                    BeanUtils.copyProperties(mauSac ,mauSac1);
                    mauSacRepository.save(mauSac1);
                }
            }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if(id != null){
            MauSac mauSac = mauSacRepository.findById(id).orElse(null);
            if(mauSac != null){
                mauSacRepository.delete(mauSac);
                return true;
            }
        }
        return false;
    }
}
