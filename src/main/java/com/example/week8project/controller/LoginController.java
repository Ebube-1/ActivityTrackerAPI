package com.example.week8project.controller;

import com.example.week8project.dtos.LoginDto;
import com.example.week8project.models.Member;
import com.example.week8project.models.Task;
import com.example.week8project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

//    @Autowired
//    public LoginController(MemberService memberService) {
//        this.memberService = memberService;
//    }

    @GetMapping("/")
    public String login(Model model) {
        LoginDto loginDto = new LoginDto();
        model.addAttribute("loginDto", loginDto);
        return "index";
    }

    @PostMapping("/account")
    public String login(@ModelAttribute("loginDto") LoginDto loginDto, HttpSession session,Model model) {
        Member member = memberService.login(loginDto);
        session.setAttribute("user", member);
        if (member != null){
           //session.setAttribute("user", member);
            List<Task> listOfTasks = memberService.viewAllMemberTasks(session);
            model.addAttribute("listOfTasks",listOfTasks);
        return "/account/index2";
    }
        String message = "Wrong Email or Password";
        model.addAttribute("message",message);
        return"index";
    }



//    Person person = personService.loginAccount(loginDto);
//        if(person!=null){
//        session.setAttribute("person",person);
//        return "/account/index2";
//    }
//    String failed = "Wrong Email or Password";
//        model.addAttribute("failed",failed);
//
//        return "index";

}
