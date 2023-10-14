package com.example.demo.controllers;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.HangSanPham;
import com.example.demo.models.KhachHang;
import com.example.demo.services.ChatLieuService;
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
@RequestMapping("/chat-lieu")
public class ChatLieuController {
    @Autowired
    private ChatLieuService chatLieuService;

    @GetMapping("hien-thi")
    public String hienthi(@ModelAttribute("chatLieu") ChatLieu chatLieu, Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<ChatLieu> list = chatLieuService.getAll(pageable);
        model.addAttribute("listChatLieu", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        model.addAttribute("contentPage", "chat-lieu/hien-thi.jsp");
        return "layout";
    }

    @GetMapping("/hien-thi-delete")
    public String hienThiDelete(Model model, @ModelAttribute("khachHang") KhachHang khachHang,
                                @RequestParam("pageNum") Optional<Integer> num,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer size) {

        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<ChatLieu> page = chatLieuService.getAll1(pageable);
        model.addAttribute("contentPage", "chat-lieu/view-trang-thai.jsp");
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("chatLieu") KhachHang khachHang){
        model.addAttribute("chatLieu",new ChatLieu());
        model.addAttribute("contentPage","chat-lieu/add.jsp");
        return "layout";
    }

    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id) {
        ChatLieu hsp = chatLieuService.findById(id);
        model.addAttribute("chatLieu", hsp);
        model.addAttribute("contentPage", "chat-lieu/update.jsp");
        return "layout";
    }

    @PostMapping("/add")
    public String add(Model model,@Valid @ModelAttribute("chatlieu") ChatLieu chatLieu ,BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "chat-lieu/add.jsp");
            return "layout";
        }
        chatLieu.setNgayTao(Date.valueOf(LocalDate.now()));
        chatLieu.setTinhTrang(0);
        chatLieu.setMa("CL" + String.valueOf(chatLieuService.findAll().size() + 1));
        chatLieuService.add(chatLieu);
        return "redirect:/chat-lieu/hien-thi";
        // Tiếp tục xử lý và trả về view tương ứng
    }

    @PostMapping("/update/{id}")
    public String update(Model model, @ModelAttribute("chatLieu") @Valid ChatLieu chatLieu,
                         BindingResult bindingResult, @PathVariable("id") UUID id) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("contentPage", "chat-lieu/update.jsp");
            return "layout";
        }
        ChatLieu cl= chatLieuService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chatLieu.setNgayCapNhat(date);
        chatLieu.setNgayTao(cl.getNgayTao());
        chatLieu.setMa(cl.getMa());
        chatLieu.setTinhTrang(cl.getTinhTrang());
        chatLieuService.update(id, chatLieu);
        return "redirect:/chat-lieu/hien-thi";
    }

    @GetMapping("/update-all")
    public String updateTT(Model model, @RequestParam("pageNum") Optional<Integer> pageNum,
                           @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                           @ModelAttribute("khachHang") ChatLieu chatLieu) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chatLieu.setNgayCapNhat(date);
        chatLieuService.updateTT();
        Page<ChatLieu> page = chatLieuService.getAll1(pageable);
        model.addAttribute("contentPage","chat-lieu/view-trang-thai.jsp");
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @GetMapping("/update-status/{id}")
    public String updateStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                               @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("chatLieu") ChatLieu chatLieu) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);

        ChatLieu chatLieu1 = chatLieuService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chatLieu1.setNgayCapNhat(date);
        chatLieu1.setTinhTrang(1);
        chatLieuService.update(id,chatLieu1);
        Page<ChatLieu> page = chatLieuService.getAll(pageable);
        model.addAttribute("contentPage","chat-lieu/hien-thi.jsp");
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }
    //
    @GetMapping("/reset-status/{id}")
    public String resetStatus(Model model, @PathVariable("id") UUID id, @RequestParam("pageNum") Optional<Integer> pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize, @ModelAttribute("chatLieu") ChatLieu chatLieu) {
        Sort sort = Sort.by("ngayTao").ascending();
        Pageable pageable = PageRequest.of(pageNum.orElse(0), pageSize, sort);
        ChatLieu chatLieu1 = chatLieuService.findById(id);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        chatLieu1.setNgayCapNhat(date);

        chatLieu1.setTinhTrang(0);
        chatLieuService.update(id,chatLieu1);
        Page<ChatLieu> page = chatLieuService.getAll(pageable);
        model.addAttribute("contentPage","chat-lieu/view-trang-thai.jsp");
        model.addAttribute("listChatLieu", page.getContent());
        model.addAttribute("page", page.getNumber());
        model.addAttribute("total", page.getTotalPages());
        return "layout";
    }

    @PostMapping("/search-0")
    public String search0(Model model, @ModelAttribute("khachHang") ChatLieu chatLieu, @RequestParam("search") String search, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Sort sort = Sort.by("ngayTao").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<ChatLieu> list = chatLieuService.getAll(pageable);
            model.addAttribute("listChatLieu", list.getContent());
            model.addAttribute("total", list.getTotalPages());
            model.addAttribute("contentPage", "chat-lieu/hien-thi.jsp");
            return "layout";
        } else {
            List<ChatLieu> list = chatLieuService.search0(search);
            model.addAttribute("listChatLieu", list);
            model.addAttribute("contentPage", "chat-lieu/hien-thi.jsp");
            return "layout";
        }

    }

    @PostMapping("/search-1")
    public String search1(Model model, @ModelAttribute("khachHang") ChatLieu chatLieu, @RequestParam("search") String search, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        if (search.isEmpty()) {
            model.addAttribute("thongBao", "Không để trống thông tin");
            Sort sort = Sort.by("ngayTao").descending();
            Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
            Page<ChatLieu> list = chatLieuService.getAll1(pageable);
            model.addAttribute("listChatLieu", list.getContent());
            model.addAttribute("total", list.getTotalPages());
            model.addAttribute("contentPage", "chat-lieu/view-trang-thai.jsp");
            return "layout";
        } else {
            List<ChatLieu> list = chatLieuService.search1(search);
            model.addAttribute("listChatLieu", list);
            model.addAttribute("contentPage", "chat-lieu/view-trang-thai.jsp");
            return "layout";
        }

    }

}
