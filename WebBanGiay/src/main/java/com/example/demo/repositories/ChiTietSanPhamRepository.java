package com.example.demo.repositories;

import com.example.demo.models.ChiTietSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.sizeGiay.size like %:search% or ctsp.sanPham.ten like %:search% or ctsp.mauSac.ten like %:search% or ctsp.de.loaiDe like %:search%")
    List<ChiTietSanPham> search(String search);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.sanPham.ten like ?1 or ctsp.mauSac.ten like ?2 or ctsp.sizeGiay.size like ?3 or ctsp.de.loaiDe like ?4")
    List<ChiTietSanPham> loc(String locSP, String locMS, String locSize, String locDe);
}
