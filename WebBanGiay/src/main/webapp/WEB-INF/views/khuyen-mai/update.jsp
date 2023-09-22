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
    <title>Sửa Khuyến Mãi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h4 style="text-align: center">Thông tin khuyến mãi</h4>
<div class="container">
    <%--@elvariable id="khuyenMai" type=""--%>
    <form:form action="/khuyen-mai/update/${khuyenMai.id}" method="post" modelAttribute="khuyenMai">
        <div class="row">
            <div class="col-6">
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="ten"/>
                    <form:label class="form-label" path="ten">Tên chương trình:</form:label>
                    <form:errors path="ten" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="ngayBatDau" type="date"/>
                    <form:label class="form-label" path="ngayBatDau">Ngày bắt đầu:</form:label>
<%--                    <form:errors path="ngayBatDau" cssStyle="color: red"></form:errors>--%>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="ngayKetThuc" type="date"/>
                    <form:label class="form-label" path="ngayKetThuc">Ngày kết thúc</form:label>
<%--                    <form:errors path="ngayKetThuc" cssStyle="color: red"></form:errors>--%>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="soTienGiam"/>
                    <form:label class="form-label" path="soTienGiam">Mức:</form:label>
                    <form:errors path="soTienGiam" cssStyle="color: red"/>
                </div>
            </div>
            <div class="col-6">
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="loaiGiamGia"/>
                    <form:label class="form-label" path="loaiGiamGia">Loại:</form:label>
                    <form:errors path="loaiGiamGia" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="hinhThucGiamGia"/>
                    <form:label class="form-label" path="hinhThucGiamGia">Hình thức:</form:label>
                    <form:errors path="hinhThucGiamGia" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:textarea class="form-control" placeholder="" path="moTa" />
                    <form:label class="form-label" path="moTa">Mô tả:</form:label>
                    <form:errors path="moTa" cssStyle="color: #ff0000"/>
                </div>
                <div class="form-check mb-3 mt-3">
                    <form:label class="form-label" path="tinhTrang">Tình trạng:</form:label>
                    <form:radiobutton path="tinhTrang" value="0" checked="true"/>Còn chương trình
                    <form:radiobutton path="tinhTrang" value="1"/>Hết chương trình
                </div>
            </div>
            <div class="row">
                <div class="col-12" style="text-align: center">
                    <button type="submit" class="btn btn-success"
                            id="btt">Update
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