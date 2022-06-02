package com.example.QuietMelody.controller;

import com.example.QuietMelody.domain.OrderList;
import com.example.QuietMelody.domain.User;
import com.example.QuietMelody.repos.OrderListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/my")
@Controller
public class MyController {
    @Autowired
    private OrderListRepo orderListRepo;

    @GetMapping()
    public String MyMain(Model model) {
        return "/my/main";
    }
    @GetMapping("/orderlist")
    public String orderList(@AuthenticationPrincipal User user, Model model) {
        if(user.isAdmin()){
            model.addAttribute("orderList", orderListRepo.findAll());
        }else{
            model.addAttribute("orderList", orderListRepo.findOrderByUserId(user.getId()));
        }
        return "/my/orderList";
    }

}
