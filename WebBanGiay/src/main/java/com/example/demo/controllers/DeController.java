package com.example.demo.controllers;

import com.example.demo.models.De;
import com.example.demo.models.MauSac;
import com.example.demo.services.DeService;
import com.example.demo.services.MauSacService;
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
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/de")
public class DeController {
    @Autowired
    private DeService deService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<De> list = deService.getAll(pageable);
        model.addAttribute("listDe", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "de/hien-thi";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("De") De de ){
        model.addAttribute("de",new De());
        return "de/add";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "De") De de , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "de/add";
        }
        String maDe = "De" + (deService.findAll().size()+1);
        de.setMa(maDe);
        de.setNgayTao(Date.valueOf(LocalDate.now()));
        deService.add(de);
        return "redirect:/de/hien-thi";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id){
        deService.delete(id);
        return "redirect:/de/hien-thi";
    }
    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id){
        De de = deService.findById(id);
        model.addAttribute("de",de);
        return "de/update";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute(name = "de") De de,
                         @PathVariable(name = "id") UUID id){
        De deUd = deService.findById(id);
        de.setId(id);
        de.setMa(deUd.getMa());
        de.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        deService.update(id,de);
        return "redirect:/de/hien-thi";
    }
    @PostMapping("/search")
    public String search (Model model, @ModelAttribute("de") De de , @RequestParam("search") String search){
        List<De> list = deService.search(search);
        model.addAttribute("listDe",list);
        return "de/hien-thi";
    }

}

