package com.example.demo.services;

import com.example.demo.models.DiaChi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface DiaChiService {
    //phân trang bên trang hiển thị
    public Page<DiaChi> getAll(Pageable pageable);

    //phân trang bên trang view trạng thái
    public Page<DiaChi> getAll1(Pageable pageable);

    //làm mã tự tăng
    public List<DiaChi> findAll();

    //hiển thị liên combobox với những trường có trạng thái 0
    public List<DiaChi> findAll0();

    //tìm theo id
    public DiaChi findById(UUID id);

    //thêm
    public DiaChi add(DiaChi diaChi);

    //update
    public DiaChi update(UUID id, DiaChi diaChi);

    //update lại toàn bộ các trường có trạng thái 0
    public void updateTT();

    //tìm kiếm bên trang trạng thái
    public List<DiaChi> search0(String ten);

    //tìm kiếm bên trang view trạng thái
    public List<DiaChi> search1(String ten);
}
