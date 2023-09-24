package com.example.demo.controllers;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.ChucVu;
import com.example.demo.services.ChucVuService;
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
@RequestMapping("/chuc-vu")
public class ChucVuController {
    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size",defaultValue = "5",required = false)Integer size){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<ChucVu> list = chucVuService.getAll(pageable);
        model.addAttribute("listChucVu", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "chuc-vu/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model,@ModelAttribute("ChucVu") ChucVu chucVu) {
        model.addAttribute("chucVu", new ChucVu());
        return "chuc-vu/add";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        ChucVu hsp = chucVuService.findById(id);
        model.addAttribute("chucVu", hsp);
        return "chuc-vu/update";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "ChucVu") ChucVu chucVu, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "chuc-vu/add";
        }

        String maCV = "CV" + chucVuService.findAll().size() + 1;
        chucVu.setMa(maCV);
        chucVu.setNgayTao(Date.valueOf(LocalDate.now()));
        chucVuService.add(chucVu);
        return "redirect:/chuc-vu/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute(name = "chucVu") ChucVu chucVu,
                         @PathVariable(name = "id") UUID id){
        ChucVu cVu = chucVuService.findById(id);
        chucVu.setMa(cVu.getMa());
        chucVu.setNgayTao(cVu.getNgayTao());
        chucVu.setId(id);
        chucVu.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        chucVuService.update(id,chucVu);
        return "redirect:/chuc-vu/hien-thi";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id) {
        chucVuService.delete(id);
        return "redirect:/chuc-vu/hien-thi";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("chucVu")   ChucVu chucVu, @RequestParam("search") String search) {
        List<ChucVu> list = chucVuService.search(search);
        model.addAttribute("listChucVu",list);
        return "chuc-vu/hien-thi";
    }
}
