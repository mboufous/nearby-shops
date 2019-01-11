package com.unitedremote.shops.Services;

import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateEnum;
import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShop;
import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShopPrimaryKey;
import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.DAO.Repositories.LikeStateShopRepository;
import com.unitedremote.shops.DAO.Repositories.ShopRepository;
import com.unitedremote.shops.DAO.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeStateService implements ILikeStateService {

    LikeStateShopRepository likeRepository;
    ShopRepository shopRepository;
    UserRepository userRepository;

    LikeStateService(LikeStateShopRepository likeRepository, ShopRepository shopRepository, UserRepository userRepository){
        this.likeRepository = likeRepository;
        this.shopRepository = shopRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Long getLikesCount(Long id) {
        return likeRepository.countByShopIdAndLikeState(id, LikeStateEnum.Like);
    }

    @Override
    public Long getDislikesCount(Long id) {
        return likeRepository.countByShopIdAndLikeState(id, LikeStateEnum.Dislike);
    }
    private void likeStateBuilder(Long userId, Long shopId, LikeStateEnum state){
        likeRepository.save(LikeStateShop.builder().id(new LikeStateShopPrimaryKey(userId, shopId))
                .user(userRepository.getOne(userId))
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
    public void removeLikeShop(Long userId, Long shopId) {
        likeRepository.deleteById(new LikeStateShopPrimaryKey(userId, shopId));
    }

    @Override
    public List<Shop> getNotLikedShops(Long userId) {
        return shopRepository.findShopsNotCurrentLikeState(userId, LikeStateEnum.Like);
    }

}
