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
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../../vendors/feather/feather.css">
    <link rel="stylesheet" href="../../vendors/ti-icons/css/themify-icons.css">
    <link rel="stylesheet" href="../../vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="../../css/vertical-layout-light/style.css">
    <link rel="shortcut icon" href="../../images/favicon.png"/>
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/dia-chi/hien-thi" role="tab" onclick="return myFunction4()">Thông tin địa chỉ</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Chỉnh sửa địa chỉ</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/dia-chi/hien-thi-delete" role="tab" onclick="return myFunction7()">Địa chỉ đã
                xoá</a>
        </li>
    </ul>
</div>
<div>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="description" role="tabpanel"
             aria-labelledby="description-tab">
            <form:form action="/dia-chi/update" method="post" modelAttribute="DiaChi">
                <div class="col-12 grid-margin">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Thêm địa chỉ</h4>
                            <form class="form-sample">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <form:label class="form-label" path="diaChi">Địa chỉ nhận hàng:</form:label>
                                            <div class="col-sm-9">
                                                <form:input class="form-control" placeholder="" path="diaChi"/>
                                                <form:errors path="diaChi" cssStyle="color: red"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <form:label class="form-label" path="quan">Quận:</form:label>
                                            <div class="col-sm-9">
                                                <form:input class="form-control" placeholder="" path="quan"/>
                                                <form:errors path="quan" cssStyle="color: red"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <form:label class="form-label" path="huyen">Huyện:</form:label>
                                            <div class="col-sm-9">
                                                <form:input class="form-control" placeholder="" path="huyen"/>
                                                <form:errors path="huyen" cssStyle="color: red"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <form:label class="form-label" path="thanhPho">Thành phố:</form:label>
                                            <div class="col-sm-9">
                                                <form:input class="form-control" placeholder="" path="thanhPho"/>
                                                <form:errors path="thanhPho" cssStyle="color: red"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <form:label class="form-label" path="moTa">Mô tả:</form:label>
                                            <div class="col-sm-9">
                                                <form:input class="form-control" placeholder="" path="moTa"/>
                                                <form:errors path="moTa" cssStyle="color: red"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <div class="row">
                                                <form:label class="form-label" path="khachHang">Khách hàng:</form:label>
                                                <div class="col-sm-10">
                                                    <form:select path="khachHang" class="form-control"
                                                                 id="selectKhachHang"
                                                                 cssStyle="font-weight: bold; width: 100%">
                                                        <option selected disabled>Khách hàng</option>
                                                        <form:options items="${listKhachHang}" itemLabel="hoTen"
                                                                      itemValue="id"/>
                                                    </form:select>
                                                </div>
                                                <div class="col-sm-2">
                                                    <a class="btn btn-secondary" data-bs-toggle="modal"
                                                       data-bs-target="#exampleModalKhachHang">
                                                        <img src="/uploads/plus.png">
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div style="text-align: center">
                                            <button type="submit" class="btn btn-primary mr-2"
                                                    onclick="return myFunction2()">
                                                UPDATE
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModalKhachHang" tabindex="-1" aria-labelledby="exampleModalLabelKhachHang"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-lg modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div class="modal-body">
                <form:form action="/khach-hang/add" method="post" modelAttribute="khachHang">
                    <div class="col-12 grid-margin">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">Thêm khách hàng</h4>
                                <form class="form-sample">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="form-label"
                                                            path="hoTen">Họ tên khách hàng:</form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder="" path="hoTen"/>
                                                    <form:errors path="hoTen" cssStyle="color: red"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="form-label" path="email">Email:</form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder="" path="email"/>
                                                    <form:errors path="email" cssStyle="color: red"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="form-label" path="sdt">Số điện thoại:</form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder="" path="sdt"/>
                                                    <form:errors path="sdt" cssStyle="color: red"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="form-label" path="ngaySinh">Ngày sinh:</form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder="" path="ngaySinh"
                                                                type="date"/>
                                                    <form:errors path="ngaySinh" cssStyle="color: red"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="form-label" path="taiKhoan">Tài khoản:</form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder="" path="taiKhoan"/>
                                                    <form:errors path="taiKhoan" cssStyle="color: red"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-group row">
                                                <form:label class="form-label" path="matKhau">Mật khẩu:</form:label>
                                                <div class="col-sm-9">
                                                    <form:input class="form-control" placeholder="" path="matKhau"/>
                                                    <form:errors path="matKhau" cssStyle="color: red"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group row">
                                                <form:label class="form-label" path="gioiTinh">Giới tính:</form:label>
                                                <div class="col-sm-12 form-control">
                                                    <form:radiobutton path="gioiTinh" value="true" checked="true"/>Nam
                                                    <form:radiobutton path="gioiTinh" value="false"/>Nữ
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div style="text-align: center">
                                                <button type="submit" class="btn btn-primary mr-2"
                                                        onclick="return myFunction1()">
                                                    ADD
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    function myFunction1() {
        let text = "Bạn chắc chắn muốn thêm";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction2() {
        let text = "Bạn chắc chắn muốn sửa";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction3() {
        let text = "Bạn chắc chắn muốn thay đổi trạng thái";
        let kt = confirm(text);
        if (kt == true) {
            confirm("Thay đổi trạng thái thành công");
            return true
        } else {
            return false;
        }
    }

    function myFunction4() {
        let text = "Bạn chắc chắn muốn về lại trang hiển thị";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction5() {
        let text = "Bạn chắc chắn muốn tìm kiếm thông tin";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction6() {
        let text = "Bạn chắc chắn muốn sang trang thêm thông tin";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }

    function myFunction7() {
        let text = "Bạn chắc chắn muốn sang trang thông tin đã thay đổi";
        let kt = confirm(text);
        if (kt == true) {
            return true
        } else {
            return false;
        }
    }
</script>
<script src="../../vendors/js/vendor.bundle.base.js"></script>
<script src="../../js/off-canvas.js"></script>
<script src="../../js/hoverable-collapse.js"></script>
<script src="../../js/template.js"></script>
<script src="../../js/settings.js"></script>
<script src="../../js/todolist.js"></script>
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