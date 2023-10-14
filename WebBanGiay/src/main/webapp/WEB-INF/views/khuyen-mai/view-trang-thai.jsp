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
            <a class="nav-link" href="/khuyen-mai/hien-thi" role="tab" onclick="return myFunction4()">Thông tin khuyến mãi</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/khuyen-mai/view-add" role="tab" onclick="return myFunction6()">Thêm khuyến mãi</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Khuyến mãi đã xoá</a>
        </li>
        <a href="/khuyen-mai/update-all" class="btn btn-outline-danger btn-icon-text"
           style="float: right; margin-left: 720px"
           tabindex="-1"
           role="button"
           onclick="if(!(confirm('Bạn có muốn thực hiện thao tác này không ? ')))return false;">
            <i class="ti-reload btn-icon-prepend"></i>
            Status All
        </a>
    </ul>
</div>
<div>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="description" role="tabpanel"
             aria-labelledby="description-tab">
            <div class="col-lg-12 grid-margin stretch-card">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title" style="float: left">Danh sách khuyến mãi</h4>
                        <%--            Tìm kiếm               --%>
                        <form action="/khuyen-mai/search-1" method="post">
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
                                    <th>Tên CT</th>
                                    <th>Ngày bắt đầu</th>
                                    <th>Ngày kết thúc</th>
                                    <th>Mức</th>
                                    <th>Loại</th>
                                    <th>Hình thức</th>
                                    <th>Ngày tạo</th>
                                    <th>Ngày cập nhật</th>
                                    <th>Tình trạng</th>
                                    <th colspan="2">Chức năng</th>
                                </tr>
                                <c:forEach items="${listKM}" var="km" varStatus="stt">
                                    <tr>
                                        <td>${stt.index+1}</td>
                                        <td>${km.ma}</td>
                                        <td>${km.ten} </td>
                                        <td>${km.ngayBatDau}</td>
                                        <td>${km.ngayKetThuc}</td>
                                        <td>${km.soTienGiam}</td>
                                        <td>${km.loaiGiamGia}</td>
                                        <td>${km.hinhThucGiamGia}</td>
                                        <td>${km.ngayTao}</td>
                                        <td>${km.ngayCapNhat}</td>
                                        <td>
                                            <c:if test="${km.tinhTrang==0}">Còn chương trình</c:if>
                                            <c:if test="${km.tinhTrang==1}">Hết chương trình</c:if>
                                        </td>
                                        <td colspan="2">
                                            <a class="btn btn-warning btn-icon-text"
                                               href="/khuyen-mai/view-update/${km.id}"
                                               onclick="return myFunction2()">
                                                <i class="ti-file btn-icon-prepend"></i>
                                                Update</a>
                                            <a class="btn btn-danger btn-icon-text"
                                               href="/khuyen-mai/update-status/${km.id}"
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
                            <li class="page-item"><a class="page-link" href="/khuyen-mai/hien-thi?num=0">First</a></li>
                            <c:forEach begin="1" end="${total}" varStatus="status">
                                <li class="page-item">
                                    <a href="${pageContext.request.contextPath}/khuyen-mai/hien-thi?num=${status.index -1}"
                                       class="page-link">${status.index}</a>
                                </li>
                            </c:forEach>
                            <li class="page-item"><a class="page-link"
                                                     href="/khuyen-mai/hien-thi?num=${total-1}">Last</a></li>
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