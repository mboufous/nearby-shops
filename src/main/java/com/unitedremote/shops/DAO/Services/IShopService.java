package com.unitedremote.shops.DAO.Services;

import com.unitedremote.shops.DAO.Entities.Shop;

import java.util.List;
import java.util.Optional;

public interface IShopService {
    Shop findShopByName(String keyword);

    Optional<Shop> getShop(Long id);

    List<Shop> getAllShops();

    Integer getLikesCount(Long id);

    Integer getDislikesCount(Long id);

    Shop save(Shop shop);

    void deleteShop(Long id);
}
