package com.example.demo.rest.model.task;

import com.example.demo.persistance.model.task.State;
import com.example.demo.rest.model.user.UserResponseModel;

import java.io.Serializable;
import java.util.Objects;

public class TaskResponseModel implements Serializable {
    private Long id;
    private String name;
    private State state;
    private UserResponseModel user;

    public TaskResponseModel(Long id, String name, State state, UserResponseModel user) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.user = user;
    }

    public TaskResponseModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public UserResponseModel getUser() {
        return user;
    }

    public void setUser(UserResponseModel user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskResponseModel that = (TaskResponseModel) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                state == that.state &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, state, user);
    }

    @Override
    public String toString() {
        return "TaskResponseModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", user=" + user +
                '}';
    }
}
