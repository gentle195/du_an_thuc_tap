package com.example.demo.controllers;

import com.example.demo.models.ChucVu;
import com.example.demo.models.KhachHang;
import com.example.demo.models.NhanVien;
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

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<KhachHang> list = khachHangService.getAll(pageable);
        model.addAttribute("listKhachHang", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "khach-hang/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("khachHang") KhachHang khachHang) {
        model.addAttribute("khachHang", new KhachHang());
        return "khach-hang/add";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        KhachHang hsp = khachHangService.findById(id);
        model.addAttribute("khachHang", hsp);
        return "khach-hang/update";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "khachHang") KhachHang khachHang, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "khach-hang/add";
        }

        String maKH = "KH" + (khachHangService.findAll().size() + 1);
        khachHang.setMa(maKH);
        khachHang.setNgayTao(Date.valueOf(LocalDate.now()));
        khachHangService.add(khachHang);
        return "redirect:/khach-hang/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute(name = "khachHang") KhachHang khachHang,
                         @PathVariable(name = "id") UUID id) {
        KhachHang kh = khachHangService.findById(id);
        khachHang.setId(id);
        khachHang.setMa(kh.getMa());
        khachHang.setNgayTao(kh.getNgayTao());
        khachHang.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        khachHangService.update(id, khachHang);
        return "redirect:/khach-hang/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id) {
        khachHangService.delete(id);
        return "redirect:/khach-hang/hien-thi";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("khachHang") KhachHang khachHang, @RequestParam("search") String search) {
        List<KhachHang> list=khachHangService.search(search);
        model.addAttribute("listKhachHang",list);
        return "khach-hang/hien-thi";
    }

}
