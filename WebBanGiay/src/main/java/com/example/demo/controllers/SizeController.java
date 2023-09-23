package com.example.demo.controllers;

import com.example.demo.models.De;
import com.example.demo.models.Size;
import com.example.demo.services.SizeService;
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
@RequestMapping("/size")
public class SizeController {
    @Autowired
    private SizeService sizeService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<Size> list = sizeService.getAll(pageable);
        model.addAttribute("listSize", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "size/hien-thi";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("Size") Size size ){
        model.addAttribute("size",new Size());
        return "size/add";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "Size") Size size , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "size/add";
        }
        String maSize  = "Size" + (sizeService.findAll().size()+1);
        size.setMa(maSize);
        size.setNgayTao(Date.valueOf(LocalDate.now()));
        sizeService.add(size);
        return "redirect:/size/hien-thi";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id){
        sizeService.delete(id);
        return "redirect:/size/hien-thi";
    }
    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id){
        Size size = sizeService.findById(id);
        model.addAttribute("size",size);
        return "size/update";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute(name = "size") Size size,
                         @PathVariable(name = "id") UUID id){
        Size sizeUd = sizeService.findById(id);
        size.setId(id);
        size.setMa(sizeUd.getMa());
        size.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        sizeService.update(id,size);
        return "redirect:/size/hien-thi";
    }
    @PostMapping("/search")
    public String search (Model model, @ModelAttribute("size") Size size , @RequestParam("search") String search){
        List<Size> list = sizeService.search(search);
        model.addAttribute("list",list);
        return "size/hien-thi";
    }
}
