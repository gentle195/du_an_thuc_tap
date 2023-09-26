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
    <title>Chi Tiết Sản Phẩm</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
</head>
<body>
<h4 style="text-align: center">Thông tin chi tiết sản phẩm</h4>
<br>
<div class="container">
    <table class="table container">
        <tbody>
        <tr>
            <form action="/chi-tiet-san-pham/search" method="post">
                <td colspan="2" style="text-align: center">Tìm kiếm: <input type="text" name="search">
                    <button type="submit">Tìm kiếm</button>
                </td>
            </form>
            <form action="/chi-tiet-san-pham/loc" method="post">
                <td colspan="2" style="text-align: center">
                    Lọc:
                    <select name="locSP">
                        <option value="null">Sản phẩm</option>
                        <c:forEach items="${listSP}" var="sp">
                            <option value="${sp.ten}">${sp.ten}</option>
                        </c:forEach>
                    </select>
                    <select name="locMS">
                        <option value="null">Màu sắc</option>
                        <c:forEach items="${listMS}" var="ms">
                            <option value="${ms.ten}">${ms.ten}</option>
                        </c:forEach>
                    </select>
                    <select name="locSize">
                        <option value="null">Size</option>
                        <c:forEach items="${listSize}" var="size">
                            <option value="${size.size}">${size.size}</option>
                        </c:forEach>
                    </select>
                    <select name="locDe">
                        <option value="null">Loại đế</option>
                        <c:forEach items="${listDe}" var="de">
                            <option value="${de.loaiDe}">${de.loaiDe}</option>
                        </c:forEach>
                    </select>
                    <button type="submit">Lọc</button>
                </td>
            </form>
            <td colspan="2" style="text-align: center">
                <button class="btn btn-info">
                    <a style="color: white;text-decoration: none" href="/chi-tiet-san-pham/view-add">Thêm mới</a>
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
            <th>Ảnh</th>
            <th>Tên sản phẩm</th>
            <th>Màu sắc</th>
            <th>Kích cỡ</th>
            <th>Loại đế</th>
            <th>Số lượng</th>
            <th>Giá nhập</th>
            <th>Giá bán</th>
            <th>Năm bảo hành</th>
            <th>Ngày tạo</th>
            <th>Ngày cập nhật</th>
            <th>Tình trạng</th>
            <th colspan="2">Chức năng</th>
        </tr>
        <c:forEach items="${listCTSP}" var="ctsp" varStatus="stt">
            <tr>
                <td>${stt.index+1}</td>
                <td>${ctsp.urlAnh}</td>
                <td>${ctsp.sanPham.ten}</td>
                <td>${ctsp.mauSac.ten}</td>
                <td>${ctsp.sizeGiay.size}</td>
                <td>${ctsp.de.loaiDe}</td>
                <td>${ctsp.soLuongTon}</td>
                <td>${ctsp.giaNhap}</td>
                <td>${ctsp.giaBan}</td>
                <td>${ctsp.namBaoHanh}</td>
                <td>${ctsp.ngayTao}</td>
                <td>${ctsp.ngayCapNhat}</td>
                <td>
                    <c:if test="${ctsp.tinhTrang==0}">Còn hàng</c:if>
                    <c:if test="${ctsp.tinhTrang==1}">Hết hàng</c:if>
                </td>

                <td colspan="2">
                    <a href="/chi-tiet-san-pham/delete/${ctsp.id}" class="btn btn-success"
                       onclick="return tbxd()">Delete</a>
                    <a href="/chi-tiet-san-pham/view-update/${ctsp.id}" class="btn btn-success"
                       onclick="return tbxd()">Update</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center pagination-lg">
            <li class="page-item"><a class="page-link" href="/chi-tiet-san-pham/hien-thi?num=0">First</a></li>

            <c:forEach begin="1" end="${total}" varStatus="status">
                <li class="page-item">
                    <a href="${pageContext.request.contextPath}/chi-tiet-san-pham/hien-thi?num=${status.index - 1}"
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