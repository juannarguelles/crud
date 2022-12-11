package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<UserModel> getUsers() {
        return (ArrayList<UserModel>) userRepository.findAll();
    }

    public UserModel saveUser(UserModel user) {
        return userRepository.save(user);
    }

    public UserModel updateUser(UserModel user, Long id) {
        UserModel userDB = userRepository.findById(id).get();

        if (Objects.nonNull(user.getName())
                && !"".equalsIgnoreCase(
                        user.getName())) {
            userDB.setName(
                    user.getName());
        }

        if (Objects.nonNull(
                user.getEmail())
                && !"".equalsIgnoreCase(
                        user.getEmail())) {
            userDB.setEmail(
                    user.getEmail());
        }

        if (user.getPrioridad() >= 1 && user.getPrioridad() <= 3) {
            userDB.setPrioridad(user.getPrioridad());
        }

        return userRepository.save(userDB);

    }

    public Optional<UserModel> getById(Long id) {
        return userRepository.findById(id);
    }

    public ArrayList<UserModel> getByPrioridad(Integer prioridad) {
        return userRepository.findByPrioridad(prioridad);
    }

    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }

}
