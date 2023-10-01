package com.example.demo.controllers;

import com.example.demo.models.ChucVu;
import com.example.demo.models.KhachHang;
import com.example.demo.models.NhanVien;
import com.example.demo.services.ChucVuService;
import com.example.demo.services.NhanVienService;
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
@RequestMapping("/nhan-vien")
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    @Autowired
    private ChucVuService chucVuService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size",defaultValue = "5",required = false)Integer size){
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<NhanVien> list = nhanVienService.getAll(pageable);
        model.addAttribute("listNhanVien", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "nhan-vien/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model,@ModelAttribute("nhanVien") NhanVien nhanVien,@ModelAttribute("chucVu") ChucVu chucVu) {
        List<ChucVu> listChucVu = chucVuService.findAll();
        model.addAttribute("nhanVien", new NhanVien());
        model.addAttribute("listChucVu", listChucVu);
        return "nhan-vien/add";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id,@ModelAttribute("chucVu") ChucVu chucVu) {
        NhanVien hsp = nhanVienService.findById(id);
        model.addAttribute("nhanVien", hsp);
        return "nhan-vien/update";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "NhanVien") NhanVien nhanVien, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "nhan-vien/add";
        }
        String maNV = "NV" + nhanVienService.findAll().size() + 1;
        nhanVien.setMa(maNV);
        nhanVien.setNgayTao(Date.valueOf(LocalDate.now()));
        nhanVienService.add(nhanVien);
        return "redirect:/nhan-vien/hien-thi";
    }

    @PostMapping("/update/{id}")
    public String update(@ModelAttribute(name = "nhanVien") NhanVien nhanVien,
                         @ModelAttribute(name = "chucVu") ChucVu chucVu,
                         @PathVariable(name = "id") UUID id){
        NhanVien nv = nhanVienService.findById(id);
        nhanVien.setId(id);
        nhanVien.setMa(nv.getMa());
        nhanVien.setNgayTao(nv.getNgayTao());
        nhanVien.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        nhanVienService.update(id,nhanVien);
        return "redirect:/nhan-vien/hien-thi";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id) {
        nhanVienService.delete(id);
        return "redirect:/nhan-vien/hien-thi";
    }

    @PostMapping("/modal-add-nhan-vien")
    public String addKhachHang(@ModelAttribute("nhanVien") @Valid NhanVien nhanVien, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "nhan-vien/add";
        }
        String maKH = "KH" + (chucVuService.findAll().size() + 1);
        nhanVien.setMa(maKH);
        nhanVien.setNgayTao(Date.valueOf(LocalDate.now()));
        nhanVienService.add(nhanVien);
        return "redirect:/nhan-vien/view-add";
    }
    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("nhanVien")   NhanVien nhanVien, @RequestParam("search") String search) {
        List<NhanVien> list = nhanVienService.search(search);
        model.addAttribute("listNhanVien",list);
        return "nhan-vien/hien-thi";
    }
}
