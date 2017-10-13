package com.csye6225.demo.repository;

import com.csye6225.demo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task,String> {

    Task findByUserId(Long userId);

}