package com.unitedremote.shops.Services;

import com.unitedremote.shops.DAO.Entities.Location;
import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.DAO.Entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Component
public class NerbyShopsImpl implements INearbyShopsService {

    @Autowired
    IShopService shopService;
    @Autowired
    IUserService userService;

    private Double calculateDistance(Location a, Location b){
        return Math.sqrt(Math.pow(a.getLat() - b.getLat() , 2) + Math.pow(a.getLon() - b.getLon() , 2) );
    }

    @Override
    public List<Shop> getNearbyShops(Location userLocation) {
        List<Shop> shops = shopService.getAllShops();
        List<Shop> ns = shops.stream()
                .sorted(Comparator.comparingDouble(shop -> calculateDistance(shop.getLocation(), userLocation)))
                .collect(toList());
        return ns;

    }
}
