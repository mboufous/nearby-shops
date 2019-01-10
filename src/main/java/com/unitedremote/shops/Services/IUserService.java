package com.unitedremote.shops.Services;

import com.unitedremote.shops.DAO.Entities.User;

import java.util.Optional;

public interface IUserService {
    User getUser(Long id);

    User save(User newUser);

    String login(String email, String password);

    void deleteUser(Long id);

    User updateUser(Long id, User updatedUser);

    Optional<User> findByUsername(String username);

    void addRoleToUser(String username, String Role);
}
