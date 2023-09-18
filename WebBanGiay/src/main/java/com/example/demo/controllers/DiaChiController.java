package com.example.demo.controllers;

import com.example.demo.models.ChucVu;
import com.example.demo.models.DiaChi;
import com.example.demo.models.KhachHang;
import com.example.demo.models.MauSac;
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

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @ModelAttribute("DiaChi") DiaChi diaChi, @ModelAttribute("khachHang") KhachHang khachHang,
                          @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<DiaChi> list = diaChiService.getAll(pageable);
        model.addAttribute("listDiaChi", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "dia-chi/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("DiaChi") DiaChi diaChi, @ModelAttribute("khachHang") KhachHang khachHang) {
        List<KhachHang> listKhachHang = khachHangService.findAll();
        model.addAttribute("DiaChi", new DiaChi());
        model.addAttribute("listKhachHang", listKhachHang);
        return "dia-chi/add";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id, @ModelAttribute("khachHang") KhachHang khachHang) {
        DiaChi dc = diaChiService.findById(id);
        model.addAttribute("DiaChi", dc);
        return "dia-chi/update";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "DiaChi") DiaChi diaChi, BindingResult bindingResult,
                      @ModelAttribute("khachHang") KhachHang khachHang) {
        if (bindingResult.hasErrors()) {
            return "dia-chi/add";
        }

        String maDC = "DC" + (diaChiService.findAll().size() + 1);
        diaChi.setMa(maDC);
        diaChi.setNgayTao(Date.valueOf(LocalDate.now()));
        diaChiService.add(diaChi);
        return "redirect:/dia-chi/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute(name = "DiaChi") DiaChi diaChi,
                         @ModelAttribute(name = "khachHang") KhachHang khachHang,
                         @PathVariable(name = "id") UUID id) {
        DiaChi dc = diaChiService.findById(id);
        diaChi.setId(id);
        diaChi.setMa(dc.getMa());
        diaChi.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        diaChiService.update(id, diaChi);
        return "redirect:/dia-chi/hien-thi";
    }

    @PostMapping("/modal-add-khach-hang")
    public String addKhachHang(@ModelAttribute("khachHang") @Valid KhachHang khachHang, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "dia-chi/add";
        }
        String maKH = "KH" + (khachHangService.findAll().size() + 1);
        khachHang.setMa(maKH);
        khachHang.setNgayTao(Date.valueOf(LocalDate.now()));
        khachHangService.add(khachHang);
        return "redirect:/dia-chi/view-add";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id) {
        diaChiService.delete(id);
        return "redirect:/dia-chi/hien-thi";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("diaChi") DiaChi diaChi, @RequestParam("search") String search) {
        List<DiaChi> list=diaChiService.search(search);
        model.addAttribute("listDiaChi",list);
        return "dia-chi/hien-thi";
    }
}
