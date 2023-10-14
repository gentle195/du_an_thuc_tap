package com.example.demo.repositories;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.HangSanPham;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChatLieuRepository extends JpaRepository<ChatLieu, UUID> {
    @Query("select cl from ChatLieu cl where cl.ma like %:search% or cl.loaiChatLieu like %:search%")
    List<ChatLieu> search(String search);

    @Query("select c from ChatLieu c  where c.tinhTrang=0")
    Page<ChatLieu> getAll(Pageable pageable);

    @Query("select c from ChatLieu c  where c.tinhTrang=1")
    Page<ChatLieu> getAll1(Pageable pageable);

    @Query("select c from KhachHang c  where  c.tinhTrang = 0 ")
    List<ChatLieu> findAll0();

    //tìm kiếm bên trang trạng thái
    @Query("select c from ChatLieu c  where  c.tinhTrang = 0 and (c.ma like %:ten% or c.loaiChatLieu like %:ten%)")
    List<ChatLieu> search0(String ten);

    //tìm kiếm bên trang view trạng thái
    @Query("select c from ChatLieu c  where  c.tinhTrang = 1 and (c.ma like %:ten% or c.loaiChatLieu like %:ten%)")
    List<ChatLieu> search1(String ten);

    //update lại toàn bộ các trường có trạng thái 0, vì là câu native query nên tên bảng sẽ lấy theo tên trong sql
    @Transactional
    @Modifying
    @Query(value = "update chat_lieu set tinh_trang=0", nativeQuery = true)
    void updateTT();
}
