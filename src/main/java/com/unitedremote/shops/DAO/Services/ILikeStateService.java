package com.unitedremote.shops.DAO.Services;

import com.unitedremote.shops.DAO.Entities.Shop;

import java.util.List;

public interface ILikeStateService {

    public void likeShop(Long userId, Long shopId);

    public void dislikeShop(Long userId, Long shopId);

    public List<Shop> getLikedShops(Long userId);

    public List<Shop> getDislikedShops(Long userId);
}
