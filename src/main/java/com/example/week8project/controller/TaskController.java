package com.example.week8project.controller;

import com.example.week8project.dtos.MemberDto;
import com.example.week8project.dtos.TaskDto;
import com.example.week8project.models.Member;
import com.example.week8project.models.Task;
import com.example.week8project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TaskController {

    private final MemberService memberService;
    @Autowired
    public TaskController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/addtask")
    public String create(Model model){

        TaskDto taskDto = new TaskDto();
        model.addAttribute("taskDto",taskDto);
        return "account/addtask";
    }

    @PostMapping("/addnewtask")
    public String createNewTask(@ModelAttribute TaskDto taskDto, HttpSession session){
        memberService.createTask(taskDto,session);
        return "redirect:/account";


    }
    @GetMapping("/account")
    public String getMemberTasks(Model model, HttpSession session){
        List<Task>  listOfTasks = memberService.viewAllMemberTasks(session);
        model.addAttribute("listOfTasks",listOfTasks);
        return "account/index2";
    }

    @GetMapping("/editButton/{id}")
    public String showEditPage(@PathVariable Long id, Model model){
        Task task = memberService.viewTask(id);
        model.addAttribute("task",task);
        return "/account/edit";
    }

    @PostMapping("/edit/")
    public String edit(@ModelAttribute TaskDto taskDto, HttpSession session){
//        Member member = (Member) session.getAttribute("user");

        return null;
    }

}
