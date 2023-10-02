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
<h4 style="text-align: center">Thông tin nhân viên</h4>
<br>
<div class="container">
    <table class="table container">
        <tbody>
        <tr>
            <form action="/san-pham/search" method="post">
                <td colspan="2" style="text-align: center">Tìm kiếm: <input type="text" name="search">
                    <button type="submit">Tìm kiếm</button>
                </td>
            </form>
            <td colspan="2" style="text-align: center">
                <button class="btn btn-info">
                    <a style="color: white;text-decoration: none" href="/san-pham/view-add">Thêm mới</a>
                </button>
            </td>
        </tr>
        </tbody>
    </table>
</div>
</div>
<div class="container">
    <table class="table container">
        <tr>
            <th>STT</th>
            <th>Mã</th>
            <th>Tên</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhật</th>
            <th>Tình trạng</th>
            <th>Mô tả</th>
            <th>Ảnh</th>
            <th>Hãng sản phẩm</th>
            <th>Chất liệu</th>
            <th colspan="2">Chức năng</th>
        </tr>
        <c:forEach items="${listSanPham}" var="sanPham" varStatus="stt">
            <tr>
                <td>${stt.index+1}</td>
                <td>${sanPham.ma}</td>
                <td>${sanPham.hoTen} </td>
                <td>${sanPham.ngayTao}</td>
                <td>${sanPham.ngayCapNhat}</td>
                <td>
                    <c:if test="${sanPham.tinhTrang==0}">Sản phẩm mới</c:if>
                    <c:if test="${sanPham.tinhTrang==1}">Sản phẩm cũ</c:if>
                </td>
                <td>${sanPham.moTa}</td>
                <td>${sanPham.hinhAnh.ten}</td>
                <td>${sanPham.hangSanPham.ten}</td>
                <td>${sanPham.chatLieu.loaiChatLieu}</td>
                <td colspan="2">
                    <a href="/san-pham/delete/${nhanVien.id}" class="btn btn-success"
                       onclick="return tbxd()">Delete</a>
                    <a href="/san-pham/view-update/${nhanVien.id}" class="btn btn-success"
                       onclick="return tbxd()">Update</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pagination-lg">
            <li class="page-item"><a class="page-link" href="/san-pham/hien-thi?num=0">First</a></li>

            <c:forEach begin="1" end="${total}" varStatus="status">
                <li class="page-item">
                    <a href="${pageContext.request.contextPath}/san-pham/hien-thi?num=${status.index -1}"
                       class="page-link">${status.index}</a>
                </li>
            </c:forEach>

            <li class="page-item"><a class="page-link" href="/san-pham/hien-thi?num=${total-1}">Last</a></li>
        </ul>
    </nav>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</html>