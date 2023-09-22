package com.example.demo.controllers;

import com.example.demo.models.SanPhamGiamGia;
import com.example.demo.services.SanPhamGiamGiaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/san-pham-giam-gia")
public class SanPhamGiamGiaController {
    @Autowired
    private SanPhamGiamGiaService sanPhamGiamGiaService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("num")Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size){
        Pageable pageable = PageRequest.of(num.orElse(0), size);
        Page<SanPhamGiamGia> list = sanPhamGiamGiaService.getAll(pageable);
        model.addAttribute("listSPGG", list.getContent());
        model.addAttribute("total", list.getTotalPages());

        return "san-pham-giam-gia/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("sanPhamGiamGia") SanPhamGiamGia sanPhamGiamGia){
        model.addAttribute("sanPhamGiamGia", new SanPhamGiamGia());
        return "san-pham-giam-gia/add";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id){
        SanPhamGiamGia spgg = sanPhamGiamGiaService.findById(id);
        model.addAttribute("sanPhamGiamGia", spgg);
        return "san-pham-giam-gia/update";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "sanPhamGiamGia") SanPhamGiamGia sanPhamGiamGia,
                      BindingResult result, Model model){
        if(result.hasErrors()){
            return "san-pham-giam-gia/add";
        }
        sanPhamGiamGiaService.add(sanPhamGiamGia);
        return "redirect:/san-pham-giam-gia/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute(name = "sanPhamGiamGia") SanPhamGiamGia sanPhamGiamGia, @PathVariable(name = "id") UUID id){
//        SanPhamGiamGia spgg = sanPhamGiamGiaService.findById(id);
        sanPhamGiamGia.setId(id);
        sanPhamGiamGiaService.update(id, sanPhamGiamGia);
        return "redirect:/san-pham-giam-gia/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id){
        sanPhamGiamGiaService.delete(id);
        return "redirect:/san-pham-giam-gia/hien-thi";
    }
}
