package com.example.demo.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "chi_tiet_san_pham")
public class ChiTietSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @NotNull(message = "Không để trống thông tin")
    @Column(name = "gia_nhap")
    private BigDecimal giaNhap;

    @NotNull(message = "Không để trống thông tin")
    @Column(name = "gia_ban")
    private BigDecimal giaBan;

    @NotNull(message = "Không để trống thông tin")
    @Column(name = "so_luong_ton")
    private int soLuongTon;

    @Column(name = "url_anh")
    private String urlAnh;

    @NotNull(message = "Không để trống thông tin")
    @Column(name = "nam_bao_hang")
    private int namBaoHanh;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "mo_ta")
    private String moTa;

    @Column(name = "tinh_trang")
    private int tinhTrang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private SanPham sanPham;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mau_sac")
    private MauSac mauSac;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_size")
    private Size size;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_de")
    private De de;

}
