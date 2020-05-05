package com.example.demo.persistance.repository;

import com.example.demo.persistance.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
