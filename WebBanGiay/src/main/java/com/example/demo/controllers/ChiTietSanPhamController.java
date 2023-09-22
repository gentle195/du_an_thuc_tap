package com.example.demo.controllers;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.repositories.DeRepository;
import com.example.demo.repositories.MauSacRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.services.ChiTietSanPhamService;
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
@RequestMapping("/chi-tiet-san-pham")
public class ChiTietSanPhamController {
    @Autowired
    private ChiTietSanPhamService chiTietSanPhamService;

    @Autowired
    private SanPhamRepository sanPhamRepository;

    @Autowired
    private MauSacRepository mauSacRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private DeRepository deRepository;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("num") Optional<Integer> num, @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<ChiTietSanPham> list = chiTietSanPhamService.getAll(pageable);
        model.addAttribute("listCTSP", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "chi-tiet-san-pham/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham) {
        model.addAttribute("chiTietSanPham", new ChiTietSanPham());
        model.addAttribute("listSP", sanPhamRepository.findAll());
        model.addAttribute("listMS", mauSacRepository.findAll());
        model.addAttribute("listSize", sizeRepository.findAll());
        model.addAttribute("listDe", deRepository.findAll());
        return "chi-tiet-san-pham/add";
    }

    @GetMapping("/view-update/{id}")
    public String viewUpdate(Model model, @PathVariable("id") UUID id) {
        ChiTietSanPham ctsp = chiTietSanPhamService.findById(id);
        model.addAttribute("chiTietSanPham", ctsp);
        model.addAttribute("listSP", sanPhamRepository.findAll());
        model.addAttribute("listMS", mauSacRepository.findAll());
        model.addAttribute("listSize", sizeRepository.findAll());
        model.addAttribute("listDe", deRepository.findAll());
        return "chi-tiet-san-pham/update";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "chiTietSanPham") ChiTietSanPham chiTietSanPham, BindingResult result, Model model) {
        if(result.hasErrors()){
            model.addAttribute("listSP", sanPhamRepository.findAll());
            model.addAttribute("listMS", mauSacRepository.findAll());
            model.addAttribute("listSize", sizeRepository.findAll());
            model.addAttribute("listDe", deRepository.findAll());
            return "chi-tiet-san-pham/add";
        }

        chiTietSanPham.setNgayTao(Date.valueOf(LocalDate.now()));
        chiTietSanPhamService.add(chiTietSanPham);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute(name = "chiTietSanPham") ChiTietSanPham chiTietSanPham, @PathVariable(name = "id") UUID id){
        ChiTietSanPham ctsp = chiTietSanPhamService.findById(id);
        chiTietSanPham.setId(id);
        chiTietSanPham.setNgayTao(ctsp.getNgayTao());
        chiTietSanPham.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        chiTietSanPhamService.update(id, chiTietSanPham);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id){
        chiTietSanPhamService.delete(id);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, @RequestParam("search") String search){
        List<ChiTietSanPham> list = chiTietSanPhamService.search(search);
        model.addAttribute("listCTSP", list);
        return "chi-tiet-san-pham/hien-thi";
    }
}
