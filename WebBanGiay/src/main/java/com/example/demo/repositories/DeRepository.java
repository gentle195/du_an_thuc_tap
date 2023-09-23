package com.example.demo.repositories;

import com.example.demo.models.De;
import com.example.demo.models.MauSac;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeRepository extends JpaRepository<De, UUID> {
    @Query("select de from De de where de.ma like %:search% or de.loaiDe  like %:search%")
    List<De> search(String search);
}
