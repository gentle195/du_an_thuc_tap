package com.example.demo.repositories;

import com.example.demo.models.HangSanPham;
import com.example.demo.models.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HangSanPhamRepository extends JpaRepository<HangSanPham, UUID> {
    @Query("select hsp from HangSanPham hsp where hsp.ma like %:search% or hsp.ten like %:search%")
    List<HangSanPham> search(String search);
}
