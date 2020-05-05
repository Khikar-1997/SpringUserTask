package com.example.demo.service;

import com.example.demo.exception.TaskNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.persistance.model.task.Task;
import com.example.demo.persistance.model.user.User;
import com.example.demo.persistance.repository.TaskRepository;
import com.example.demo.persistance.repository.UserRepository;
import com.example.demo.rest.model.task.TaskRequestModel;
import com.example.demo.rest.model.task.TaskResponseModel;
import com.example.demo.rest.model.user.UserResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    //region Public Methods

    public TaskResponseModel create(TaskRequestModel taskRequestModel){
        LOGGER.info("Request to create task - {}",taskRequestModel);
        Task task = buildTaskModelForm(taskRequestModel);
        User user = userRepository.findById(taskRequestModel.getUserId())
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found for id - {}%d",taskRequestModel.getUserId())));
        task.setUser(user);
        Task createTask = taskRepository.save(task);
        TaskResponseModel taskResponseModel = buildTaskResponseModelForm(createTask);
        LOGGER.info("Response of: task is successfully created - {}",taskResponseModel);
        return taskResponseModel;
    }

    public List<TaskResponseModel> selectAllTasks(){
        LOGGER.info("Request to select all tasks");
        List<Task> tasks = taskRepository.findAll();
        List<TaskResponseModel> collect = tasks.stream()
                .map(this::buildTaskResponseModelForm)
                .collect(Collectors.toList());
        LOGGER.info("Response of: all tasks are successfully selected - {}",collect);
        return collect;
    }

    public TaskResponseModel findTaskById(Long id){
        LOGGER.info("Request to find task by id - {}",id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(String.format("Task not found for id - {}%d",id)));
        TaskResponseModel taskResponseModel = buildTaskResponseModelForm(task);
        LOGGER.info("Response of: task is successfully find by id - {}",taskResponseModel);
        return taskResponseModel;
    }

    public TaskResponseModel update(Long id,TaskRequestModel taskRequestModel){
        LOGGER.info("Request to update task by id - {} - {}",id,taskRequestModel);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(String.format("Task not found for id - {}%d",id)));
        task.setName(taskRequestModel.getName());
        task.setState(taskRequestModel.getState());
        User user = userRepository.findById(taskRequestModel.getUserId())
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found for id - {}%d",taskRequestModel.getUserId())));
        task.setUser(user);
        Task updatedTask = taskRepository.save(task);
        TaskResponseModel taskResponseModel = buildTaskResponseModelForm(updatedTask);
        LOGGER.info("Response of: task is successfully updated by id - {}",taskResponseModel);
        return taskResponseModel;
    }

    public void delete(Long id){
        LOGGER.info("Request to delete task by id - {}",id);
        taskRepository.deleteById(id);
        LOGGER.info("Response of: task is successfully deleted by id");
    }

    //endregion

    //region Private Methods

    private Task buildTaskModelForm(TaskRequestModel taskRequestModel){
        Task task = new Task();
        task.setName(taskRequestModel.getName());
        task.setState(taskRequestModel.getState());
        return task;
    }

    private TaskResponseModel buildTaskResponseModelForm(Task task){
        TaskResponseModel taskResponseModel = new TaskResponseModel();
        taskResponseModel.setId(task.getId());
        taskResponseModel.setName(task.getName());
        taskResponseModel.setState(task.getState());
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setId(task.getUser().getId());
        userResponseModel.setName(task.getUser().getName());
        userResponseModel.setSurname(task.getUser().getSurname());
        userResponseModel.setAge(task.getUser().getAge());
        taskResponseModel.setUser(userResponseModel);
        return taskResponseModel;
    }

    //endregion
}
