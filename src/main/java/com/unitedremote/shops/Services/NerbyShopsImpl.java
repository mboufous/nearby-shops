package com.unitedremote.shops.Services;

import com.unitedremote.shops.DAO.Entities.Location;
import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.DAO.Entities.User;
import com.unitedremote.shops.Utils.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    ILikeStateService likeStateService;
    @Autowired
    PrincipalUser principalUser;

    private Double calculateDistance(Location a, Location b){
        return Math.sqrt(Math.pow(a.getLat() - b.getLat() , 2) + Math.pow(a.getLon() - b.getLon() , 2) );
    }


    @Override
    public List<Shop> getNearbyShops() {
//        List<Shop> shops = shopService.getAllShops();
        List<Shop> shops = likeStateService.getNotLikedShops(principalUser.getAuthUser().getId());
        System.out.println(shops);
        List<Shop> ns = shops.stream()
                .sorted(Comparator.comparingDouble(shop -> calculateDistance(shop.getLocation(), principalUser.getAuthUser().getLocation())))
                .collect(toList());
        return ns;
    }
}
