package com.example.demo.controllers;

import com.example.demo.models.ChiTietSanPham;
import com.example.demo.models.De;
import com.example.demo.models.MauSac;
import com.example.demo.models.Size;
import com.example.demo.repositories.SanPhamRepository;
import com.example.demo.services.ChiTietSanPhamService;
import com.example.demo.services.DeService;
import com.example.demo.services.MauSacService;
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
        model.addAttribute("contentPage", "chi-tiet-san-pham/hien-thi.jsp");
        return "layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham,
                                @RequestParam("pageNum") Optional<Integer> num,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer size) {

        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<ChiTietSanPham> page = chiTietSanPhamService.getAll1(pageable);
        model.addAttribute("contentPage", "chi-tiet-san-pham/view-trang-thai.jsp");
        model.addAttribute("listCTSP", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
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
        model.addAttribute("contentPage", "chi-tiet-san-pham/add.jsp");
        return "layout";
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
        model.addAttribute("contentPage", "chi-tiet-san-pham/update.jsp");
        return "layout";
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
            model.addAttribute("contentPage", "chi-tiet-san-pham/add.jsp");
            return "layout";
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

    @GetMapping("/update-all")
    public String updateTT(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                           @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chiTietSanPham.setNgayCapNhat(date);
        chiTietSanPhamService.updateTT();
        Page<ChiTietSanPham> page = chiTietSanPhamService.getAll1(pageable);
        model.addAttribute("contentPage", "chi-tiet-san-pham/view-trang-thai.jsp");
        model.addAttribute("listCTSP", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);

        ChiTietSanPham ctsp = chiTietSanPhamService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        ctsp.setNgayCapNhat(date);
        ctsp.setTinhTrang(1);
        chiTietSanPhamService.update(id, ctsp);
        Page<ChiTietSanPham> page = chiTietSanPhamService.getAll(pageable);
        model.addAttribute("contentPage", "chi-tiet-san-pham/hien-thi.jsp");
        model.addAttribute("listCTSP", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        ChiTietSanPham ctsp = chiTietSanPhamService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        ctsp.setNgayCapNhat(date);

        ctsp.setTinhTrang(0);
        chiTietSanPhamService.update(id, chiTietSanPham);
        Page<ChiTietSanPham> page = chiTietSanPhamService.getAll1(pageable);
        model.addAttribute("contentPage", "chi-tiet-san-pham/view-trang-thai.jsp");
        model.addAttribute("listCTSP", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @PostMapping("/search")
    public String search(Model model, @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, @RequestParam("search") String search){
        model.addAttribute("listSP", sanPhamRepository.findAll());
        model.addAttribute("listMS", mauSacService.findAll());
        model.addAttribute("listSize", sizeService.findAll());
        model.addAttribute("listDe", deService.findAll());

        List<ChiTietSanPham> list = chiTietSanPhamService.search0(search);
        model.addAttribute("listCTSP", list);
        model.addAttribute("contentPage", "chi-tiet-san-pham/hien-thi.jsp");
        return "layout";
    }

    @PostMapping("/search1")
    public String search1(Model model, @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, @RequestParam("search") String search){
        model.addAttribute("listSP", sanPhamRepository.findAll());
        model.addAttribute("listMS", mauSacService.findAll());
        model.addAttribute("listSize", sizeService.findAll());
        model.addAttribute("listDe", deService.findAll());

        List<ChiTietSanPham> list = chiTietSanPhamService.search1(search);
        model.addAttribute("listCTSP", list);
        model.addAttribute("contentPage", "chi-tiet-san-pham/view-trang-thai.jsp");
        return "layout";
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
        model.addAttribute("contentPage", "chi-tiet-san-pham/hien-thi.jsp");
        model.addAttribute("listCTSP", list);
        return "layout";
    }

    @PostMapping("/loc1")
    public String loc1(Model model, @ModelAttribute("chiTietSanPham") ChiTietSanPham chiTietSanPham, @RequestParam("locSP") String locSP,
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
        model.addAttribute("contentPage", "chi-tiet-san-pham/view-trang-thai.jsp");
        return "layout";
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
            model.addAttribute("contentPage", "chi-tiet-san-pham/add.jsp");
            return "layout";
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
            model.addAttribute("contentPage", "chi-tiet-san-pham/add.jsp");
            return "layout";
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
            model.addAttribute("contentPage", "chi-tiet-san-pham/add.jsp");
            return "layout";
        }
        String maDe = "De" + (deService.findAll().size()+1);
        de.setMa(maDe);
        de.setNgayTao(Date.valueOf(LocalDate.now()));
        deService.add(de);
        return "redirect:/chi-tiet-san-pham/view-add";
    }
}
