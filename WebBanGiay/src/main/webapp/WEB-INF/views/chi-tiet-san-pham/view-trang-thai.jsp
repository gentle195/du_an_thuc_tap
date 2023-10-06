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
            <a class="nav-link" href="/chi-tiet-san-pham/hien-thi" role="tab" onclick="return myFunction4()">Thông tin chi tiết sản phẩm </a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/chi-tiet-san-pham/view-add" role="tab" onclick="return myFunction6()">Thêm chi tiết sản phẩm </a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Chi tiết sản phẩm đã xoá</a>
        </li>
        <a href="/chi-tiet-san-pham/update-all" class="btn btn-outline-danger btn-icon-text"
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
                        <h4 class="card-title" style="float: left">Danh sách chi tiết sản phẩm</h4>
                        <%--            Tìm kiếm               --%>
                        <table class="table container">
                            <tbody>
                            <tr>
                                <form action="/chi-tiet-san-pham/search1" method="post">
                                    <td colspan="2" style="text-align: center">Tìm kiếm: <input type="text" name="search">
                                        <button type="submit">Tìm kiếm</button>
                                    </td>
                                </form>
                                <form action="/chi-tiet-san-pham/loc1" method="post">
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
                            </tr>
                            </tbody>
                        </table>

                        <%--           kết thúc tìm kiếm         --%>
                        <div class="table-responsive">
                            <table class="table table-striped">
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
                                            <a class="btn btn-warning btn-icon-text"
                                               href="/chi-tiet-san-pham/view-update/${ctsp.id}"
                                               onclick="return myFunction2()">
                                                <i class="ti-file btn-icon-prepend"></i>
                                                Update</a>
                                            <a class="btn btn-danger btn-icon-text"
                                               href="/chi-tiet-san-pham/update-status/${ctsp.id}"
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
                            <li class="page-item"><a class="page-link" href="/chi-tiet-san-pham/hien-thi?num=0">First</a></li>

                            <c:forEach begin="1" end="${total}" varStatus="status">
                                <li class="page-item">
                                    <a href="${pageContext.request.contextPath}/chi-tiet-san-pham/hien-thi?num=${status.index - 1}"
                                       class="page-link">${status.index}</a>
                                </li>
                            </c:forEach>

                            <li class="page-item"><a class="page-link" href="/chi-tiet-san-pham/hien-thi?num=${total-1}">Last</a></li>
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