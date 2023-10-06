package com.example.demo.repositories;

import com.example.demo.models.KhachHang;
import com.example.demo.models.KhuyenMai;
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
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, UUID> {
    //quy định: trạng thái 0: còn hoạt động, còn kinh doanh
    //quy định: trạng thái 1: ngừng hoạt động, ngừng kinh doanh

    //phân trang bên trang hiển thị
    @Query("select c from KhuyenMai c  where c.tinhTrang=0")
    Page<KhuyenMai> getAll(Pageable pageable);

    //phân trang bên trang view trạng thái
    @Query("select c from KhuyenMai c  where c.tinhTrang=1")
    Page<KhuyenMai> getAll1(Pageable pageable);

    //hiển thị liên combobox với những trường có trạng thái 0
    @Query("select c from KhuyenMai c  where  c.tinhTrang = 0 ")
    List<KhuyenMai> findAll0();

    //tìm kiếm bên trang trạng thái
    @Query("select km from KhuyenMai km where km.tinhTrang = 0 and (km.ma like %:search% or km.ten like %:search%)")
    List<KhuyenMai> search0(String search);

    //tìm kiếm bên trang view trạng thái
    @Query("select km from KhuyenMai km where km.tinhTrang = 1 and (km.ma like %:search% or km.ten like %:search%)")
    List<KhuyenMai> search1(String search);

    //update lại toàn bộ các trường có trạng thái 0, vì là câu native query nên tên bảng sẽ lấy theo tên trong sql
    @Transactional
    @Modifying
    @Query(value = "update khuyen_mai set tinh_trang=0", nativeQuery = true)
    void updateTT();

}
