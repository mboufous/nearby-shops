package com.unitedremote.shops.Web.Controllers;

import com.unitedremote.shops.DAO.Entities.User;
import com.unitedremote.shops.Services.INearbyShopsService;
import com.unitedremote.shops.Web.ShopNotFoundException;
import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.Services.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@RestController
@RequestMapping("v1/shops")
public class ShopController {

    private final IShopService shopService;
    private final INearbyShopsService nearbyShopsService;

    @Autowired
    public ShopController(IShopService shopService, INearbyShopsService nearbyShopsService) {
        this.shopService = shopService;
        this.nearbyShopsService = nearbyShopsService;
    }

    /*
     * Get All shops
     * */
    @GetMapping("/")
    List<Shop> getAllShops() {
//        return shopService.getAllShops();
        throw new NotImplementedException();
    }


    /*
     * Get One shop by id
     * */
    @GetMapping("/{id}")
    Shop getShopById(@PathVariable Long id) {

        return shopService.getShop(id)
                .orElseThrow(() -> new ShopNotFoundException(id));
    }

    /*
     * Update Existing Shop or create a new one if not
     * */
    @PutMapping("/{id}")
    Shop replaceShop(@RequestBody Shop newShop, @PathVariable Long id) {
        throw new NotImplementedException();
    }

    /*
     * Add a new Shop
     * */
    @PostMapping("/{id}")
    Shop newShop(@RequestBody Shop newShop, @PathVariable Long id) {
        throw new NotImplementedException();
    }

    /*
     * Delete a shop by id
     * */
    @DeleteMapping("/{id}")
    void deleteShop(@PathVariable Long shopId) {
        throw new NotImplementedException();
    }

    /*
     * find shop by name
     * */
    @GetMapping("/find/{k}")
    List<Shop> findShopByName(@PathVariable String k) {
        return shopService.findShopByName(k);
    }

    @GetMapping("")
    List<Shop> getNearbyShops(@AuthenticationPrincipal Authentication auth){
        return nearbyShopsService.getNearbyShops(((User)auth.getPrincipal()).getLocation());
    }


}
