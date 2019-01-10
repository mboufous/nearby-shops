package com.unitedremote.shops.DAO.Services;

import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateEnum;
import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShop;
import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShopPrimaryKey;
import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.DAO.Entities.User;
import com.unitedremote.shops.DAO.Repositories.LikeStateShopRepository;
import com.unitedremote.shops.DAO.Repositories.ShopRepository;
import com.unitedremote.shops.DAO.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.MethodNotAllowedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.el.MethodNotFoundException;
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

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User register(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User login(String email, String password) {
        throw new MethodNotFoundException();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.delete(getUser(id));
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        return userRepository.save(updatedUser);
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
        return shopRepository.findShopByUserIdAndLikeState(userId, LikeStateEnum.Like);
    }

    @Override
    public List<Shop> getDislikedShops(Long userId) {
        return shopRepository.findShopByUserIdAndLikeState(userId, LikeStateEnum.Dislike);
    }
}
