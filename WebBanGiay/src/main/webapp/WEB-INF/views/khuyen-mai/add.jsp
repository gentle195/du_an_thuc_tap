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
    <link rel="stylesheet" href="../../vendors/feather/feather.css">
    <link rel="stylesheet" href="../../vendors/ti-icons/css/themify-icons.css">
    <link rel="stylesheet" href="../../vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="../../css/vertical-layout-light/style.css">
    <link rel="shortcut icon" href="../../images/favicon.png"/>
</head>
</head>
<body>
<div>
    <ul class="nav nav-tabs border-top" id="setting-panel" role="tablist">
        <li class="nav-item">
            <a class="nav-link" href="/khuyen-mai/hien-thi" role="tab" onclick="return myFunction4()">Thông tin khuyến mãi</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Thêm khuyến mãi</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/khuyen-mai/hien-thi-delete" role="tab" onclick="return myFunction7()">Khuyến mãi đã
                xoá</a>
        </li>
    </ul>
</div>
<div>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="description" role="tabpanel"
             aria-labelledby="description-tab">
            <%--@elvariable id="khuyenMai" type=""--%>
            <form:form action="/khuyen-mai/add" method="post" modelAttribute="khuyenMai">
                <div class="col-12 grid-margin">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Thêm khuyến mãi</h4>
                            <form class="form-sample">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" placeholder="" path="ten"/>
                                            <form:label class="form-label" path="ten">Tên chương trình:</form:label>
                                            <form:errors path="ten" cssStyle="color: red"/>
                                        </div>
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" placeholder="" path="ngayBatDau" type="date"/>
                                            <form:label class="form-label" path="ngayBatDau">Ngày bắt đầu:</form:label>
                                                <%--                    <form:errors path="ngayBatDau" cssStyle="color: red"/>--%>
                                        </div>
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" placeholder="" path="ngayKetThuc" type="date"/>
                                            <form:label class="form-label" path="ngayKetThuc">Ngay kết thúc</form:label>
                                                <%--                    <form:errors path="ngayKetThuc" cssStyle="color: red"/>--%>
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
                                        <div class="col-md-12">
                                            <div style="text-align: center">
                                                <button type="submit" class="btn btn-primary mr-2"
                                                        onclick="return myFunction1()">
                                                    ADD
                                                </button>
                                            </div>
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
