package com.example.demo.controllers;

import com.example.demo.models.ChatLieu;
import com.example.demo.models.HangSanPham;
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

    @GetMapping("/hien-thi")
    public String hienThi(Model model, @RequestParam("num") Optional<Integer> num,
                          @RequestParam(name = "size", defaultValue = "5", required = false) Integer size) {
        Sort sort = Sort.by("ngayTao").descending();
        Pageable pageable = PageRequest.of(num.orElse(0), size, sort);
        Page<ChatLieu> list = chatLieuService.getAll(pageable);
        model.addAttribute("listChatLieu", list.getContent());
        model.addAttribute("total", list.getTotalPages());
        return "chat-lieu/hien-thi";
    }
    @GetMapping("/view-add")
    public String viewAdd(Model model, @ModelAttribute("ChatLieu") ChatLieu chatLieu){
        model.addAttribute("ChatLieu",new ChatLieu());
        return "chat-lieu/add";
    }
    @PostMapping("/add")
    public String add(@Valid @ModelAttribute(name = "ChatLieu") ChatLieu chatLieu, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "chat-lieu/add";
        }
        String maCL = "CL" + (chatLieuService.findAll().size()+1);
        chatLieu.setMa(maCL);
        chatLieu.setNgayTao(Date.valueOf(LocalDate.now()));
        chatLieuService.add(chatLieu);
        return "redirect:/chat-lieu/hien-thi";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable(name = "id") UUID id){
        chatLieuService.delete(id);
        return "redirect:/chat-lieu/hien-thi";
    }
    @GetMapping("/view-update/{id}")
    public String detail(Model model, @PathVariable("id") UUID id){
        ChatLieu cl = chatLieuService.findById(id);
        model.addAttribute("chatLieu",cl);
        return "chat-lieu/update";
    }
    @PostMapping("/update/{id}")
    public String update(@ModelAttribute(name = "chatLieu") ChatLieu chatLieu,
                         @PathVariable(name = "id") UUID id){
        ChatLieu clUd = chatLieuService.findById(id);
        chatLieu.setId(id);
        chatLieu.setMa(clUd.getMa());
        chatLieu.setNgayTao(clUd.getNgayTao());
        chatLieu.setNgayCapNhat(Date.valueOf(LocalDate.now()));
        chatLieuService.update(id,chatLieu);
        return "redirect:/chat-lieu/hien-thi";
    }
    @PostMapping("/search")
    public String search (Model model, @ModelAttribute("chatLieu") ChatLieu chatLieu, @RequestParam("search") String search){
        List<ChatLieu> list = chatLieuService.search(search);
        model.addAttribute("listChatLieu",list);
        return "chat-lieu/hien-thi";
    }

}
