package com.example.demo.services;

import com.example.demo.models.HangSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface HangSanPhamService {
    public Page<HangSanPham> getAll(Pageable pageable);

    //phân trang bên trang view trạng thái
    public Page<HangSanPham> getAll1(Pageable pageable);

    public List<HangSanPham> findAll();

    //hiển thị liên combobox với những trường có trạng thái 0
    public List<HangSanPham> findAll0();

    //tìm theo id
    public HangSanPham findById(UUID id);

    //thêm
    public HangSanPham add(HangSanPham hangSanPham);

    //update
    public HangSanPham update(UUID id, HangSanPham hangSanPham);

    //update lại toàn bộ các trường có trạng thái 0
    public void updateTT();

    //tìm kiếm bên trang trạng thái
    public List<HangSanPham> search0(String ten);

    //tìm kiếm bên trang view trạng thái
    public List<HangSanPham> search1(String ten);
}
