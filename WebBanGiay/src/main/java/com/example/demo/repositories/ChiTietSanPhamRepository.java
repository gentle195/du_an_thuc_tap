package com.example.demo.repositories;

import com.example.demo.models.ChiTietSanPham;
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
public interface ChiTietSanPhamRepository extends JpaRepository<ChiTietSanPham, UUID> {
    //quy định: trạng thái 0: còn hoạt động, còn kinh doanh
    //quy định: trạng thái 1: ngừng hoạt động, ngừng kinh doanh

    //phân trang bên trang hiển thị
    @Query("select c from ChiTietSanPham c  where c.tinhTrang=0")
    Page<ChiTietSanPham> getAll(Pageable pageable);

    //phân trang bên trang view trạng thái
    @Query("select c from ChiTietSanPham c  where c.tinhTrang=1")
    Page<ChiTietSanPham> getAll1(Pageable pageable);

    //hiển thị liên combobox với những trường có trạng thái 0
    @Query("select c from ChiTietSanPham c  where  c.tinhTrang = 0 ")
    List<ChiTietSanPham> findAll0();

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.tinhTrang = 0 and (ctsp.sizeGiay.size like %:search% or ctsp.sanPham.ten like %:search% or ctsp.mauSac.ten like %:search% or ctsp.de.loaiDe like %:search%)")
    List<ChiTietSanPham> search(String search);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.tinhTrang = 1 and (ctsp.sizeGiay.size like %:search% or ctsp.sanPham.ten like %:search% or ctsp.mauSac.ten like %:search% or ctsp.de.loaiDe like %:search%)")
    List<ChiTietSanPham> search1(String search);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.tinhTrang = 0 and (ctsp.sanPham.ten like ?1 or ctsp.mauSac.ten like ?2 or ctsp.sizeGiay.size like ?3 or ctsp.de.loaiDe like ?4)")
    List<ChiTietSanPham> loc(String locSP, String locMS, String locSize, String locDe);

    @Query("select ctsp from ChiTietSanPham ctsp where ctsp.tinhTrang = 1 and (ctsp.sanPham.ten like ?1 or ctsp.mauSac.ten like ?2 or ctsp.sizeGiay.size like ?3 or ctsp.de.loaiDe like ?4)")
    List<ChiTietSanPham> loc1(String locSP, String locMS, String locSize, String locDe);

    //update lại toàn bộ các trường có trạng thái 0, vì là câu native query nên tên bảng sẽ lấy theo tên trong sql
    @Transactional
    @Modifying
    @Query(value = "update chi_tiet_san_pham set tinh_trang=0", nativeQuery = true)
    void updateTT();
}
