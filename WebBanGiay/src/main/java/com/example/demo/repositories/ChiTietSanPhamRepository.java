package com.example.demo.repositories;

import com.example.demo.models.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.size.size like %:search% or ctsp.sanPham.ten like %:search% or ctsp.mauSac.ten like %:search%")
    List<ChiTietSanPham> search(String search);
}
