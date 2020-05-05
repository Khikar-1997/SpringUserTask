package com.example.demo.rest.controller;

import com.example.demo.rest.model.task.TaskRequestModel;
import com.example.demo.rest.model.task.TaskResponseModel;
import com.example.demo.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/task")
    public ResponseEntity<TaskResponseModel> create(@RequestBody TaskRequestModel task){
        LOGGER.info("Request to create task - {}",task);
        TaskResponseModel createTask = taskService.create(task);
        LOGGER.info("Response of: task is successfully created - {}",createTask);
        return ResponseEntity.ok(createTask);
    }

    @GetMapping(value = "/task")
    public ResponseEntity<List<TaskResponseModel>> selectAllTasks(){
        LOGGER.info("Request to select all tasks");
        List<TaskResponseModel> tasks = taskService.selectAllTasks();
        LOGGER.info("Response of: all tasks are successfully selected - {}",tasks);
        return ResponseEntity.ok(tasks);
    }

    @GetMapping(value = "/task/{id}")
    public ResponseEntity<TaskResponseModel> findTaskById(@PathVariable Long id){
        LOGGER.info("Request to find task by id - {}",id);
        TaskResponseModel task = taskService.findTaskById(id);
        LOGGER.info("Response of: task is successfully find by id - {}",task);
        return ResponseEntity.ok(task);
    }

    @PutMapping(value = "/task/{id}")
    public ResponseEntity<TaskResponseModel> update(@PathVariable Long id,@RequestBody TaskRequestModel task){
        LOGGER.info("Request to update task by id - {} - {}",id,task);
        TaskResponseModel updatedTask = taskService.update(id, task);
        LOGGER.info("Response of: task is successfully updated - {}",updatedTask);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping(value = "/task/{id}")
    public void delete(@PathVariable Long id){
        LOGGER.info("Request to delete task by id - {}",id);
        taskService.delete(id);
        LOGGER.info("Response of: task is successfully deleted by id");
    }
}
