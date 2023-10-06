package com.example.demo.services;

import com.example.demo.models.ChucVu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ChucVuService {
    //phân trang bên trang hiển thị
    public Page<ChucVu> getAll(Pageable pageable);

    //phân trang bên trang view trạng thái
    public Page<ChucVu> getAll1(Pageable pageable);

    //làm mã tự tăng
    public List<ChucVu> findAll();

    //hiển thị liên combobox với những trường có trạng thái 0
    public List<ChucVu> findAll0();

    //tìm theo id
    public ChucVu findById(UUID id);

    //thêm
    public ChucVu add(ChucVu chucVu);

    //update
    public ChucVu update(UUID id, ChucVu chucVu);

    //update lại toàn bộ các trường có trạng thái 0
    public void updateTT();

    //tìm kiếm bên trang trạng thái
    public List<ChucVu> search0(String ten);

    //tìm kiếm bên trang view trạng thái
    public List<ChucVu> search1(String ten);
}
