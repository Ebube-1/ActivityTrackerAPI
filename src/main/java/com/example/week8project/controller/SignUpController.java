package com.example.week8project.controller;

import com.example.week8project.dtos.MemberDto;
import com.example.week8project.entity.Member;
import com.example.week8project.repository.MemberRepository;
import com.example.week8project.service.MemberService;
import com.example.week8project.service.implementation.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignUpController {


    private final MemberService memberService;

    //private MemberRepository memberRepository;

    @Autowired
    public SignUpController(MemberServiceImpl appUserService) {
        this.memberService = appUserService;
    }

    @GetMapping("/register")
    public String showForm(Model model){
        Member member = new Member();
        model.addAttribute("member", member);
        return "register";
    }

    @PostMapping("/register")
    public String submitForm(@ModelAttribute("memberDto") MemberDto memberDto){
        memberService.createAppUser(memberDto);
        return "index";
    }


}
