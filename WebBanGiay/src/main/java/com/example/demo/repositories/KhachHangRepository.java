package com.example.demo.repositories;

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
public interface KhachHangRepository extends JpaRepository<KhachHang, UUID> {
    //quy định: trạng thái 0: còn hoạt động, còn kinh doanh
    //quy định: trạng thái 1: ngừng hoạt động, ngừng kinh doanh

    //phân trang bên trang hiển thị
    @Query("select c from KhachHang c  where c.tinhTrang=0")
    Page<KhachHang> getAll(Pageable pageable);

    //phân trang bên trang view trạng thái
    @Query("select c from KhachHang c  where c.tinhTrang=1")
    Page<KhachHang> getAll1(Pageable pageable);

    //hiển thị liên combobox với những trường có trạng thái 0
    @Query("select c from KhachHang c  where  c.tinhTrang = 0 ")
    List<KhachHang> findAll0();

    //tìm kiếm bên trang trạng thái
    @Query("select c from KhachHang c  where  c.tinhTrang = 0 and (c.ma like %:ten% or c.hoTen like %:ten%)")
    List<KhachHang> search0(String ten);

    //tìm kiếm bên trang view trạng thái
    @Query("select c from KhachHang c  where  c.tinhTrang = 1 and (c.ma like %:ten% or c.hoTen like %:ten%)")
    List<KhachHang> search1(String ten);

    //update lại toàn bộ các trường có trạng thái 0, vì là câu native query nên tên bảng sẽ lấy theo tên trong sql
    @Transactional
    @Modifying
    @Query(value = "update khach_hang set tinh_trang=0", nativeQuery = true)
    void updateTT();
}
