package com.unitedremote.shops.Services;

import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateEnum;
import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShop;
import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShopPrimaryKey;
import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.DAO.Repositories.LikeStateShopRepository;
import com.unitedremote.shops.DAO.Repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopServiceImpl implements IShopService{

    @Autowired
    ShopRepository shopRepository;

    @Override
    public List<Shop> findShopByName(String keyword) {
        return shopRepository.findByNameContainingIgnoreCase(keyword);
    }

    @Override
    public Optional<Shop> getShop(Long id) {
        return shopRepository.findById(id);
    }

    @Override
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public void deleteShop(Long id) {
        shopRepository.deleteById(id);
    }



}