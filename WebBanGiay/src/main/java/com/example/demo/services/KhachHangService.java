package com.example.demo.services;

import com.example.demo.models.KhachHang;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface KhachHangService {
    //phân trang bên trang hiển thị
    public Page<KhachHang> getAll(Pageable pageable);

    //phân trang bên trang view trạng thái
    public Page<KhachHang> getAll1(Pageable pageable);

    //làm mã tự tăng
    public List<KhachHang> findAll();

    //hiển thị liên combobox với những trường có trạng thái 0
    public List<KhachHang> findAll0();

    //tìm theo id
    public KhachHang findById(UUID id);

    //thêm
    public KhachHang add(KhachHang khachHang);

    //update
    public KhachHang update(UUID id, KhachHang khachHang);

    //update lại toàn bộ các trường có trạng thái 0
    public void updateTT();

    //tìm kiếm bên trang trạng thái
    public List<KhachHang> search0(String ten);

    //tìm kiếm bên trang view trạng thái
    public List<KhachHang> search1(String ten);
}
