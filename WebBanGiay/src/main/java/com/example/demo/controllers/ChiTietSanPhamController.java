package com.example.demo.controllers;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.De;
import com.example.demo.models.MauSac;
import com.example.demo.models.Size;
import com.example.demo.repositories.DeRepository;
import com.example.demo.repositories.MauSacRepository;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.repositories.SizeRepository;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.DeService;
import com.example.demo.services.MauSacService;
import com.example.demo.services.SanPhamService;
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
    private MauSacService mauSacService;

    @Autowired
    private SizeService sizeService;

    @Autowired
    private DeService deService;

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("num") Optional<Integer> num, @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        model.addAttribute("listSP", sanPhamRepository.findAll());
        model.addAttribute("listMS", mauSacService.findAll());
        model.addAttribute("listSize", sizeService.findAll());
        model.addAttribute("listDe", deService.findAll());

        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<ChiTietSanPham> list = chiTietSanPhamService.getAll(pageable);
        model.addAttribute("listCTSP", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "chi-tiet-san-pham/hien-thi";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham,
                          @ModelAttribute("mauSac") MauSac mauSac,
                          @ModelAttribute("size") Size size,
                          @ModelAttribute("de") De de) {
        model.addAttribute("chiTietSanPham", new ChiTietSanPham());
        model.addAttribute("listSP", sanPhamRepository.findAll());
        model.addAttribute("listMS", mauSacService.findAll());
        model.addAttribute("listSize", sizeService.findAll());
        model.addAttribute("listDe", deService.findAll());
        return "chi-tiet-san-pham/add";
    }

    @GetMapping("/view-update/{idctsp}")
    public String viewUpdate(Model model, @PathVariable("idctsp") UUID id,
                             @ModelAttribute("mauSac") MauSac mauSac,
                             @ModelAttribute("size") Size size,
                             @ModelAttribute("de") De de) {
        ChiTietSanPham ctsp = chiTietSanPhamService.findById(id);
        model.addAttribute("chiTietSanPham", ctsp);
        model.addAttribute("listSP", sanPhamRepository.findAll());
        model.addAttribute("listMS", mauSacService.findAll());
        model.addAttribute("listSize", sizeService.findAll());
        model.addAttribute("listDe", deService.findAll());
        return "chi-tiet-san-pham/update";
    }

    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "chiTietSanPham") ChiTietSanPham chiTietSanPham, BindingResult result, Model model,
                      @ModelAttribute("mauSac") MauSac mauSac,
                      @ModelAttribute("size") Size size,
                      @ModelAttribute("de") De de) {
        if(result.hasErrors()){
            model.addAttribute("listSP", sanPhamRepository.findAll());
            model.addAttribute("listMS", mauSacService.findAll());
            model.addAttribute("listSize", sizeService.findAll());
            model.addAttribute("listDe", deService.findAll());
            return "chi-tiet-san-pham/add";
        }

        chiTietSanPham.setNgayTao(Date.valueOf(LocalDate.now()));
        chiTietSanPhamService.add(chiTietSanPham);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @PostMapping("/update/{idctsp}")
    public String update(@ModelAttribute(name = "chiTietSanPham") ChiTietSanPham chiTietSanPham, @PathVariable(name = "idctsp") UUID id,
                         @ModelAttribute("mauSac") MauSac mauSac,
                         @ModelAttribute("size") Size size,
                         @ModelAttribute("de") De de){
        ChiTietSanPham ctsp = chiTietSanPhamService.findById(id);
        chiTietSanPham.setId(id);
        chiTietSanPham.setNgayTao(ctsp.getNgayTao());
        chiTietSanPham.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        chiTietSanPhamService.update(id, chiTietSanPham);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @GetMapping("/delete/{idctsp}")
    public String delete(@PathVariable(name = "idctsp") UUID id){
        chiTietSanPhamService.delete(id);
        return "redirect:/chi-tiet-san-pham/hien-thi";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, @RequestParam("search") String search){
        model.addAttribute("listSP", sanPhamRepository.findAll());
        model.addAttribute("listMS", mauSacService.findAll());
        model.addAttribute("listSize", sizeService.findAll());
        model.addAttribute("listDe", deService.findAll());

        List<ChiTietSanPham> list = chiTietSanPhamService.search(search);
        model.addAttribute("listCTSP", list);
        return "chi-tiet-san-pham/hien-thi";
    }

    @PostMapping("/loc")
    public String loc(Model model, @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, @RequestParam("locSP") String locSP,
                      @RequestParam("locMS") String locMS,
                      @RequestParam("locSize") String locSize,
                      @RequestParam("locDe") String locDe){
        if(locSP.equals("null") && locMS.equals("null") &&  locSize.equals("null") && locDe.equals("null")){
            return "redirect:/chi-tiet-san-pham/hien-thi";
        }
        model.addAttribute("listSP", sanPhamRepository.findAll());
        model.addAttribute("listMS", mauSacService.findAll());
        model.addAttribute("listSize", sizeService.findAll());
        model.addAttribute("listDe", deService.findAll());

        List<ChiTietSanPham> list = chiTietSanPhamService.loc(locSP, locMS, locSize, locDe);
        model.addAttribute("listCTSP", list);
        return "chi-tiet-san-pham/hien-thi";
    }

    @PostMapping("/modal-add-mau-sac")
    public String addMauSac(@ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, @ModelAttribute("mauSac") @Valid MauSac mauSac, BindingResult result,
                            @ModelAttribute("size") Size size,
                            @ModelAttribute("de") De de, Model model){
        if(result.hasErrors()){
            model.addAttribute("listSP", sanPhamRepository.findAll());
            model.addAttribute("listMS", mauSacService.findAll());
            model.addAttribute("listSize", sizeService.findAll());
            model.addAttribute("listDe", deService.findAll());
            return "chi-tiet-san-pham/add";
        }
        String maMS = "MS" + (mauSacService.findAll().size()+1);
        mauSac.setMa(maMS);
        mauSac.setNgayTao(Date.valueOf(LocalDate.now()));
        mauSacService.add(mauSac);
        return "redirect:/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/modal-add-size")
    public String addSize(@ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, @ModelAttribute("size") @Valid Size size, BindingResult result,
                          @ModelAttribute("mauSac") MauSac mauSac,
                          @ModelAttribute("de") De de, Model model){
        if(result.hasErrors()){
            model.addAttribute("listSP", sanPhamRepository.findAll());
            model.addAttribute("listMS", mauSacService.findAll());
            model.addAttribute("listSize", sizeService.findAll());
            model.addAttribute("listDe", deService.findAll());
            return "chi-tiet-san-pham/add";
        }
        String maSize  = "Size" + (sizeService.findAll().size()+1);
        size.setMa(maSize);
        size.setNgayTao(Date.valueOf(LocalDate.now()));
        sizeService.add(size);
        return "redirect:/chi-tiet-san-pham/view-add";
    }

    @PostMapping("/modal-add-de")
    public String addDe(@ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, @ModelAttribute("de") @Valid De de, BindingResult result,
                        @ModelAttribute("mauSac") MauSac mauSac,
                        @ModelAttribute("size") Size size, Model model){
        if(result.hasErrors()){
            model.addAttribute("listSP", sanPhamRepository.findAll());
            model.addAttribute("listMS", mauSacService.findAll());
            model.addAttribute("listSize", sizeService.findAll());
            model.addAttribute("listDe", deService.findAll());
            return "chi-tiet-san-pham/add";
        }
        String maDe = "De" + (deService.findAll().size()+1);
        de.setMa(maDe);
        de.setNgayTao(Date.valueOf(LocalDate.now()));
        deService.add(de);
        return "redirect:/chi-tiet-san-pham/view-add";
    }
}
