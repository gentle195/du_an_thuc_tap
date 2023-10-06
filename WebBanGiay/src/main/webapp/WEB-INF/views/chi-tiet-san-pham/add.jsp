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
            <a class="nav-link" href="/chi-tiet-san-pham/hien-thi" role="tab" onclick="return myFunction4()">Thông tin chi tiết sản phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" id="description-tab" data-toggle="tab" href="#description" role="tab"
               aria-controls="description" aria-selected="true">Thêm chi tiết sản phẩm</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/chi-tiet-san-pham/hien-thi-delete" role="tab" onclick="return myFunction7()">Chi tiết sản phẩm đã
                xoá</a>
        </li>
    </ul>
</div>
<div>
    <div class="tab-content" id="myTabContent">
        <div class="tab-pane fade show active" id="description" role="tabpanel"
             aria-labelledby="description-tab">
            <%--@elvariable id="chiTietSanPham" type=""--%>
            <form:form action="/chi-tiet-san-pham/add" method="post" modelAttribute="chiTietSanPham">
                <div class="col-12 grid-margin">
                    <div class="card">
                        <div class="card-body">
                            <h4 class="card-title">Thêm chi tiết sản phẩm</h4>
                            <form class="form-sample">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-floating mb-3 mt-3">
                                            <form:select class="form-select" path="sanPham">
                                                <option>Sản phẩm</option>
                                                <form:options items="${listSP}" itemValue="id" itemLabel="ten"/>
                                            </form:select>
                                            <form:label class="form-label" path="sanPham">Tên sản phẩm:</form:label>
                                        </div>
                                        <div class="row mb-3 mt-3">
                                            <div class="col-11 form-floating">
                                                <form:select path="mauSac" class="form-select" id="selectMauSac">
                                                    <option selected disabled>Màu sắc</option>
                                                    <form:options items="${listMS}" itemLabel="ten" itemValue="id"/>
                                                </form:select>
                                            </div>
                                            <div class="col-1">
                                                <a data-bs-toggle="modal" data-bs-target="#exampleModalMauSac">
                                                    <img src="/uploads/plus.png">
                                                </a>
                                            </div>
                                        </div>

                                        <div class="row mb-3 mt-3">
                                            <div class="col-11 form-floating">
                                                <form:select path="sizeGiay" class="form-select" id="selectSize">
                                                    <option selected disabled>Kích cỡ</option>
                                                    <form:options items="${listSize}" itemLabel="size" itemValue="id"/>
                                                </form:select>
                                            </div>
                                            <div class="col-1">
                                                <a data-bs-toggle="modal" data-bs-target="#exampleModalSize">
                                                    <img src="/uploads/plus.png">
                                                </a>
                                            </div>
                                        </div>
                                        <div class="row mb-3 mt-3">
                                            <div class="col-11 form-floating">
                                                <form:select path="de" class="form-select" id="selectDe">
                                                    <option selected disabled>Loại đế</option>
                                                    <form:options items="${listDe}" itemLabel="loaiDe" itemValue="id"/>
                                                </form:select>
                                            </div>
                                            <div class="col-1">
                                                <a data-bs-toggle="modal" data-bs-target="#exampleModalDe">
                                                    <img src="/uploads/plus.png">
                                                </a>
                                            </div>
                                        </div>
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" placeholder="" path="soLuongTon"/>
                                            <form:label class="form-label" path="soLuongTon">Số lượng tồn:</form:label>
                                            <form:errors path="soLuongTon" cssStyle="color: red"/>
                                        </div>
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" placeholder="" path="namBaoHanh"/>
                                            <form:label class="form-label" path="namBaoHanh">Năm bảo hành:</form:label>
                                            <form:errors path="namBaoHanh" cssStyle="color: red"/>
                                        </div>
                                    </div>
                                    <div class="col-6">
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" placeholder="" path="giaNhap"/>
                                            <form:label class="form-label" path="giaNhap">Giá nhập:</form:label>
                                            <form:errors path="giaNhap" cssStyle="color: red"/>
                                        </div>
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" placeholder="" path="giaBan"/>
                                            <form:label class="form-label" path="giaBan">Giá bán:</form:label>
                                            <form:errors path="giaBan" cssStyle="color: #ff0000"/>
                                        </div>
                                        <div class="form-floating mb-3 mt-3">
                                            <form:textarea class="form-control" placeholder="" path="moTa" />
                                            <form:label class="form-label" path="moTa">Mô tả:</form:label>
                                            <form:errors path="moTa" cssStyle="color: #ff0000"/>
                                        </div>

                                        <div class="form-check mb-3 mt-3">
                                            <form:label class="form-label" path="tinhTrang">Tình Trạng:</form:label>
                                            <form:radiobutton path="tinhTrang" value="0" checked="true"/>Còn hàng
                                            <form:radiobutton path="tinhTrang" value="1"/>Hết hàng
                                        </div>
                                        <div class="form-floating mb-3 mt-3">
                                            <form:input class="form-control" placeholder="" path="urlAnh" type="file"/>
                                            <form:label class="form-label" path="urlAnh">Ảnh:</form:label>
                                                <%--                    <form:errors path="urlAnh" cssStyle="color: red"/>--%>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-12" style="text-align: center">
                                            <button type="submit" class="btn btn-success"
                                                    id="btt" onclick="return myFunction1()">Add
                                            </button>
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
<%--Them nhanh mau sac--%>
<div class="modal fade" id="exampleModalMauSac" tabindex="-1" aria-labelledby="exampleModalLabelMauSac"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabelMauSac">Màu Sắc</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="imeiListMauSac">
                    <table class="table">
                        <%--@elvariable id="mauSac" type=""--%>
                        <form:form action="/chi-tiet-san-pham/modal-add-mau-sac" method="post" modelAttribute="mauSac">
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-floating mb-3 mt-3">
                                        <form:input class="form-control" placeholder="" path="ten"/>
                                        <form:label class="form-label" path="ten">Tên màu sắc:</form:label>
                                        <form:errors path="ten" cssStyle="color: red"/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3 mt-3">
                                        <form:input class="form-control" placeholder="" path="moTa"/>
                                        <form:label class="form-label" path="moTa">Mô tả:</form:label>
                                        <form:errors path="moTa" cssStyle="color: red"/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-check mb-3 mt-3">
                                        <form:label class="form-label" path="tinhTrang">Tình Trạng:</form:label>
                                        <br>
                                        <form:radiobutton path="tinhTrang" value="0" checked="true"/>Hoạt động
                                        <br>
                                        <form:radiobutton path="tinhTrang" value="1"/>Ngưng hoạt động
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12" style="text-align: center">
                                        <button type="submit" class="btn btn-success"
                                                onclick="myFunction1()">Add
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<%--Them nhanh kich co--%>
<div class="modal fade" id="exampleModalSize" tabindex="-1" aria-labelledby="exampleModalLabelSize"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabelSize">Kích cỡ</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="imeiListSize">
                    <table class="table">
                        <%--@elvariable id="size" type=""--%>
                        <form:form action="/chi-tiet-san-pham/modal-add-size" method="post" modelAttribute="size">
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-floating mb-3 mt-3">
                                        <form:input class="form-control" placeholder="" path="size"/>
                                        <form:label class="form-label" path="size">Kích cỡ:</form:label>
                                        <form:errors path="size" cssStyle="color: red"/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3 mt-3">
                                        <form:input class="form-control" placeholder="" path="moTa"/>
                                        <form:label class="form-label" path="moTa">Mô tả:</form:label>
                                        <form:errors path="moTa" cssStyle="color: red"/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-check mb-3 mt-3">
                                        <form:label class="form-label" path="tinhTrang">Tình Trạng:</form:label>
                                        <br>
                                        <form:radiobutton path="tinhTrang" value="0" checked="true"/>Hoạt động
                                        <br>
                                        <form:radiobutton path="tinhTrang" value="1"/>Ngưng hoạt động
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12" style="text-align: center">
                                        <button type="submit" class="btn btn-success"
                                                onclick="myFunction1()">Add
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<%--Them nhanh de--%>
<div class="modal fade" id="exampleModalDe" tabindex="-1" aria-labelledby="exampleModalLabelDe"
     aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabelDe">Loại đế</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <div id="imeiListDe">
                    <table class="table">
                        <%--@elvariable id="de" type=""--%>
                        <form:form action="/chi-tiet-san-pham/modal-add-de" method="post" modelAttribute="de">
                            <div class="row">
                                <div class="col-6">
                                    <div class="form-floating mb-3 mt-3">
                                        <form:input class="form-control" placeholder="" path="loaiDe"/>
                                        <form:label class="form-label" path="loaiDe">Loại đế:</form:label>
                                        <form:errors path="loaiDe" cssStyle="color: red"/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-floating mb-3 mt-3">
                                        <form:input class="form-control" placeholder="" path="moTa"/>
                                        <form:label class="form-label" path="moTa">Mô tả:</form:label>
                                        <form:errors path="moTa" cssStyle="color: red"/>
                                    </div>
                                </div>
                                <div class="col-6">
                                    <div class="form-check mb-3 mt-3">
                                        <form:label class="form-label" path="tinhTrang">Tình Trạng:</form:label>
                                        <br>
                                        <form:radiobutton path="tinhTrang" value="0" checked="true"/>Hoạt động
                                        <br>
                                        <form:radiobutton path="tinhTrang" value="1"/>Ngưng hoạt động
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-12" style="text-align: center">
                                        <button type="submit" class="btn btn-success"
                                                onclick="myFunction1()">Add
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </form:form>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
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
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
        crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
<script>
    $(document).ready(function () {
        $('#selectMauSac').select2();
        $('#selectSize').select2();
        $('#selectDe').select2();
    });
</script>
</html>
