package com.example.demo.repositories;

import com.example.demo.models.NhanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface NhanVienRepository extends JpaRepository<NhanVien, UUID> {
    @Query("select nv from NhanVien nv where nv.ma like %:search% or nv.hoTen like %:search%")
    List<NhanVien> search(String search);
}
