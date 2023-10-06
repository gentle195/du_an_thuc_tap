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
    public String hienThi(@ModelAttribute("khuyenMai") KhuyenMai khuyenMai, Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<KhuyenMai> list = khuyenMaiService.getAll(pageable);
        model.addAttribute("listKM", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        model.addAttribute("contentPage", "khuyen-mai/hien-thi.jsp");
        return "layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("khuyenMai") KhuyenMai khuyenMai,
                                @RequestParam("pageNum") Optional<Integer> num,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer size) {

        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<KhuyenMai> page = khuyenMaiService.getAll1(pageable);
        model.addAttribute("contentPage", "khuyen-mai/view-trang-thai.jsp");
        model.addAttribute("listKM", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("khuyenMai") KhuyenMai khuyenMai){
        model.addAttribute("khuyenMai", new KhuyenMai());
        model.addAttribute("contentPage","khuyen-mai/add.jsp");
        return "layout";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id){
        KhuyenMai km = khuyenMaiService.findById(id);
        model.addAttribute("khuyenMai", km);
        model.addAttribute("contentPage", "khuyen-mai/update.jsp");
        return "layout";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "khuyenMai") KhuyenMai khuyenMai, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("contentPage", "khuyen-mai/add.jsp");
            return "layout";
        }

        String maKM = "KM" + (khuyenMaiService.findAll().size() + 1);
        khuyenMai.setMa(maKM);
        khuyenMai.setTinhTrang(0);
        khuyenMai.setNgayTao(Date.valueOf(LocalDate.now()));
        khuyenMaiService.add(khuyenMai);
        return "redirect:/khuyen-mai/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(Model model, BindingResult result,@Valid @ModelAttribute(name = "khuyenMai") KhuyenMai khuyenMai, @PathVariable(name = "id") UUID id){
        if(result.hasErrors()){
            model.addAttribute("contentPage", "khuyen-mai/update.jsp");
            return "layout";
        }

        KhuyenMai km = khuyenMaiService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        khuyenMai.setId(id);
        khuyenMai.setMa(km.getMa());
        khuyenMai.setNgayCapNhat(date);
        khuyenMai.setNgayTao(km.getNgayTao());
        khuyenMai.setTinhTrang(km.getTinhTrang());
        khuyenMaiService.update(id, khuyenMai);
        return "redirect:/khuyen-mai/hien-thi";
    }

    @GetMapping("/update-all")
    public String updateTT(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                           @ModelAttribute("khuyenMai") KhuyenMai khuyenMai) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        khuyenMai.setNgayCapNhat(date);
        khuyenMaiService.updateTT();
        Page<KhuyenMai> page = khuyenMaiService.getAll1(pageable);
        model.addAttribute("contentPage","khuyen-mai/view-trang-thai.jsp");
        model.addAttribute("listKM", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("khuyenMai") KhuyenMai khuyenMai) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);

        KhuyenMai km = khuyenMaiService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        km.setNgayCapNhat(date);
        km.setTinhTrang(1);
        khuyenMaiService.update(id,km);
        Page<KhuyenMai> page = khuyenMaiService.getAll(pageable);
        model.addAttribute("contentPage","khuyen-mai/hien-thi.jsp");
        model.addAttribute("listKM", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("khuyenMai") KhuyenMai khuyenMai) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        KhuyenMai km = khuyenMaiService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        km.setNgayCapNhat(date);

        km.setTinhTrang(0);
        khuyenMaiService.update(id,km);
        Page<KhuyenMai> page = khuyenMaiService.getAll1(pageable);
        model.addAttribute("contentPage","khuyen-mai/view-trang-thai.jsp");
        model.addAttribute("listKM", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("khuyenMai") KhuyenMai khuyenMai, @RequestParam("search") String search,
                          @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size){
        if(search.isEmpty()){
            model.addAttribute("thongBao", "Không để trống thông tin");
            Sort sort = Sort.by("ngayTao").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<KhuyenMai> list = khuyenMaiService.getAll(pageable);
            model.addAttribute("listKM", list.getContent());
            model.addAttribute("total", list.getTotalPages());
            model.addAttribute("contentPage", "khuyen-mai/hien-thi.jsp");
            return "layout";
        }else{
            List<KhuyenMai> list = khuyenMaiService.search0(search);
            model.addAttribute("listKM", list);
            model.addAttribute("contentPage", "khuyen-mai/hien-thi.jsp");
            return "layout";
        }


    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("khuyenMai") KhuyenMai khuyenMai, @RequestParam("search") String search,
                          @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size){
        if(search.isEmpty()){
            model.addAttribute("thongBao", "Không để trống thông tin");
            Sort sort = Sort.by("ngayTao").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<KhuyenMai> list = khuyenMaiService.getAll1(pageable);
            model.addAttribute("listKM", list.getContent());
            model.addAttribute("total", list.getTotalPages());
            model.addAttribute("contentPage", "khuyen-mai/view-trang-thai.jsp");
            return "layout";
        }else{
            List<KhuyenMai> list = khuyenMaiService.search1(search);
            model.addAttribute("listKM", list);
            model.addAttribute("contentPage", "khuyen-mai/view-trang-thai.jsp");
            return "layout";
        }


    }

}
