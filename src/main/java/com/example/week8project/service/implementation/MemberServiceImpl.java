package com.example.week8project.service.implementation;

import com.example.week8project.dtos.MemberDto;
import com.example.week8project.dtos.LoginDto;
import com.example.week8project.entity.Member;
import com.example.week8project.repository.MemberRepository;
import com.example.week8project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
// or use @RequiredArgsConstructor insteadof declaring the constructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void createAppUser(MemberDto memberDto) {
        Member member = new Member();
        member.setFirstname(memberDto.getFirstname());
        member.setLastname(memberDto.getLastname());
        member.setUsername(memberDto.getUsername());
        member.setEmail(memberDto.getEmail());
        member.setPassword(memberDto.getPassword());

        memberRepository.save(member);
    }

    @Override
    public Member login(LoginDto loginDto) {
        Optional<Member> appUser = memberRepository.findByEmail(loginDto.getEmail());
        if(appUser.isPresent()){
            Member presentMember = appUser.get();
            if(presentMember.getPassword().equals(loginDto.getPassword()))
                return presentMember;
        }
        return null;
    }

//    public void signUpAppUser(AppUser appUser){
//        appUserRepository.save(appUser);
//    }
}
