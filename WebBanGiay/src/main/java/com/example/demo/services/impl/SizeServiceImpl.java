package com.example.demo.services.impl;

import com.example.demo.models.De;
import com.example.demo.models.Size;
import com.example.demo.repositories.DeRepository;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.services.SizeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    SizeRepository sizeRepository;
    @Override
    public Page<Size> getAll(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }

    @Override
    public List<Size> findAll() {
        return sizeRepository.findAll();
    }

    @Override
    public List<Size> search(String search) {
        return sizeRepository.search(search);
    }

    @Override
    public Size findById(UUID id) {
        return sizeRepository.findById(id).orElse(null);
    }

    @Override
    public Size add(Size size) {
        return null;
    }

    @Override
    public Size update(UUID id, Size size) {
        if(id != null){
            Size size1  = sizeRepository.findById(id).orElse(null);
            if(size1 != null){
                BeanUtils.copyProperties(size, size1 );
                sizeRepository.save(size);
            }
        }
        return null;
    }

    @Override
    public Boolean delete(UUID id) {
        if(id != null){
            Size size   = sizeRepository.findById(id).orElse(null);
            if(size != null){
                sizeRepository.delete(size);
                return true;
            }
        }
        return false;
    }
}
