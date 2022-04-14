package com.example.week8project.dtos;

import com.example.week8project.models.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private String title;
    private String description;
    private String status;
    private String email;
}
