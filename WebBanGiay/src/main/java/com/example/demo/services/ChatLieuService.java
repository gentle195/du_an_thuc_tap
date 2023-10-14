package com.example.demo.services;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.HangSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChatLieuService {
    public Page<ChatLieu> getAll(Pageable pageable);

    //phân trang bên trang view trạng thái
    public Page<ChatLieu> getAll1(Pageable pageable);

    public List<ChatLieu> findAll();

    //hiển thị liên combobox với những trường có trạng thái 0
    public List<ChatLieu> findAll0();

    //tìm theo id
    public ChatLieu findById(UUID id);

    //thêm
    public ChatLieu add(ChatLieu chatLieu);

    //update
    public ChatLieu update(UUID id, ChatLieu chatLieu);

    //update lại toàn bộ các trường có trạng thái 0
    public void updateTT();

    //tìm kiếm bên trang trạng thái
    public List<ChatLieu> search0(String ten);

    //tìm kiếm bên trang view trạng thái
    public List<ChatLieu> search1(String ten);
}
