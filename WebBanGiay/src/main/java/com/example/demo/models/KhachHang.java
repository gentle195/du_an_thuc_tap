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
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "ma")
    private String ma;

    @NotBlank(message = "Không để trống thông tin")
    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @NotBlank(message = "Không để trống thông tin")
    @Pattern(regexp = "^.{8,}@gmail\\.com$", message = "Email phải có ít nhất 8 ký tự và phải có đuôi @gmail.com")
    @Column(name = "email")
    private String email;

    @NotBlank(message = "Không để trống thông tin")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Sdt phải 10 số và bắt đầu bằng 0")
    @Column(name = "sdt")
    private String sdt;

    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "tai_khoan")
    private String taiKhoan;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "ngay_tao")
    private Date ngayTao;

    @Column(name = "ngay_cap_nhat")
    private Date ngayCapNhat;

    @Column(name = "tinh_trang")
    private int tinhTrang;
}
