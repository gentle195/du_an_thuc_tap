package com.example.demo.services;

import com.example.demo.models.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface KhuyenMaiService {
    //phân trang bên trang hiển thị
    public Page<KhuyenMai> getAll(Pageable pageable);

    //phân trang bên trang view trạng thái
    public Page<KhuyenMai> getAll1(Pageable pageable);

    //làm mã tự tăng
    public List<KhuyenMai> findAll();

    //hiển thị liên combobox với những trường có trạng thái 0
    public List<KhuyenMai> findAll0();

    //tìm theo id
    public KhuyenMai findById(UUID id);

    //thêm
    public KhuyenMai add(KhuyenMai khuyenMai);

    //update
    public KhuyenMai update(UUID id, KhuyenMai khuyenMai);

    //update lại toàn bộ các trường có trạng thái 0
    public void updateTT();

    //tìm kiếm bên trang trạng thái
    public List<KhuyenMai> search0(String ten);

    //tìm kiếm bên trang view trạng thái
    public List<KhuyenMai> search1(String ten);
}
