package com.example.demo.rest.model.task;

import com.example.demo.persistance.model.task.State;

import java.io.Serializable;
import java.util.Objects;

public class TaskRequestModel implements Serializable {
    private String name;
    private State state;
    private Long userId;

    public TaskRequestModel(String name, State state, Long userId) {
        this.name = name;
        this.state = state;
        this.userId = userId;
    }

    public TaskRequestModel() {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskRequestModel that = (TaskRequestModel) o;
        return Objects.equals(name, that.name) &&
                state == that.state &&
                Objects.equals(userId, that.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, state, userId);
    }

    @Override
    public String toString() {
        return "TaskRequestModel{" +
                "name='" + name + '\'' +
                ", state=" + state +
                ", userId=" + userId +
                '}';
    }
}
