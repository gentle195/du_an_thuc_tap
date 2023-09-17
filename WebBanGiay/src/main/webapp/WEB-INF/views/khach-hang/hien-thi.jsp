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
    <div class="mb-3 mt-3 col-3">
        <button class="btn btn-info">
            <a style="color: white;text-decoration: none" href="/khach-hang/view-add">Thêm mới</a>
        </button>
    </div>
</div>
</div>
<div class="container">
    <table class="table-header container">
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
            <th>Chức năng</th>
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

                <td>
                    <a href="/khach-hang/delete/${khachHang.id}" class="btn btn-success"
                       onclick="return tbxd()">Delete</a>
                    <a href="/khach-hang/view-update/${khachHang.id}" class="btn btn-success"
                       onclick="return tbxd()">Update</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pagination-lg">
            <li class="page-item"><a class="page-link" href="/khach-hang/hien-thi?num=0">First</a></li>

            <c:forEach begin="1" end="${total}" varStatus="status">
                <li class="page-item">
                    <a href="${pageContext.request.contextPath}/khach-hang/hien-thi?num=${status.index -1}"
                       class="page-link">${status.index}</a>
                </li>
            </c:forEach>

            <li class="page-item"><a class="page-link" href="/khach-hang/hien-thi?num=${total-1}">Last</a></li>
        </ul>
    </nav>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</html>