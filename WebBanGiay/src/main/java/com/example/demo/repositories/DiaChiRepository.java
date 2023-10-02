package com.example.demo.repositories;

import com.example.demo.models.DiaChi;
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
public interface DiaChiRepository extends JpaRepository<DiaChi, UUID> {

    //phân trang bên trang hiển thị
    @Query("select c from DiaChi c  where c.tinhTrang=0")
    Page<DiaChi> getAll(Pageable pageable);

    //phân trang bên trang view trạng thái
    @Query("select c from DiaChi c  where c.tinhTrang=1")
    Page<DiaChi> getAll1(Pageable pageable);

    //hiển thị liên combobox với những trường có trạng thái 0
    @Query("select c from DiaChi c  where  c.tinhTrang = 0 ")
    List<DiaChi> findAll0();

    //tìm kiếm bên trang trạng thái
    @Query("select dc from DiaChi dc where dc.tinhTrang=0 and(dc.ma like %:search% or dc.quan like %:search% or dc.huyen like %:search% " +
            " or dc.thanhPho like %:search% or dc.khachHang.hoTen like %:search%) ")
    List<DiaChi> search0(String search);

    //tìm kiếm bên trang view trạng thái
    @Query("select dc from DiaChi dc where dc.tinhTrang=1 and(dc.ma like %:search% or dc.quan like %:search% or dc.huyen like %:search% " +
            " or dc.thanhPho like %:search% or dc.khachHang.hoTen like %:search%) ")
    List<DiaChi> search1(String search);

    //update lại toàn bộ các trường có trạng thái 0, vì là câu native query nên tên bảng sẽ lấy theo tên trong sql
    @Transactional
    @Modifying
    @Query(value = "update dia_chi set tinh_trang=0", nativeQuery = true)
    void updateTT();
}
