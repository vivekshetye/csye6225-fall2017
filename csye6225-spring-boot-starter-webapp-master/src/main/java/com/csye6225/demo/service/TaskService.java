package com.csye6225.demo.service;

import com.csye6225.demo.model.Task;

public interface TaskService {

    public Task findTaskByUserId(Long userId);
}