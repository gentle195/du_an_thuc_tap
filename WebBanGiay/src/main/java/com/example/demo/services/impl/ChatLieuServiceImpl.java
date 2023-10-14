package com.example.demo.services.impl;

import com.example.demo.models.ChatLieu;
import com.example.demo.repositories.ChatLieuRepository;
import com.example.demo.services.ChatLieuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ChatLieuServiceImpl implements ChatLieuService {
    @Autowired
    ChatLieuRepository chatLieuRepository;

    @Override
    public Page<ChatLieu> getAll(Pageable pageable) {
        return chatLieuRepository.getAll(pageable);
    }

    @Override
    public Page<ChatLieu> getAll1(Pageable pageable) {
        return chatLieuRepository.getAll1(pageable);
    }

    @Override
    public List<ChatLieu> findAll() {
        return chatLieuRepository.findAll();
    }

    @Override
    public List<ChatLieu> findAll0() {
        return  chatLieuRepository.findAll0();
    }



    @Override
    public ChatLieu findById(UUID id) {
        return chatLieuRepository.findById(id).orElse(null);
    }

    @Override
    public ChatLieu add(ChatLieu chatLieu) {
        return chatLieuRepository.save(chatLieu);
    }

    @Override
    public ChatLieu update(UUID id, ChatLieu chatLieu) {
        if(id != null){
            ChatLieu chatLieu1 = chatLieuRepository.findById(id).orElse(null);
            if(chatLieu1 != null){
                BeanUtils.copyProperties(chatLieu ,chatLieu1);
                chatLieuRepository.save(chatLieu1);
            }
        }
        return null;
    }

    @Override
    public void updateTT() {
        chatLieuRepository.updateTT();
    }

    @Override
    public List<ChatLieu> search0(String ten) {
        return chatLieuRepository.search0(ten);
    }

    @Override
    public List<ChatLieu> search1(String ten) {
        return chatLieuRepository.search1(ten);
    }
}
