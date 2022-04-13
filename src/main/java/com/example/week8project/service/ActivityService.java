package com.example.week8project.service;

import com.example.week8project.model.Activity;
import com.example.week8project.model.User;
import com.example.week8project.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public Activity findActivityById(long activityId) {
        return activityRepository.findById(activityId).orElseThrow(() ->
                new IllegalArgumentException("Invalid activity id: " + activityId));
    }

    //delete activity services
    public void deleteActivity(Long activityId){
        Activity activity = findActivityById(activityId);
        Set<User> user = activity.getUsers();

        for (User singleUser: user){
            singleUser.getActivities().remove(activity);
        }
        activityRepository.delete(activity);
    }

    //change activity status




}
