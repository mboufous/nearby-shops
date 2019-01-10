package com.unitedremote.shops.Services;

import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateEnum;
import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShop;
import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShopPrimaryKey;
import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.DAO.Entities.User;
import com.unitedremote.shops.DAO.Repositories.LikeStateShopRepository;
import com.unitedremote.shops.DAO.Repositories.ShopRepository;
import com.unitedremote.shops.DAO.Repositories.UserRepository;
import com.unitedremote.shops.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.el.MethodNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService, ILikeStateService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    LikeStateShopRepository likeRepository;
    @Autowired
    ShopRepository shopRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenProvider jwtTokenProvider;
    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).orElseThrow(()->new UsernameNotFoundException("with id:"+id));
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
        User user = userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException(email));
//        if (user.getRoles() == null) System.out.println("NULL");
        user.setRoles(Collections.singletonList("USER"));
    }

    private void likeStateBuilder(Long userId, Long shopId, LikeStateEnum state){
        likeRepository.save(LikeStateShop.builder().id(new LikeStateShopPrimaryKey(userId, shopId))
                .user(getUser(userId))
                .shop(shopRepository.getOne(shopId))
                .likeState(state)
                .build());
    }


    @Override
    public void likeShop(Long userId, Long shopId) {
        this.likeStateBuilder(userId, shopId, LikeStateEnum.Like);
    }

    @Override
    public void dislikeShop(Long userId, Long shopId) {
        this.likeStateBuilder(userId, shopId, LikeStateEnum.Dislike);
    }

    @Override
    public List<Shop> getLikedShops(Long userId) {
        return shopRepository.findShopsByUserIdAndLikeState(userId, LikeStateEnum.Like);
    }

    @Override
    public List<Shop> getDislikedShops(Long userId) {
        return shopRepository.findShopsByUserIdAndLikeState(userId, LikeStateEnum.Dislike);
    }

    @Override
    public List<Shop> getNotLikedShops(Long userId) {
        return shopRepository.findShopsNotCurrentLikeState(userId, LikeStateEnum.Like);
    }
}
