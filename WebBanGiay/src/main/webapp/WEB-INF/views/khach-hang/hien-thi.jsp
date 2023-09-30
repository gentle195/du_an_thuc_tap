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
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Thông tin khách hàng</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/khach-hang/hien-thi-delete" role="tab" onclick="return myFunction7()">Khách hàng
                đã xoá</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/khach-hang/view-add" role="tab"  onclick="return myFunction6()">Thêm khách hàng</a>
        </li>
    </ul>
</div>
<div>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="description" role="tabpanel"
             aria-labelledby="description-tab">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title" style="float: left">Danh sách Khách hàng</h4>
                        <%--            Tìm kiếm               --%>
                        <form action="/khach-hang/search-0" method="post">
                            <div class="row">
                                <div class="col-8">
                                    <h6 style="float: right; margin: 14px;color: red">${thongBao}</h6></div>
                                <div class="col-4">
                                    <div class="input-group" style="width: 100%; float: left">
                                        <input type="text" class="form-control" placeholder="Bạn tìm gì..."
                                               aria-label="Bạn tìm gì..." name="search">
                                        <div class="input-group-append">
                                            <button class="btn btn-sm btn-primary" type="submit"
                                                    onclick="return myFunction5()">Search
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <%--           kết thúc tìm kiếm         --%>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <tr>
                                    <th>STT</th>
                                    <th>Mã</th>
                                    <th>Họ tên</th>
                                    <th>Giới tính</th>
                                    <th>Email</th>
                                    <th>SDT</th>
                                    <th>Ngày sinh</th>
                                    <th>Tài khoản</th>
                                    <th>Mật khẩu</th>
                                    <th>Ngày tạo</th>
                                    <th>Ngày cập nhật</th>
                                    <th>Tình trạng</th>
                                    <th colspan="2">Chức năng</th>
                                </tr>
                                <c:forEach items="${listKhachHang}" var="khachHang" varStatus="stt">
                                    <tr>
                                        <td>${stt.index+1}</td>
                                        <td>${khachHang.ma}</td>
                                        <td>${khachHang.hoTen} </td>
                                        <td>
                                            <c:if test="${khachHang.gioiTinh==true}">Nam</c:if>
                                            <c:if test="${khachHang.gioiTinh==false}">Nữ</c:if>
                                        </td>
                                        <td>${khachHang.email}</td>
                                        <td>${khachHang.sdt}</td>
                                        <td>${khachHang.ngaySinh}</td>
                                        <td>${khachHang.taiKhoan}</td>
                                        <td>${khachHang.matKhau}</td>
                                        <td>${khachHang.ngayTao}</td>
                                        <td>${khachHang.ngayCapNhat}</td>
                                        <td>
                                            <c:if test="${khachHang.tinhTrang==0}">Khách hàng mới</c:if>
                                            <c:if test="${khachHang.tinhTrang==1}">Khách hàng cũ</c:if>
                                        </td>

                                        <td colspan="2">
                                            <a class="btn btn-warning btn-icon-text"
                                               href="/khach-hang/view-update/${khachHang.id}"
                                               onclick="return myFunction2()">
                                                <i class="ti-file btn-icon-prepend"></i>
                                                Update</a>
                                            <a class="btn btn-danger btn-icon-text"
                                               href="/khach-hang/update-status/${khachHang.id}"
                                               onclick="return myFunction3()"><i class="ti-reload btn-icon-prepend"></i>
                                                Status</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
            <div align="center">
                <div class="btn-group" role="group" aria-label="Basic example">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-center pagination-lg">
                            <li class="page-item"><a class="page-link" href="/khach-hang/hien-thi?num=0">First</a></li>

                            <c:forEach begin="1" end="${total}" varStatus="status">
                                <li class="page-item">
                                    <a href="${pageContext.request.contextPath}/khach-hang/hien-thi?num=${status.index -1}"
                                       class="page-link">${status.index}</a>
                                </li>
                            </c:forEach>

                            <li class="page-item"><a class="page-link"
                                                     href="/khach-hang/hien-thi?num=${total-1}">Last</a></li>
                        </ul>
                    </nav>
                </div>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</html>