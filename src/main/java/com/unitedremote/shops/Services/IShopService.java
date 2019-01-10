package com.unitedremote.shops.Services;

import com.unitedremote.shops.DAO.Entities.Shop;

import java.util.List;
import java.util.Optional;

public interface IShopService {
    List<Shop> findShopByName(String keyword);

    Optional<Shop> getShop(Long id);

    List<Shop> getAllShops();

    Shop save(Shop shop);

    void deleteShop(Long id);
}
