USE
[master]
CREATE 
DATABASE [WEB_GIAY]
GO

USE [WEB_GIAY]
GO

CREATE TABLE khach_hang
(
	id   UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma			   varchar(50) Not Null,
	ho_ten		   NVARCHAR(50) Not Null,
    sdt			   VARCHAR(15)  Null,
    email          VARCHAR(50) Null,
    gioi_tinh       BIT Null,
	ngay_sinh       DATE Null,
    tai_khoan		NVARCHAR(100)Null,
    mat_khau		NVARCHAR(100)Null,
	ngay_tao		Date Default GetDATE(),
	ngay_cap_nhat   DATE Null,
    tinh_trang      INT Null,
) 
go

CREATE TABLE dia_chi
(
    id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma	VARCHAR(30) Not Null,
    dia_chi NVARCHAR(50) Null,
    quan  NVARCHAR( MAX) Null,
    huyen NVARCHAR(50) Null,
    thanh_pho    NVARCHAR(50) Null,
	id_khach_hang    UNIQUEIDENTIFIER
        REFERENCES khach_hang (id),
    ngay_tao		DATE Default GetDATE(),
	ngay_cap_nhat   DATE Null,
	tinh_trang      INT Null,
	mo_ta  NVARCHAR( MAX)  
)
    GO

CREATE TABLE chuc_vu
(
	id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma NVARCHAR(50) Not Null,
	ten NVARCHAR(50) Not Null,
	ngay_tao		DATE Default GetDATE(),
	ngay_cap_nhat   DATE Null,
	tinh_trang      INT Null,
	mo_ta  NVARCHAR( MAX)  
) 
go

CREATE TABLE nhan_vien
(
	id   UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma				varchar(50) Not Null,
	ho_ten		    NVARCHAR(50) Not Null,
	sdt			    VARCHAR(15) Null,
    email           VARCHAR(50) Null,
	url_anh         VARCHAR(50) Null,
    gioi_tinh       BIT Null,
	ngay_sinh       DATE Null,
	que_quan		NVARCHAR(100) Null,
	cccd			VARCHAR(30) Null,
    tai_khoan		NVARCHAR(100),
    mat_khau		NVARCHAR(100),
	ngay_tao		DATE Default GetDATE(),
	ngay_cap_nhat   DATE Null,
    tinh_trang      INT Null,
	id_chuc_vu	UNIQUEIDENTIFIER
        REFERENCES chuc_vu (id),
)

-- HangSanPham
CREATE TABLE hang_san_pham(
	id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma	varchar(30)Not Null ,
	ten NVARCHAR(50) Not Null,
	xuat_su NVARCHAR(50),
	ngay_tao		DATE Default GetDATE(),
	ngay_cap_nhat   DATE Null,
	tinh_trang      INT Null,
	mo_ta  NVARCHAR( MAX)  Null 
)
GO

-- MauSac
CREATE TABLE mau_sac(
	id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma	varchar(30) Not Null,
	ten NVARCHAR(50) Not Null,
	ngay_tao		DATE Default GetDATE(),
	ngay_cap_nhat   DATE Null,
	tinh_trang      INT Null,
	mo_ta  NVARCHAR( MAX) Null
)
GO

-- Size
CREATE TABLE size
(
	id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma	varchar(30) Not Null,
	size NVARCHAR(50) Not Null,
	ngay_tao		DATE Default GetDATE(),
	ngay_cap_nhat   DATE Null,
	tinh_trang      INT Null,
	mo_ta  NVARCHAR( MAX) Null
)
GO

-- ChatLieu
CREATE TABLE chat_lieu
(
	id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma	varchar(30) Not Null,
	loai_chat_lieu NVARCHAR(50) Not Null,
	ngay_tao		DATE Default GetDATE(),
	ngay_cap_nhat   DATE Null,
	tinh_trang      INT Null,
	mo_ta  NVARCHAR( MAX) Null
)
GO

-- de
CREATE TABLE de(
	id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma	varchar(30) Not Null,
	loai_de NVARCHAR(50) Not Null,
	ngay_tao		DATE Default GetDATE(),
	ngay_cap_nhat   DATE Null,
	tinh_trang      INT Null,
	mo_ta  NVARCHAR( MAX) Null
)
GO

--hinhanh
CREATE TABLE hinh_anh
(
	id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma	varchar(30) Not Null,
	ten NVARCHAR(50) Not Null,
	url_anh1  VARCHAR(50) Null,
	url_anh2   VARCHAR(50) Null,
	url_anh3  VARCHAR(50) Null,
	ngay_tao DATE Default GetDATE(),
	ngay_cap_nhat   DATE Null,
	tinh_trang  INT Null,
	mo_ta  NVARCHAR( MAX) Null
)
GO

-- SanPham
CREATE TABLE san_pham
(
	id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma	varchar(50)Not Null ,
	ten NVARCHAR(100) Null,
	ngay_tao		DATE Default GetDATE(),
	ngay_cap_nhat   DATE Null,
	tinh_trang      INT Null,
	mo_ta  NVARCHAR( MAX) Null,
	id_anh	UNIQUEIDENTIFIER
        REFERENCES hinh_anh (id) Null,
	id_hang	UNIQUEIDENTIFIER
        REFERENCES hang_san_pham (id) Null,
	id_chat_lieu	UNIQUEIDENTIFIER
        REFERENCES chat_lieu (id) Null,
)
GO

-- ChiTietSP
CREATE TABLE chi_tiet_san_pham
(
	Id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	id_san_pham	UNIQUEIDENTIFIER
        REFERENCES san_pham (Id)Not Null,
	gia_nhap Money Null,
	gia_ban Money Null,
	so_luong_ton INT Null,
	url_anh Varchar(50) Null,
	nam_bao_hang   int Null,
	ngay_tao		DATE Default GetDATE(),
	ngay_cap_nhat   DATE Null,
	tinh_trang      INT Null,
	mo_ta  NVARCHAR( MAX) Null,
	id_mau_sac	UNIQUEIDENTIFIER
        REFERENCES mau_sac (id) Null,
	id_size	UNIQUEIDENTIFIER
        REFERENCES size (id) Null,
	id_de	UNIQUEIDENTIFIER
        REFERENCES de (id) Null,
)
GO

CREATE TABLE khuyen_mai
(
	id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma	varchar(50) Not Null,
	ten NVARCHAR(100) Not Null,
	ngay_tao		DATE Default GetDATE() Null,
	ngay_cap_nhat   DATE Null,
	ngay_bat_dau		DATE Null,
	ngay_ket_thuc   DATE Null,
	loai_giam_gia NVARCHAR(100) Null,
	hinh_thuc_giam_gia NVARCHAR(100) Null,
	so_tien_giam Money Null,
	tinh_trang   INT Null,
	mo_ta  NVARCHAR( MAX) Null
)
GO

CREATE TABLE san_pham_giam_gia
(
	Id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	id_chi_tiet_san_pham	UNIQUEIDENTIFIER
        REFERENCES chi_tiet_san_pham (id) Not Null,
	id_khuyen_mai	UNIQUEIDENTIFIER
        REFERENCES khuyen_mai (id) Not Null,
	tinh_trang  INT Null,
	mo_ta  NVARCHAR( MAX) Null
)
GO

CREATE TABLE gio_hang
(
    id   UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
    id_khach_hang  UNIQUEIDENTIFIER
        REFERENCES khach_hang (id) Not Null,
	ma	varchar(50) Not Null,
	ngay_tao     DATE Default GetDATE(),
	tinh_trang      INT Null,
	id_quy_doi varchar(50) Null
)
    GO

CREATE TABLE gio_hang_chi_tiet
(
	id   UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
    id_gio_hang UNIQUEIDENTIFIER
        REFERENCES gio_hang (id) Not Null,
    id_chi_tiet_san_pham UNIQUEIDENTIFIER
        REFERENCES chi_tiet_san_pham (id) Not Null,
    so_luong   INT Null,
	don_gia Money Null,
	don_gia_khi_giam Money Null,
    tinh_trang INT Null,
 )
    GO


--HoaDon
CREATE TABLE hoa_don(
	id   UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma	varchar(50) Not Null,
	id_nhan_vien  UNIQUEIDENTIFIER
        REFERENCES nhan_vien (id) Not Null,
    id_khach_hang  UNIQUEIDENTIFIER
        REFERENCES khach_hang (id)Not Null,
	id_dia_chi UNIQUEIDENTIFIER
        REFERENCES dia_chi (id) Not Null,
	ten_khach_hang_nhan NVARCHAR(100) Null,
	sdt varchar(50) Null,
	tong_tien Money Not Null,
	ngay_tao		DATE Default GetDATE() ,
	ngay_cap_nhat   DATE Null,
	ngay_ship		DATE Null,
	ngay_nhan   DATE Null,
	tinh_trang      INT Null,
	ghi_chu  NVARCHAR( MAX) Null
)
GO

-- HoaDonChiTiet
CREATE TABLE hoa_don_chi_tiet(
	id UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	id_chi_tiet_san_pham	UNIQUEIDENTIFIER
        REFERENCES chi_tiet_san_pham (id) Not Null,
	id_hoa_don	UNIQUEIDENTIFIER
        REFERENCES Hoa_Don (Id) Not Null,
	so_luong INT Not Null,
	don_gia Money Not Null,
	tinh_trang  INT Null,
)
Go

-- doitra
CREATE TABLE doi_tra
(
    id   UNIQUEIDENTIFIER
        DEFAULT NEWID() PRIMARY KEY,
	ma	varchar(50) Not Null,
	id_nhan_vien  UNIQUEIDENTIFIER
        REFERENCES nhan_vien (id) Not Null,
    id_khach_hang  UNIQUEIDENTIFIER
        REFERENCES khach_hang (id) Not Null,
	id_hoa_don UNIQUEIDENTIFIER
        REFERENCES hoa_don (id) Not Null,		
	ngay_doi_tra	DATE Default GetDATE(),
	ngay_hoan_tra   DATE Null,
	ly_do_doi_tra NVARCHAR(Max) Null,
	tien_doi_tra Money,
	tinh_trang  INT Null, 
)
    GO