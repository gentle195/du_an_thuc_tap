package com.example.demo.services.impl;

import com.example.demo.models.De;
import com.example.demo.models.MauSac;
import com.example.demo.repositories.DeRepository;
import com.example.demo.repositories.MauSacRepository;
import com.example.demo.services.DeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DeServiceImpl implements DeService {
    @Autowired
    DeRepository deRepository;
    @Override
    public Page<De> getAll(Pageable pageable) {
        return deRepository.findAll(pageable);
    }

    @Override
    public List<De> findAll() {
        return deRepository.findAll();
    }

    @Override
    public List<De> search(String search) {
        return deRepository.search(search);
    }

    @Override
    public De findById(UUID id) {
        return deRepository.findById(id).orElse(null);
    }

    @Override
    public De add(De de) {
        return null;
    }

    @Override
    public De update(UUID id, De de) {
        if(id != null){
            De de1  = deRepository.findById(id).orElse(null);
            if(de1 != null){
                BeanUtils.copyProperties(de ,de1);
                deRepository.save(de);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if(id != null){
            De de   = deRepository.findById(id).orElse(null);
            if(de != null){
                deRepository.delete(de);
                return true;
            }
        }
        return false;
    }
}
