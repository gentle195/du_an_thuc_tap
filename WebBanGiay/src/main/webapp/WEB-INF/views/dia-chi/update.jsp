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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
</head>
<body>
<h4 style="text-align: center">Thông tin khách hàng</h4>
<div class="container">
    <form:form action="/dia-chi/update" method="post" modelAttribute="DiaChi">
        <div class="row">
            <div class="col-6">
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="diaChi"/>
                    <form:label class="form-label" path="diaChi">Địa chỉ nhận hàng:</form:label>
                    <form:errors path="diaChi" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="quan"/>
                    <form:label class="form-label" path="quan">Quận:</form:label>
                    <form:errors path="quan" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="huyen"/>
                    <form:label class="form-label" path="huyen">Huyện:</form:label>
                    <form:errors path="huyen" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="thanhPho"/>
                    <form:label class="form-label" path="thanhPho">Thành phố:</form:label>
                    <form:errors path="thanhPho" cssStyle="color: red"/>
                </div>
            </div>
            <div class="col-6">
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="moTa"/>
                    <form:label class="form-label" path="moTa">Mô tả:</form:label>
                    <form:errors path="moTa" cssStyle="color: red"/>
                </div>
                <div class="form-check mb-3 mt-3">
                    <form:label class="form-label" path="tinhTrang">Tình Trạng:</form:label>
                    <form:radiobutton path="tinhTrang" value="0" checked="true"/>Địa chỉ mới
                    <form:radiobutton path="tinhTrang" value="1"/>Địa chỉ cũ
                </div>
                <div class="form-floating mb-3 mt-3">
                    <div class="row">
                        <div class="col-10">
                            <form:select path="khachHang" class="form-control" id="selectKhachHang"
                                         cssStyle="font-weight: bold; width: 100%">
                                <option selected disabled>Khách hàng</option>
                                <form:options items="${listKhachHang}" itemLabel="hoTen" itemValue="id"/>
                            </form:select>
                        </div>
                        <div class="col-2">
                            <a class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModalKhachHang">
                                <img src="../img/plus.png">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-12" style="text-align: center">
                    <button type="submit" class="btn btn-success"
                    >Update
                    </button>
                </div>
            </div>
        </div>
    </form:form>
</div>
<div class="modal fade" id="exampleModalKhachHang" tabindex="-1" aria-labelledby="exampleModalLabelKhachHang"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabelKhachHang">KhachHang</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="imeiList">
                    <table class="table">
                        <form:form action="/dia-chi/modal-add-khach-hang" method="post" modelAttribute="khachHang">
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-floating mb-3 mt-3">
                                        <form:input class="form-control" placeholder="" path="hoTen"/>
                                        <form:label class="form-label" path="hoTen">Họ tên khách hàng:</form:label>
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
                                </div>
                                <div class="col-6">
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
                                        <form:radiobutton path="gioiTinh" value="true" checked="true"/>Nam
                                        <form:radiobutton path="gioiTinh" value="false"/>Nữ
                                    </div>
                                    <div class="form-check mb-3 mt-3">
                                        <form:label class="form-label" path="tinhTrang">Tình Trạng:</form:label>
                                        <form:radiobutton path="tinhTrang" value="0" checked="true"/>Khách hàng mới
                                        <form:radiobutton path="tinhTrang" value="1"/>Khách hàng cũ
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12" style="text-align: center">
                                        <button type="submit" class="btn btn-success"
                                        >Add
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script>
    $(document).ready(function () {
        $('#selectKhachHang').select2();
    });
</script>
</html>