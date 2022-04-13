package com.example.week8project.controller;

import com.example.week8project.dtos.LoginDto;
import com.example.week8project.entity.Member;
import com.example.week8project.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final MemberService memberService;

    public LoginController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/")
    public String login(Model model) {
        LoginDto loginDto = new LoginDto();
        model.addAttribute("loginDto", loginDto);
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto, HttpSession session) {
        Member member = memberService.login(loginDto);
        if (member != null){
            session.setAttribute("user", member);
        return "";
    }
    return"index";
    }

}
