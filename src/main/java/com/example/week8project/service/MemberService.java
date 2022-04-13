package com.example.week8project.service;

import com.example.week8project.dtos.MemberDto;
import com.example.week8project.dtos.LoginDto;
import com.example.week8project.entity.Member;

public interface MemberService {
    void createAppUser(MemberDto memberDto);
    Member login(LoginDto loginDto);

}
