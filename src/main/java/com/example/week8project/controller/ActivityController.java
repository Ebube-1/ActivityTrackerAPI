package com.example.week8project.controller;

import com.example.week8project.repository.ActivityRepository;
import com.example.week8project.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ActivityController {
    @Autowired
    private ActivityRepository activityRepo;

    @Autowired
    private ActivityService activityService;

    @GetMapping("/")  //todo: to fix the action for the delete button
    public void delete(@PathVariable("id") long activityId){
        activityService.deleteActivity(activityId);
    }

}
