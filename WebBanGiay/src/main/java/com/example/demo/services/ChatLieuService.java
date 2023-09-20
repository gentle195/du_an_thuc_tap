package com.example.demo.services;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.HangSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChatLieuService {
    public Page<ChatLieu> getAll(Pageable pageable);

    public List<ChatLieu> findAll();

    public List<ChatLieu> search(String search);

    public ChatLieu findById(UUID id);

    public ChatLieu add(ChatLieu chatLieu);

    public ChatLieu update(UUID id, ChatLieu chatLieu);

    public Boolean delete(UUID id);
}
