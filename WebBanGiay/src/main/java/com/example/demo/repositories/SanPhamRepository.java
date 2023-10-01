package com.example.demo.repositories;

import com.example.demo.models.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SanPhamRepository extends JpaRepository<SanPham, UUID> {
    @Query("select sp from SanPham sp where sp.ma like %:search% or sp.tenSP like %:search%")
    List<SanPham> search(String search);
}
