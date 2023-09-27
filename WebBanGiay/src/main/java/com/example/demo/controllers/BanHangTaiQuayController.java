package com.example.demo.controllers;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.HoaDon;
import com.example.demo.models.HoaDonChiTiet;
import com.example.demo.models.KhachHang;
import com.example.demo.services.ChatLieuService;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.DeService;
import com.example.demo.services.DiaChiService;
import com.example.demo.services.HangSanPhamService;
import com.example.demo.services.HoaDonChiTietService;
import com.example.demo.services.HoaDonService;
import com.example.demo.services.KhachHangService;
import com.example.demo.services.MauSacService;
import com.example.demo.services.NhanVienService;
import com.example.demo.services.SanPhamService;
import com.example.demo.services.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/ban-hang")
public class BanHangTaiQuayController {
    @Autowired
    private HoaDonService hoaDonService;
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private KhachHangService khachHangService;
    @Autowired
    private HoaDonChiTietService hoaDonChiTietService;
    @Autowired
    private DiaChiService diaChiService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ChatLieuService chatLieuService;
    @Autowired
    private DeService deService;
    @Autowired
    private SanPhamService sanPhamService;
    @Autowired
    private MauSacService mauSacService;

    @Autowired
    private HangSanPhamService hangSanPhamService;

    private HoaDon hoaDonnn = new HoaDon();
    private BigDecimal total = BigDecimal.ZERO;

    @GetMapping("hien-thi")
    public String hienThi(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        hoaDonnn = null;
        List<HoaDon> list = hoaDonService.find();
        model.addAttribute("listHoaDon", list);
        model.addAttribute("listHang", hangSanPhamService.findAll());
        model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll());
        model.addAttribute("listNhanVien", nhanVienService.findAll());
        model.addAttribute("listKhachHang", khachHangService.findAll());
        model.addAttribute("listDiaChi", diaChiService.findAll());
        model.addAttribute("hoaDon", hoaDonnn);
        return "ban-hang/hien-thi";
    }

    @PostMapping("/add-hoa-don")
    public String addHoaDon(Model model, @ModelAttribute("hoaDon") HoaDon hoaDon) {
        List<HoaDon> list = hoaDonService.find();
        if (list.size() >= 3) {
            hoaDonnn = null;
            model.addAttribute("hoaDon", hoaDonnn);
            model.addAttribute("thongBaoHoaDon", "Đã quá số lượng hóa đơn chờ");
            model.addAttribute("listHoaDon", list);
            model.addAttribute("listHang", hangSanPhamService.findAll());
            model.addAttribute("listMauSac", mauSacService.findAll());
            model.addAttribute("listChiTietSanPham", chiTietSanPhamService.findAll());
            model.addAttribute("listNhanVien", nhanVienService.findAll());
            model.addAttribute("listKhachHang", khachHangService.findAll());
            model.addAttribute("listDiaChi", diaChiService.findAll());
            return "ban-hang/hien-thi";
        }
        HoaDon hd = new HoaDon();
        hd.setMa("HD" + String.valueOf(hoaDonService.findAll().size() + 1));
        hd.setTinhTrang(0);
        hd.setNgayTao(Date.valueOf(LocalDate.now()));
        hoaDonService.add(hd);
        return "redirect:/ban-hang/hien-thi";
    }


}
