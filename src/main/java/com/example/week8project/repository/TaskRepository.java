package com.example.week8project.repository;


import com.example.week8project.models.Member;
import com.example.week8project.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
//    Optional<Task> findByTitle(String title);
//    Optional<Task> findByTitleAndAndMember(String title, Member member);
    Optional<Task> findById(Long taskId);
    //Optional<Task> findByIdAndMember(Long taskId, Member member);
    List<Task> findAll();
//    List<Task> findAllByStatus(String status);
//    List<Task> findAllByStatusAndMember(String status, Member member);


}
