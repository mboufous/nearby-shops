package com.unitedremote.shops.DAO.Services;

import com.unitedremote.shops.DAO.Entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface IUserService {
    User getUser(Long id);

    User register(User newUser);

    User login(String email, String password);

    void deleteUser(Long id);

    User updateUser(Long id, User updatedUser);

}
