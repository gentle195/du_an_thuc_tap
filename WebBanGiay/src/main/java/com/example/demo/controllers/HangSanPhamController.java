package com.example.demo.controllers;


import com.example.demo.models.HangSanPham;
import com.example.demo.models.KhachHang;
import com.example.demo.services.HangSanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/hang-san-pham")
public class HangSanPhamController {
    @Autowired
    private HangSanPhamService hangSanPhamService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<HangSanPham> list = hangSanPhamService.getAll(pageable);
        model.addAttribute("listHangSanPham", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "hang-san-pham/hien-thi";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model , @ModelAttribute("HangSanPham") HangSanPham hangSanPham){
        model.addAttribute("HangSanPham", new HangSanPham());
        return "hang-san-pham/add";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "HangSanPham") HangSanPham hangSanPham , BindingResult bindingResult ){
        if(bindingResult.hasErrors()){
            return "hang-san-pham/add";
        }
        String maHSP = "HSP" + (hangSanPhamService.findAll().size() +1);
        hangSanPham.setMa(maHSP);
        hangSanPham.setNgayTao(Date.valueOf(LocalDate.now()));
        hangSanPhamService.add(hangSanPham);
        return "redirect:/hang-san-pham/hien-thi";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id){
        hangSanPhamService.delete(id);
        return "redirect:/hang-san-pham/hien-thi";
    }
    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id){
        HangSanPham hangSanPham = hangSanPhamService.findById(id);
        model.addAttribute("hangSanPham", hangSanPham);
        return "hang-san-pham/update";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute(name = "hangSanPham") HangSanPham hangSanPham,
                         @PathVariable(name = "id")UUID id){
        HangSanPham hangSanPhamupdate = hangSanPhamService.findById(id);
        hangSanPham.setId(id);
        hangSanPham.setMa(hangSanPhamupdate.getMa());
        hangSanPham.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        hangSanPhamService.update(id, hangSanPham);
        return "redirect:/hang-san-pham/hien-thi";
    }
}
