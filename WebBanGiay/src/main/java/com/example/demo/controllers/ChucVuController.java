package com.example.demo.controllers;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.ChucVu;
import com.example.demo.models.DiaChi;
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
        model.addAttribute("contentPage", "chuc-vu/hien-thi.jsp");
        return "layout";
    }
    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("ChucVu") ChucVu chucVu,
                                @RequestParam("pageNum") Optional<Integer> num,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer size) {

        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<ChucVu> page = chucVuService.getAll1(pageable);
        model.addAttribute("contentPage", "chuc-vu/view-trang-thai.jsp");
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model,@ModelAttribute("ChucVu") ChucVu chucVu) {
        model.addAttribute("chucVu", new ChucVu());
        model.addAttribute("contentPage", "chuc-vu/add.jsp");
        return "layout";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        ChucVu hsp = chucVuService.findById(id);
        model.addAttribute("chucVu", hsp);
        model.addAttribute("contentPage", "chuc-vu/update.jsp");
        return "layout";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "ChucVu") ChucVu chucVu, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "chuc-vu/add.jsp");
            return "layout";
        }

        String maCV = "CV" + chucVuService.findAll().size() + 1;
        chucVu.setMa(maCV);
        chucVu.setNgayTao(Date.valueOf(LocalDate.now()));
        chucVuService.add(chucVu);
        return "redirect:/chuc-vu/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(Model model,@ModelAttribute(name = "chucVu") ChucVu chucVu,
                         @PathVariable(name = "id") UUID id,BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "chuc-vu/update.jsp");
            return "layout";
        }
        ChucVu cVu = chucVuService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chucVu.setMa(cVu.getMa());
        chucVu.setNgayTao(cVu.getNgayTao());
        chucVu.setId(id);
        chucVu.setNgayCapNhat(date);
        chucVuService.update(id,chucVu);
        return "redirect:/chuc-vu/hien-thi";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("ChucVu") ChucVu chucVu) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        ChucVu chucVu1 = chucVuService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chucVu1.setNgayCapNhat(date);

        chucVu1.setTinhTrang(0);
        chucVuService.update(id, chucVu1);
        Page<ChucVu> page = chucVuService.getAll1(pageable);
        model.addAttribute("contentPage", "chuc-vu/view-trang-thai.jsp");
        model.addAttribute("listChucVu", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("ChucVu") ChucVu chucVu, @RequestParam("search") String search, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Sort sort = Sort.by("ngayTao").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<ChucVu> list = chucVuService.getAll(pageable);
            model.addAttribute("listChucVu", list.getContent());
            model.addAttribute("total", list.getTotalPages());
            model.addAttribute("contentPage", "chuc-vu/hien-thi.jsp");
            return "layout";
        } else {
            List<ChucVu> list = chucVuService.search0(search);
            model.addAttribute("listChucVu", list);
            model.addAttribute("contentPage", "chuc-vu/hien-thi.jsp");
            return "layout";
        }

    }

    @PostMapping("/search-1")
    public String search1(Model model,  @ModelAttribute("ChucVu") ChucVu chucVu, @RequestParam("search") String search, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Sort sort = Sort.by("ngayTao").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<ChucVu> list = chucVuService.getAll1(pageable);
            model.addAttribute("listChucVu", list.getContent());
            model.addAttribute("total", list.getTotalPages());
            model.addAttribute("contentPage", "chuc-vu/view-trang-thai.jsp");
            return "layout";
        } else {
            List<ChucVu> list = chucVuService.search1(search);
            model.addAttribute("listChucVu", list);
            model.addAttribute("contentPage", "chuc-vu/view-trang-thai.jsp");
            return "layout";
        }

    }
}
