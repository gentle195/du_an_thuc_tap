package com.example.demo.controllers;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.KhachHang;
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
@RequestMapping("/khach-hang")
public class KhachHangController {
    @Autowired
    private KhachHangService khachHangService;

    @GetMapping("hien-thi")
    public String hienthi(@ModelAttribute("khachHang") KhachHang khachHang, Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<KhachHang> list = khachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        model.addAttribute("contentPage", "khach-hang/hien-thi.jsp");
        return "layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("khachHang") KhachHang khachHang,
                                @RequestParam("pageNum") Optional<Integer> num,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer size) {

        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<KhachHang> page = khachHangService.getAll1(pageable);
        model.addAttribute("contentPage", "khach-hang/view-trang-thai.jsp");
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("khachHang") KhachHang khachHang){
        model.addAttribute("khachHang",new KhachHang());
        model.addAttribute("contentPage","khach-hang/add.jsp");
        return "layout";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        KhachHang hsp = khachHangService.findById(id);
        model.addAttribute("khachHang", hsp);
        model.addAttribute("contentPage", "khach-hang/update.jsp");
        return "layout";
    }

    @PostMapping("/add")
    public String add(Model model,@Valid @ModelAttribute("khachHang") KhachHang khachHang ,BindingResult bindingResult,
                      @RequestParam("pageNum") Optional<Integer> num,
                      @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer size) {
        if (bindingResult.hasErrors()) {
            Sort sort = Sort.by("ngayTao").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<KhachHang> list = khachHangService.getAll(pageable);
            model.addAttribute("listKhachHang", list.getContent());
            model.addAttribute("total", list.getTotalPages());
            model.addAttribute("contentPage", "khach-hang/hien-thi.jsp");
            return "layout";
        }
        khachHang.setNgayTao(Date.valueOf(LocalDate.now()));
        khachHang.setTinhTrang(0);
        khachHang.setMa("KH" + String.valueOf(khachHangService.findAll().size() + 1));
        khachHangService.add(khachHang);
        return "redirect:/khach-hang/hien-thi";
        // Tiếp tục xử lý và trả về view tương ứng
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("khachHang") @Valid KhachHang khachHang,
                         BindingResult bindingResult, @PathVariable("id") UUID id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "khach-hang/update.jsp");
            return "layout";
        }
        KhachHang kh= khachHangService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        khachHang.setNgayCapNhat(date);
        khachHang.setNgayTao(kh.getNgayTao());
        khachHang.setMa(kh.getMa());
        khachHang.setTinhTrang(kh.getTinhTrang());
        khachHangService.update(id, khachHang);
        return "redirect:/khach-hang/hien-thi";
    }

    @GetMapping("/update-all")
    public String updateTT(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                           @ModelAttribute("khachHang") KhachHang khachHang) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        khachHang.setNgayCapNhat(date);
        khachHangService.updateTT();
        Page<KhachHang> page = khachHangService.getAll1(pageable);
        model.addAttribute("contentPage","khach-hang/view-trang-thai.jsp");
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("khachHang") KhachHang khachHang) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);

        KhachHang khachHang1 = khachHangService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        khachHang1.setNgayCapNhat(date);
        khachHang1.setTinhTrang(1);
        khachHangService.update(id,khachHang1);
        Page<KhachHang> page = khachHangService.getAll(pageable);
        model.addAttribute("contentPage","khach-hang/hien-thi.jsp");
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("khachHang") KhachHang khachHang) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        KhachHang khachHang1 = khachHangService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        khachHang1.setNgayCapNhat(date);

        khachHang1.setTinhTrang(0);
        khachHangService.update(id,khachHang1);
        Page<KhachHang> page = khachHangService.getAll1(pageable);
        model.addAttribute("contentPage","khach-hang/view-trang-thai.jsp");
        model.addAttribute("listKhachHang", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("khachHang") KhachHang khachHang, @RequestParam("search") String search, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Sort sort = Sort.by("ngayTao").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<KhachHang> list = khachHangService.getAll(pageable);
            model.addAttribute("listKhachHang", list.getContent());
            model.addAttribute("total", list.getTotalPages());
            model.addAttribute("contentPage", "khach-hang/hien-thi.jsp");
            return "layout";
        } else {
            List<KhachHang> list = khachHangService.search0(search);
            model.addAttribute("listKhachHang", list);
            model.addAttribute("contentPage", "khach-hang/hien-thi.jsp");
            return "layout";
        }

    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("khachHang") KhachHang khachHang, @RequestParam("search") String search, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Sort sort = Sort.by("ngayTao").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<KhachHang> list = khachHangService.getAll1(pageable);
            model.addAttribute("listKhachHang", list.getContent());
            model.addAttribute("total", list.getTotalPages());
            model.addAttribute("contentPage", "khach-hang/view-trang-thai.jsp");
            return "layout";
        } else {
            List<KhachHang> list = khachHangService.search1(search);
            model.addAttribute("listKhachHang", list);
            model.addAttribute("contentPage", "khach-hang/view-trang-thai.jsp");
            return "layout";
        }

    }

}
