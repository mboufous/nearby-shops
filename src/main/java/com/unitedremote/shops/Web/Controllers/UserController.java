package com.unitedremote.shops.Web.Controllers;

import com.unitedremote.shops.DAO.Entities.User;
import com.unitedremote.shops.Services.IUserService;
import com.unitedremote.shops.Utils.RandomLocationSingleton;
import com.unitedremote.shops.Web.LoginRequest;
import com.unitedremote.shops.Web.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("v1/users")
public class UserController {

    @Autowired
    IUserService userService;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequest data) throws BadCredentialsException {
        String token = userService.login(data.getEmail(), data.getPassword());
        System.out.println("token::" + token);
        Map<Object, Object> model = new HashMap<>();
        model.put("username", data.getEmail());
        model.put("token", token);
        return ok(model);
    }

    @PostMapping("/register")
    public User register(@RequestBody RegistrationForm userForm) {
        if (!userForm.getPassword().equals(userForm.getRepassword())) {
            throw new RuntimeException("Password aren't the same.");
        }
        if (userService.findByUsername(userForm.getEmail()).isPresent()) {
            throw new RuntimeException("User Already exists.");
        }
        User newUser = User.builder()
                .email(userForm.getEmail())
                .name(userForm.getName())
                .password(encoder.encode(userForm.getPassword()))
                .roles(Collections.singletonList("USER"))
                .location(RandomLocationSingleton.getInstance().fetch())
                .build();
        userService.save(newUser);
//        System.out.println(newUser);
        return newUser;
    }

    @PostMapping("/me")
    public ResponseEntity currentUser(@AuthenticationPrincipal Authentication authentication) {
        Map<Object, Object> model = new HashMap<>();
        User u = (User)authentication.getPrincipal();
        model.put("id", u.getId());
        model.put("username", u.getUsername());
        model.put("roles", u.getAuthorities()
                .stream()
                .map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(toList())
        );
        return ok(model);
    }

}
