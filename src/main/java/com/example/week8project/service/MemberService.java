package com.example.week8project.service;

import com.example.week8project.dtos.MemberDto;
import com.example.week8project.dtos.LoginDto;
import com.example.week8project.dtos.TaskDto;
import com.example.week8project.models.Member;
import com.example.week8project.models.Task;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface MemberService {
    void createMember(MemberDto memberDto);
    Member login(LoginDto loginDto);
    void createTask(TaskDto taskDto, HttpSession session);
    List<Task> viewAllMemberTasks(HttpSession session);
    Task viewTask(Long taskId);
    void updateTask(Long taskId,TaskDto taskDto,HttpSession session);

//    void deleteTask(Long taskId,HttpSession session);

}
