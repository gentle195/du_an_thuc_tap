package com.example.demo.controllers;

import com.example.demo.models.DiaChi;
import com.example.demo.models.KhachHang;
import com.example.demo.services.DiaChiService;
import com.example.demo.services.KhachHangService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/dia-chi")
public class DiaChiController {
    @Autowired
    private DiaChiService diaChiService;

    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("hien-thi")
    public String hienthi(@ModelAttribute("DiaChi") DiaChi diaChi, Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getAll(pageable);
        model.addAttribute("listDiaChi", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        model.addAttribute("contentPage", "dia-chi/hien-thi.jsp");
        return "layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("DiaChi") DiaChi diaChi,
                                @RequestParam("pageNum") Optional<Integer> num,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer size) {

        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> page = diaChiService.getAll1(pageable);
        model.addAttribute("contentPage", "dia-chi/view-trang-thai.jsp");
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("DiaChi") DiaChi diaChi, @ModelAttribute("khachHang") KhachHang khachHang) {
        model.addAttribute("listKhachHang", khachHangService.findAll0());
        model.addAttribute("DiaChi", new DiaChi());
        model.addAttribute("contentPage", "dia-chi/add.jsp");
        return "layout";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("khachHang") KhachHang khachHang, @ModelAttribute("DiaChi") DiaChi diaChi) {
        model.addAttribute("listKhachHang", khachHangService.findAll0());
        DiaChi hsp = diaChiService.findById(id);
        model.addAttribute("DiaChi", hsp);
        model.addAttribute("contentPage", "dia-chi/update.jsp");
        return "layout";
    }

    @PostMapping("/add")
    public String add(Model model, @Valid @ModelAttribute("DiaChi") DiaChi diaChi, @ModelAttribute("khachHang") KhachHang khachHang, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listKhachHang", khachHangService.findAll0());
            model.addAttribute("contentPage", "dia-chi/add.jsp");
            return "layout";
        }
        diaChi.setNgayTao(Date.valueOf(LocalDate.now()));
        diaChi.setTinhTrang(0);
        diaChi.setMa("DC" + String.valueOf(diaChiService.findAll().size() + 1));
        diaChiService.add(diaChi);
        return "redirect:/dia-chi/hien-thi";
        // Tiếp tục xử lý và trả về view tương ứng
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("DiaChi") @Valid DiaChi diaChi,
                         BindingResult bindingResult, @PathVariable("id") UUID id, @ModelAttribute("khachHang") KhachHang khachHang) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("listKhachHang", khachHangService.findAll0());
            model.addAttribute("contentPage", "dia-chi/update.jsp");
            return "layout";
        }
        DiaChi dc = diaChiService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        diaChi.setNgayCapNhat(date);
        diaChi.setNgayTao(dc.getNgayTao());
        diaChi.setMa(dc.getMa());
        diaChi.setTinhTrang(dc.getTinhTrang());
        diaChiService.update(id, diaChi);
        return "redirect:/dia-chi/hien-thi";
    }

    @GetMapping("/update-all")
    public String updateTT(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                           @ModelAttribute("DiaChi") DiaChi diaChi) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        diaChi.setNgayCapNhat(date);
        diaChiService.updateTT();
        Page<DiaChi> page = diaChiService.getAll1(pageable);
        model.addAttribute("contentPage", "dia-chi/view-trang-thai.jsp");
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("DiaChi") DiaChi diaChi) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);

        DiaChi diaChi1 = diaChiService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        diaChi1.setNgayCapNhat(date);
        diaChi1.setTinhTrang(1);
        diaChiService.update(id, diaChi1);
        Page<DiaChi> page = diaChiService.getAll(pageable);
        model.addAttribute("contentPage", "dia-chi/hien-thi.jsp");
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("DiaChi") DiaChi diaChi) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        DiaChi diaChi1 = diaChiService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        diaChi1.setNgayCapNhat(date);

        diaChi1.setTinhTrang(0);
        diaChiService.update(id, diaChi1);
        Page<DiaChi> page = diaChiService.getAll1(pageable);
        model.addAttribute("contentPage", "dia-chi/view-trang-thai.jsp");
        model.addAttribute("listDiaChi", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("DiaChi") DiaChi diaChi, @RequestParam("search") String search, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Sort sort = Sort.by("ngayTao").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<DiaChi> list = diaChiService.getAll(pageable);
            model.addAttribute("listDiaChi", list.getContent());
            model.addAttribute("total", list.getTotalPages());
            model.addAttribute("contentPage", "dia-chi/hien-thi.jsp");
            return "layout";
        } else {
            List<DiaChi> list = diaChiService.search0(search);
            model.addAttribute("listDiaChi", list);
            model.addAttribute("contentPage", "dia-chi/hien-thi.jsp");
            return "layout";
        }

    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("DiaChi") DiaChi diaChi, @RequestParam("search") String search, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Sort sort = Sort.by("ngayTao").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<DiaChi> list = diaChiService.getAll1(pageable);
            model.addAttribute("listDiaChi", list.getContent());
            model.addAttribute("total", list.getTotalPages());
            model.addAttribute("contentPage", "dia-chi/view-trang-thai.jsp");
            return "layout";
        } else {
            List<DiaChi> list = diaChiService.search1(search);
            model.addAttribute("listDiaChi", list);
            model.addAttribute("contentPage", "dia-chi/view-trang-thai.jsp");
            return "layout";
        }

    }

    @PostMapping("/add-modal-khach-hang")
    public String add(Model model, @Valid @ModelAttribute("khachHang") KhachHang khachHang, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "dia-chi/add.jsp");
            return "layout";
        }
        khachHang.setNgayTao(Date.valueOf(LocalDate.now()));
        khachHang.setTinhTrang(0);
        khachHang.setMa("KH" + String.valueOf(khachHangService.findAll().size() + 1));
        khachHangService.add(khachHang);
        return "redirect:/dia-chi/view-add";
        // Tiếp tục xử lý và trả về view tương ứng
    }
}
