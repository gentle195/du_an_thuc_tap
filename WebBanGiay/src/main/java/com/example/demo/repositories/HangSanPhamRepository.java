package com.example.demo.repositories;

import com.example.demo.models.HangSanPham;
import com.example.demo.models.KhachHang;
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
public interface HangSanPhamRepository extends JpaRepository<HangSanPham, UUID> {
    @Query("select hsp from HangSanPham hsp where hsp.ma like %:search% or hsp.ten like %:search%")
    List<HangSanPham> search(String search);

    @Query("select c from HangSanPham c  where c.tinhTrang=0")
    Page<HangSanPham> getAll(Pageable pageable);

    @Query("select c from HangSanPham c  where c.tinhTrang=1")
    Page<HangSanPham> getAll1(Pageable pageable);

    @Query("select c from HangSanPham c  where  c.tinhTrang = 0 ")
    List<HangSanPham> findAll0();

    //tìm kiếm bên trang trạng thái
    @Query("select c from HangSanPham c  where  c.tinhTrang = 0 and (c.ma like %:ten% or c.ten like %:ten%)")
    List<HangSanPham> search0(String ten);

    //tìm kiếm bên trang view trạng thái
    @Query("select c from HangSanPham c  where  c.tinhTrang = 1 and (c.ma like %:ten% or c.ten like %:ten%)")
    List<HangSanPham> search1(String ten);

    //update lại toàn bộ các trường có trạng thái 0, vì là câu native query nên tên bảng sẽ lấy theo tên trong sql
    @Transactional
    @Modifying
    @Query(value = "update hang_san_pham set tinh_trang=0", nativeQuery = true)
    void updateTT();
}
