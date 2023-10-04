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

</head>
<body>
<h4 style="text-align: center">Thông tin sản phẩm</h4>
<div class="container">
    <form:form action="/san-pham/update/${sanPham.id}" method="post" modelAttribute="sanPham">
        <div class="row">
            <div class="col-6">
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="ten"/>
                    <form:label class="form-label" path="ten">Tên sản phẩm:</form:label>
                    <form:errors path="ten" cssStyle="color: red"/>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <form:input class="form-control" placeholder="" path="moTa"/>
                    <form:label class="form-label" path="moTa">Mô tả:</form:label>
                    <form:errors path="moTa" cssStyle="color: red"/>
                </div>
                <div class="form-check mb-3 mt-3">
                    <form:label class="form-label" path="tinhTrang">Tình Trạng:</form:label>
                    <form:radiobutton path="tinhTrang" value="0" checked="true"/>Sản phẩm mới
                    <form:radiobutton path="tinhTrang" value="1"/>Sản phẩm cũ
                </div>
            </div>
            <div class="col-6">
                <div class="form-floating mb-3 mt-3">
                    <div class="row">
                        <div class="col-10">
                            <form:select path="hangSanPham" class="form-control" id="selectHangSanPham"
                                         cssStyle="font-weight: bold; width: 100%">
                                <option selected disabled>Hãng Sản Phẩm</option>
                                <form:options items="${listHangSanPham}" itemLabel="ten" itemValue="id"/>
                            </form:select>
                        </div>
                        <div class="col-2">
                            <a class="btn btn-secondary" data-bs-toggle="modal"
                               data-bs-target="#exampleModalHangSanPham">
                                <img src="/uploads/plus.png">
                            </a>
                        </div>
                    </div>
                </div>
                <div class="form-floating mb-3 mt-3">
                    <div class="row">
                        <div class="col-10">
                            <form:select path="chatLieu" class="form-control" id="selectChatLieu"
                                         cssStyle="font-weight: bold; width: 100%">
                                <option selected disabled>Chất liệu</option>
                                <form:options items="${listChatLieu}" itemLabel="loaiChatLieu" itemValue="id"/>
                            </form:select>
                        </div>
                        <div class="col-2">
                            <a class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModalChatLieu">
                                <img src="/uploads/plus.png">
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-12" style="text-align: center">
                <button type="submit" class="btn btn-success">
                    Update
                </button>
            </div>
        </div>
    </form:form>
</div>
<div class="modal fade" id="exampleModalHangSanPham" tabindex="-1"
     aria-labelledby="exampleModalLabelHangSanPham"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div class="modal-body">
                <table class="table">
                    <form:form action="/hang-san-pham/add" method="post"
                               modelAttribute="hangSanPham">
                        <div class="row">
                            <div class="col-6">
                                <div class="form-floating mb-3 mt-3">
                                    <form:input class="form-control" placeholder=""
                                                path="ten"/>
                                    <form:label class="form-label"
                                                path="ten">Tên:</form:label>
                                    <form:errors path="ten" cssStyle="color: #ff0000"/>
                                </div>
                                <div class="form-floating mb-3 mt-3">
                                    <form:input class="form-control" placeholder=""
                                                path="moTa"/>
                                    <form:label class="form-label"
                                                path="moTa">Mô tả:</form:label>
                                    <form:errors path="moTa" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-floating mb-3 mt-3">
                                    <form:input class="form-control" placeholder=""
                                                path="xuatSu"/>
                                    <form:label class="form-label"
                                                path="xuatSu">Xuất xứ:</form:label>
                                    <form:errors path="xuatSu" cssStyle="color: red"/>
                                </div>
                                <div class="form-check mb-3 mt-3">
                                    <form:label class="form-label"
                                                path="tinhTrang">Tình Trạng:</form:label>
                                    <form:radiobutton path="tinhTrang" value="0"
                                                      checked="true"/>Hãng sản phẩm mới
                                    <form:radiobutton path="tinhTrang" value="1"/>Hãng sản
                                    phẩm
                                    cũ
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12" style="text-align: center">
                                <button type="submit" class="btn btn-primary mr-2"
                                        onclick="return myFunction1()">
                                    ADD
                                </button>
                            </div>
                        </div>
                    </form:form>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade" id="exampleModalChatLieu" tabindex="-1"
     aria-labelledby="exampleModalLabelChatLieu"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
```                <h1 class="modal-title fs-5" id="exampleModalLabelChatLieu">Chất Liệu</h1>
```            </div>
            <div class="modal-body">
                <table class="table">
                    <form:form action="/chat-lieu/add" method="post"
                               modelAttribute="chatLieu">
                        <div class="row">
                            <div class="col-6">
                                <div class="form-floating mb-3 mt-3">
                                    <form:input class="form-control" placeholder=""
                                                path="loaiChatLieu"/>
                                    <form:label class="form-label"
                                                path="loaiChatLieu">Loại chất liệu:</form:label>
                                    <form:errors path="loaiChatLieu"
                                                 cssStyle="color: #ff0000"/>
                                </div>
                                <div class="form-floating mb-3 mt-3">
                                    <form:input class="form-control" placeholder=""
                                                path="moTa"/>
                                    <form:label class="form-label"
                                                path="moTa">Mô tả:</form:label>
                                    <form:errors path="moTa" cssStyle="color: red"/>
                                </div>
                            </div>
                            <div class="col-6">
                                <div class="form-check mb-3 mt-3">
                                    <form:label class="form-label"
                                                path="tinhTrang">Tình Trạng:</form:label>
                                    <form:radiobutton path="tinhTrang" value="0"
                                                      checked="true"/>Chất
                                    liệu mới
                                    <form:radiobutton path="tinhTrang" value="1"/>Chất liệu
                                    cũ
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-12" style="text-align: center">
                                <button type="submit" class="btn btn-primary mr-2"
                                        onclick="return myFunction1()">
                                    ADD
                                </button>
                            </div>
                        </div>
                    </form:form>
                </table>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<%--                <div class="form-floating mb-3 mt-3">--%>
<%--                    <div class="row">--%>
<%--                        <div class="col-10">--%>
<%--                            <form:select path="hinhAnh" class="form-control" id="selectHinhAnh"--%>
<%--                                         cssStyle="font-weight: bold; width: 100%">--%>
<%--                                <option selected disabled>Hình Ảnh</option>--%>
<%--                                <form:options items="${listHinhAnh}" itemLabel="ten" itemValue="id"/>--%>
<%--                            </form:select>--%>
<%--                        </div>--%>
<%--                        <div class="col-2">--%>
<%--                            <a class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#exampleModalHinhAnh">--%>
<%--                                <img src="../img/plus.png">--%>
<%--                            </a>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script>
    $(document).ready(function () {
        $('#selectHinhAnh').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectHangSanPham').select2();
    });
</script>
<script>
    $(document).ready(function () {
        $('#selectChatLieu').select2();
    });
</script>
</html>