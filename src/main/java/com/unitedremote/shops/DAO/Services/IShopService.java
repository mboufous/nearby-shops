package com.unitedremote.shops.DAO.Services;

import com.unitedremote.shops.DAO.Entities.Shop;

import java.util.List;
import java.util.Optional;

public interface IShopService {
    List<Shop> findShopByName(String keyword);

    Optional<Shop> getShop(Long id);

    List<Shop> getAllShops();

    Long getLikesCount(Long id);

    Long getDislikesCount(Long id);

    Shop save(Shop shop);

    void deleteShop(Long id);
}
