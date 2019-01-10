package com.unitedremote.shops.Services;

import com.unitedremote.shops.DAO.Entities.User;
import com.unitedremote.shops.DAO.Repositories.ShopRepository;
import com.unitedremote.shops.DAO.Repositories.UserRepository;
import com.unitedremote.shops.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("with id:" + id));
    }

    @Override
    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public String login(String email, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
            System.out.println("OK");
            return jwtTokenProvider.createToken(email, findByUsername(email).orElseThrow(() -> new UsernameNotFoundException("Email " + email + "not found")).getRoles());
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid username/password.");
        }
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(getUser(id));
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.save(updatedUser);
    }

    @Override
    public Optional<User> findByUsername(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void addRoleToUser(String email, String role) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
//        if (user.getRoles() == null) System.out.println("NULL");
        user.setRoles(Collections.singletonList("USER"));
    }


}
