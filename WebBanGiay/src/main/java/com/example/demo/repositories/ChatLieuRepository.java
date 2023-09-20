package com.example.demo.repositories;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.HangSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, UUID> {
    @Query("select cl from ChatLieu cl where cl.ma like %:search% or cl.loaiChatLieu like %:search%")
    List<ChatLieu> search(String search);
}
