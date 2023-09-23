package com.example.demo.repositories;

import com.example.demo.models.De;
import com.example.demo.models.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SizeRepository extends JpaRepository<Size, UUID> {
    @Query("select si from Size si where si.ma like %:search% or si.loaiSize like %:search%")
    List<Size> search(String search);
}
