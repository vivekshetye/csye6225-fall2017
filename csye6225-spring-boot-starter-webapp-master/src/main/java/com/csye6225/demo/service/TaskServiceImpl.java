package com.csye6225.demo.service;

import com.csye6225.demo.model.Task;
import com.csye6225.demo.model.User;
import com.csye6225.demo.repository.TaskRepository;
import com.csye6225.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    @Qualifier("taskRepository")
    private TaskRepository taskRepository;

    @Override
    public Task findTaskByUserId(Long userId) {
        return taskRepository.findByUserId(userId);
    }
}