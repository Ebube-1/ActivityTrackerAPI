package com.example.week8project.service.implementation;

import com.example.week8project.dtos.LoginDto;
import com.example.week8project.dtos.MemberDto;
import com.example.week8project.dtos.TaskDto;
import com.example.week8project.models.Member;
import com.example.week8project.models.Task;
import com.example.week8project.repository.MemberRepository;
import com.example.week8project.repository.TaskRepository;
import com.example.week8project.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
// or use @RequiredArgsConstructor insteadof declaring the constructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final TaskRepository taskRepository;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, TaskRepository taskRepository) {
        this.memberRepository = memberRepository;
        this.taskRepository = taskRepository;
    }


    @Override
    public void createMember(MemberDto memberDto) {
        Member member = new Member();
        member.setFirstname(memberDto.getFirstname());
        member.setLastname(memberDto.getLastname());
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

    @Override
    public void createTask(TaskDto taskDto, HttpSession session) {
        Task newTask = new Task();
        newTask.setTitle(taskDto.getTitle());
        newTask.setDescription(taskDto.getDescription());
        newTask.setStatus("Pending");
        Time createdTime = new Time(System.currentTimeMillis());
        newTask.setCreatedAt(createdTime);
        Time updatedTime = new Time(System.currentTimeMillis());
        newTask.setUpdatedAt(updatedTime);
        taskRepository.save(newTask);
        Member presentMember = (Member) session.getAttribute("user");
        Optional<Member> member = memberRepository.findById(presentMember.getId());
        member.orElseThrow().getListOfTasks().add(newTask);
        memberRepository.save(member.orElseThrow());

    }

    @Override
    public List<Task> viewAllMemberTasks(HttpSession session) {
//        Optional<List<Task>> listOfTasks = Optional.ofNullable(taskRepository.findAll());
//        return listOfTasks.orElseThrow();
        Member member = (Member) session.getAttribute("user");
        return  memberRepository.findById(member.getId()).orElseThrow().getListOfTasks();
    }

    @Override
    public Task viewTask(Long taskId) {
//        Task newTask = new Task();
//        newTask.setTitle(taskDto.getTitle());
        return  taskRepository.findById(taskId).get();
    }

    @Override
    public void updateTask(Long taskId,TaskDto taskDto,HttpSession session) {
        Member member = (Member) session.getAttribute("user");
        Optional<Task> task = taskRepository.findById(taskId);
//        Task task2= taskRepository.getOne(taskId);
        task.orElseThrow().setTitle(taskDto.getTitle());
        task.get().setDescription(taskDto.getDescription());
        task.get().setStatus(taskDto.getStatus());
        task.get().setUpdatedAt(new Time(System.currentTimeMillis()));
        taskRepository.save(task.orElseThrow());
        Optional<Member> memberToUpdate = memberRepository.findById(member.getId());
//        Optional<Member> member = memberRepository.findByEmail(task.get().getMember().getEmail());
        Task taskToUpdate = new Task();
        int index = 0;
        for (int i = 0; i < memberToUpdate.orElseThrow().getListOfTasks().size(); i++) {
            if (memberToUpdate.get().getListOfTasks().get(i).getTitle().equals(task.get().getTitle())) {
                taskToUpdate = memberToUpdate.get().getListOfTasks().get(i);
                index = i;
                break;
            }
        }
        taskToUpdate.setTitle(task.get().getTitle());
        taskToUpdate.setDescription(task.get().getDescription());
        taskToUpdate.setUpdatedAt(task.get().getUpdatedAt());
        memberToUpdate.get().getListOfTasks().remove(index);
        memberToUpdate.get().getListOfTasks().add(index,taskToUpdate);
    }
//
//    @Override
//    public void deleteTask(Long taskId,HttpSession session) {
//        Optional<Task> task = taskRepository.findById(taskId);
////        Optional<Member> member = memberRepository.findByEmail(task.orElseThrow().getMember().getEmail());
//        Member member = (Member) session.getAttribute("user");
//        //Todo ====> Add custom exception if this does not work
//        Optional<Member> memberToDeleteTaskFrom = memberRepository.findById(member.getId());
//        memberToDeleteTaskFrom.orElseThrow().getListOfTasks().remove(task.orElseThrow());
//        taskRepository.delete(task.orElseThrow());
////        List<Task> listOfTasks= taskRepository.findAll();
//    }

}
