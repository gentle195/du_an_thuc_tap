package com.example.demo.controllers;

import com.example.demo.models.KhuyenMai;
import com.example.demo.services.KhuyenMaiService;
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
@RequestMapping("/khuyen-mai")
public class KhuyenMaiController {
    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<KhuyenMai> list = khuyenMaiService.getAll(pageable);
        model.addAttribute("listKM", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "khuyen-mai/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("khuyenMai") KhuyenMai khuyenMai){
        model.addAttribute("khuyenMai", new KhuyenMai());
        return "khuyen-mai/add";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id){
        KhuyenMai km = khuyenMaiService.findById(id);
        model.addAttribute("khuyenMai", km);
        return "khuyen-mai/update";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "khuyenMai") KhuyenMai khuyenMai, BindingResult result, Model model){
        if(result.hasErrors()){
            return "khuyen-mai/add";
        }

        String maKM = "KM" + (khuyenMaiService.findAll().size() + 1);
        khuyenMai.setMa(maKM);
        khuyenMai.setNgayTao(Date.valueOf(LocalDate.now()));
        khuyenMaiService.add(khuyenMai);
        return "redirect:/khuyen-mai/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute(name = "khuyenMai") KhuyenMai khuyenMai, @PathVariable(name = "id") UUID id){
        KhuyenMai km = khuyenMaiService.findById(id);
        khuyenMai.setId(id);
        khuyenMai.setMa(km.getMa());
        khuyenMai.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        khuyenMai.setNgayTao(km.getNgayTao());
        khuyenMaiService.update(id, khuyenMai);
        return "redirect:/khuyen-mai/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id){
        khuyenMaiService.delete(id);
        return "redirect:/khuyen-mai/hien-thi";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("khuyenMai") KhuyenMai khuyenMai, @RequestParam("search") String search){
        List<KhuyenMai> list = khuyenMaiService.search(search);
        model.addAttribute("listKM", list);
        return "khuyen-mai/hien-thi";
    }

}
