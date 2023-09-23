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
<h4 style="text-align: center">Màu Sắc </h4>

<div class="container">
    <table class="table container">
        <tbody>
        <tr>
            <form action="/mau-sac/search" method="post">
                <td colspan="2" style="text-align: center">Tìm kiếm: <input type="text" name="search">
                    <button type="submit">Tìm kiếm</button>
                </td>
            </form>
            <td colspan="2" style="text-align: center">
                <button class="btn btn-info">
                    <a style="color: white;text-decoration: none" href="/mau-sac/view-add">Thêm mới</a>
                </button>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<div class="container">
    <table class="table container">
        <tr>
            <th>STT</th>
            <th>Mã</th>
            <th>Loại chất liệu </th>
            <th>Ngày tạo</th>
            <th>Ngày Ngày cập nhập</th>
            <th>Tình trạng</th>
            <th>Mô tả</th>
            <th>Chức năng</th>
        </tr>
        <c:forEach items="${listMauSac}" var="mauSac" varStatus="stt">
            <tr>
                <td>${stt.index+1}</td>
                <td>${mauSac.ma}</td>
                <td>${mauSac.loaiMauSac}</td>
                <td>${mauSac.ngayTao}</td>
                <td>${mauSac.ngayCapNhat}</td>

                <td>
                    <c:if test="${mauSac.tinhTrang==0}">Ngừng hoạt động</c:if>
                    <c:if test="${mauSac.tinhTrang==1}">Hoạt động</c:if>
                </td>
                <td>${mauSac.moTa}</td>
                <td>
                    <a href="/mau-sac/delete/${mauSac.id}" class="btn btn-success"
                       onclick="return tbxd()">Delete</a>
                    <a href="/mau-sac/view-update/${mauSac.id}" class="btn btn-success"
                       onclick="return tbxd()">Update</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pagination-lg">
            <li class="page-item"><a class="page-link" href="/mau-sac/hien-thi?num=0">First</a></li>

            <c:forEach begin="1" end="${total}" varStatus="status">
                <li class="page-item">
                    <a href="${pageContext.request.contextPath}/mau-sac/hien-thi?num=${status.index -1}"
                       class="page-link">${status.index}</a>
                </li>
            </c:forEach>

            <li class="page-item"><a class="page-link" href="/hang-san-pham/hien-thi?num=${total-1}">Last</a></li>
        </ul>
    </nav>
</div>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
</html>