<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Thêm Chi Tiết Sản Phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h4 style="text-align: center">Thông tin chi tiết sản phẩm</h4>
<div class="container">
    <%--@elvariable id="chiTietSanPham" type=""--%>
    <form:form action="/chi-tiet-san-pham/add" method="post" modelAttribute="chiTietSanPham">
        <div class="row">
            <div class="col-6">
                <div class="form-floating mb-3 mt-3">
                    <form:select class="form-select" path="sanPham">
                        <option>Sản phẩm</option>
                        <form:options items="${listSP}" itemValue="id" itemLabel="ten"/>
                    </form:select>
                    <form:label class="form-label" path="sanPham">Tên sản phẩm:</form:label>
                    <form:errors path="sanPham" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:select class="form-select" path="mauSac">
                        <option>Màu sắc</option>
                        <form:options items="${listMS}" itemValue="id" itemLabel="ten"/>
                    </form:select>
                    <form:label class="form-label" path="mauSac">Màu sắc:</form:label>
                    <form:errors path="mauSac" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:select class="form-select" path="size">
                        <option>Kích cỡ</option>
                        <form:options items="${listSize}" itemValue="id" itemLabel="size"/>
                    </form:select>
                    <form:label class="form-label" path="size">Kích cỡ:</form:label>
                    <form:errors path="size" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:select class="form-select" path="de">
                        <option>Loại đế</option>
                        <form:options items="${listDe}" itemValue="id" itemLabel="loaiDe"/>
                    </form:select>
                    <form:label class="form-label" path="de">Loại đế:</form:label>
                    <form:errors path="de" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="soLuongTon"/>
                    <form:label class="form-label" path="soLuongTon">Số lượng tồn:</form:label>
                    <form:errors path="soLuongTon" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="namBaoHanh"/>
                    <form:label class="form-label" path="namBaoHanh">Năm bảo hành:</form:label>
                    <form:errors path="namBaoHanh" cssStyle="color: red"/>
                </div>
            </div>
            <div class="col-6">
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="giaNhap"/>
                    <form:label class="form-label" path="giaNhap">Giá nhập:</form:label>
                    <form:errors path="giaNhap" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="giaBan"/>
                    <form:label class="form-label" path="giaBan">Giá bán:</form:label>
                    <form:errors path="giaBan" cssStyle="color: #ff0000"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:textarea class="form-control" placeholder="" path="moTa" />
                    <form:label class="form-label" path="moTa">Mô tả:</form:label>
                    <form:errors path="moTa" cssStyle="color: #ff0000"/>
                </div>

                <div class="form-check mb-3 mt-3">
                    <form:label class="form-label" path="tinhTrang">Tình Trạng:</form:label>
                    <form:radiobutton path="tinhTrang" value="0" checked="true"/>Còn hàng
                    <form:radiobutton path="tinhTrang" value="1"/>Hết hàng
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="urlAnh" type="file"/>
                    <form:label class="form-label" path="urlAnh">Ảnh:</form:label>
<%--                    <form:errors path="urlAnh" cssStyle="color: red"/>--%>
                </div>
            </div>
            <div class="row">
                <div class="col-12" style="text-align: center">
                    <button type="submit" class="btn btn-success"
                            id="btt">Add
                    </button>
                </div>
            </div>
        </div>
    </form:form>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</html>