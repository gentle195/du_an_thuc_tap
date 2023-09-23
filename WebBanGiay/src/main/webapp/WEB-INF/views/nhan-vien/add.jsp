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
    <title>Document</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h4 style="text-align: center">Thông tin khách hàng</h4>
<div class="container">
    <form:form action="/nhan-vien/add" method="post" modelAttribute="nhanVien">
        <div class="row">
            <div class="col-6">
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="hoTen"/>
                    <form:label class="form-label" path="hoTen">Họ tên NhanVien:</form:label>
                    <form:errors path="hoTen" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="email"/>
                    <form:label class="form-label" path="email">Email:</form:label>
                    <form:errors path="email" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="sdt"/>
                    <form:label class="form-label" path="sdt">Số điện thoại:</form:label>
                    <form:errors path="sdt" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="ngaySinh" type="date"/>
                    <form:label class="form-label" path="ngaySinh">Ngày sinh:</form:label>
                    <form:errors path="ngaySinh" cssStyle="color: red"/>
                </div>

                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="queQuan" />
                    <form:label class="form-label" path="queQuan">Quê quán:</form:label>
                    <form:errors path="queQuan" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <div class="row">
                        <div class="col-10">
                            <form:select path="chucVu" class="form-control" id="selectChucVu"
                                         cssStyle="font-weight: bold; width: 100%">
                                <option selected disabled>Chức vụ</option>
                                <form:options items="${listChucVu}" itemLabel="ten" itemValue="id"/>
                            </form:select>
                        </div>
                        <div class="col-2">
                            <a class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModalChucVu">
                                <img src="../img/plus.png">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-6">
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="canCuoc" />
                    <form:label class="form-label" path="canCuoc">Căn cước:</form:label>
                    <form:errors path="canCuoc" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="taiKhoan"/>
                    <form:label class="form-label" path="taiKhoan">Tài khoản:</form:label>
                    <form:errors path="taiKhoan" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="matKhau"/>
                    <form:label class="form-label" path="matKhau">Mật khẩu:</form:label>
                    <form:errors path="matKhau" cssStyle="color: red"/>
                </div>
                <div class="form-check mb-3 mt-3">
                    <form:label class="form-label" path="gioiTinh">Giới tính:</form:label>
                    <form:radiobutton path="gioiTinh" value="true" checked="true" />Nam
                    <form:radiobutton path="gioiTinh" value="false"/>Nữ
                </div>
                <div class="form-check mb-3 mt-3">
                    <form:label class="form-label" path="tinhTrang">Tình Trạng:</form:label>
                    <form:radiobutton path="tinhTrang" value="0" checked="true"/>Nhân viên mới
                    <form:radiobutton path="tinhTrang" value="1"/>Nhân viên cũ
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