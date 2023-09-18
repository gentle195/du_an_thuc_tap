package com.example.demo.repositories;

import com.example.demo.models.DiaChi;
import com.example.demo.models.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiaChiRepository extends JpaRepository<DiaChi, UUID> {
    @Query("select dc from DiaChi dc where dc.ma like %:search% or dc.quan like %:search% or dc.huyen like %:search% " +
            "or dc.thanhPho like %:search% or dc.khachHang.hoTen like %:search%")
    List<DiaChi> search(String search);
}
