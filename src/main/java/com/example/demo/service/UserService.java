package com.example.demo.service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.persistance.model.user.User;
import com.example.demo.persistance.repository.UserRepository;
import com.example.demo.rest.model.user.UserRequestModel;
import com.example.demo.rest.model.user.UserResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //region Public Methods

    public UserResponseModel create(UserRequestModel userRequestModel){
        LOGGER.info("Request to create user - {}",userRequestModel);
        User user = buildUserModelForm(userRequestModel);
        User createUser = userRepository.save(user);
        UserResponseModel userResponseModel = buildUserResponseModelForm(createUser);
        LOGGER.info("Response of: user is successfully created - {}",userResponseModel);
        return userResponseModel;
    }

    public List<UserResponseModel> selectAllUsers(){
        LOGGER.info("Request to select all users");
        List<User> users = userRepository.findAll();
        List<UserResponseModel> collect = users.stream()
                .map(this::buildUserResponseModelForm)
                .collect(Collectors.toList());
        LOGGER.info("Response of: all users are successfully selected - {}",collect);
        return collect;
    }

    public UserResponseModel findUserById(Long id){
        LOGGER.info("Request to find user by id - {}",id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found for id - {}%d",id)));
        UserResponseModel userResponseModel = buildUserResponseModelForm(user);
        LOGGER.info("Response of: user is successfully find by id - {}",userResponseModel);
        return userResponseModel;
    }

    public UserResponseModel update(Long id,UserRequestModel userRequestModel){
        LOGGER.info("Request to update user by id - {} - {}",id,userRequestModel);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(String.format("User not found for id - {}%d",id)));
        user.setName(userRequestModel.getName());
        user.setSurname(userRequestModel.getSurname());
        user.setAge(userRequestModel.getAge());
        User updatedUser = userRepository.save(user);
        UserResponseModel userResponseModel =buildUserResponseModelForm(updatedUser);
        LOGGER.info("Response of: user is successfully updated by id - {}",userResponseModel);
        return userResponseModel;
    }

    public void delete(Long id){
        LOGGER.info("Request to delete user by id - {}",id);
        userRepository.deleteById(id);
        LOGGER.info("Response of: user is successfully deleted by id");
    }

    //endregion

    //region Private Methods

    private User buildUserModelForm(UserRequestModel userRequestModel){
        User user = new User();
        user.setName(userRequestModel.getName());
        user.setSurname(userRequestModel.getSurname());
        user.setAge(userRequestModel.getAge());
        return user;
    }

    private UserResponseModel buildUserResponseModelForm(User user){
        UserResponseModel userResponseModel = new UserResponseModel();
        userResponseModel.setId(user.getId());
        userResponseModel.setName(user.getName());
        userResponseModel.setSurname(user.getSurname());
        userResponseModel.setAge(user.getAge());
        return userResponseModel;
    }

    //endregion
}
