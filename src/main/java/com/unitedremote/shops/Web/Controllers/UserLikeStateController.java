package com.unitedremote.shops.Web.Controllers;

import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.DAO.Entities.User;
import com.unitedremote.shops.Services.ILikeStateService;
import com.unitedremote.shops.Utils.PrincipaleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/likestate")
public class UserLikeStateController {

    @Autowired
    PrincipaleUser connectedUser ;
    @Autowired
    ILikeStateService likeStateService;

    public UserLikeStateController(){

    }

    @GetMapping({"/like", ""})
    public List<Shop> userLikedShops() {
        return likeStateService.getLikedShops(connectedUser.getAuthUser().getId());
    }

    @GetMapping("/dislike")
    public List<Shop> userDisLikedShops() {
        return likeStateService.getDislikedShops(connectedUser.getAuthUser().getId());
    }


    /*
     * Show only Shops with None and Dislike State
     * */
    @GetMapping("/none")
    public List<Shop> userNoneShops() {
        return likeStateService.getNotLikedShops(connectedUser.getAuthUser().getId());
    }

    @PostMapping("/like/{id}")
    public void likeShop(@PathVariable Long shopId){
        likeStateService.likeShop(connectedUser.getAuthUser().getId(), shopId);
    }

    @PostMapping("/dislike/{id}")
    public void disLikeShop(@PathVariable Long shopId){
        likeStateService.dislikeShop(connectedUser.getAuthUser().getId(), shopId);
    }

}
