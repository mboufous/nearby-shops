package com.unitedremote.shops.Web.Controllers;

import com.unitedremote.shops.Web.ShopNotFoundException;
import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.Services.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;

@RestController
@RequestMapping("v1/shops")
public class ShopController {

    private final IShopService shopService;

    @Autowired
    public ShopController(IShopService shopService) {
        this.shopService = shopService;
    }

    /*
     * Get All shops
     * */
    @GetMapping("")
    List<Shop> getAllShops() {
        return shopService.getAllShops();
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
     * Get Number of likes by shop id
     * */
    @GetMapping("/{id}/likes")
    Long getLikesCount(@PathVariable Long id) {
        return shopService.getLikesCount(id);
    }

    /*
     * Get Number of dislikes by shop id
     * */
    @GetMapping("/{id}/dislikes")
    Long getDisLikesCount(@PathVariable Long id) {
        return shopService.getDislikesCount(id);
    }

    /*
     * find shop by name
     * */
    @GetMapping("/find/{k}")
    List<Shop> findShopByName(@PathVariable String k) {
        return shopService.findShopByName(k);
    }


}
