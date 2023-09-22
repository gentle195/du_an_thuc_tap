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
    <title>Khuyến Mãi</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h4 style="text-align: center">Thông tin khuyến mãi</h4>
<br>
<div class="container">
    <table class="table container">
        <tbody>
        <tr>
            <form action="/khuyen-mai/search" method="post">
                <td colspan="2" style="text-align: center">Tìm kiếm: <input type="text" name="search">
                    <button type="submit">Tìm kiếm</button>
                </td>
            </form>
            <td colspan="2" style="text-align: center">
                <button class="btn btn-info">
                    <a style="color: white;text-decoration: none" href="/khuyen-mai/view-add">Thêm mới</a>
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
                    <a href="/khuyen-mai/delete/${km.id}" class="btn btn-success"
                       onclick="return tbxd()">Delete</a>
                    <a href="/khuyen-mai/view-update/${km.id}" class="btn btn-success"
                       onclick="return tbxd()">Update</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pagination-lg">
            <li class="page-item"><a class="page-link" href="/khuyen-mai/hien-thi?num=0">First</a></li>

            <c:forEach begin="1" end="${total}" varStatus="status">
                <li class="page-item">
                    <a href="${pageContext.request.contextPath}/khuyen-mai/hien-thi?num=${status.index - 1}"
                       class="page-link">${status.index}</a>
                </li>
            </c:forEach>

            <li class="page-item"><a class="page-link" href="/khuyen-mai/hien-thi?num=${total-1}">Last</a></li>
        </ul>
    </nav>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</html>