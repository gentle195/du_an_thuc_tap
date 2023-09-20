package com.example.demo.repositories;

import com.example.demo.models.ChucVu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChucVuRepository extends JpaRepository<ChucVu, UUID> {
    @Query("select ch from ChucVu ch where ch.ma like %:search% or ch.ten like %:search%")
    List<ChucVu> search(String search);
}
