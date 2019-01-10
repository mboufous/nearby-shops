package com.unitedremote.shops.Web.Controllers;

import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.Services.ILikeStateService;
import com.unitedremote.shops.Utils.PrincipalUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/likestate")
public class UserLikeStateController {

    @Autowired
    PrincipalUser connectedUser;
    @Autowired
    ILikeStateService likeStateService;

    public UserLikeStateController() {

    }

    @GetMapping("/hello")
    public ResponseEntity hello() {
        System.out.println(connectedUser.getAuthUser());
        return ResponseEntity.ok("Hello");
    }

    @GetMapping("/like")
    public List<Shop> userLikedShops() {
        System.out.println("connected id::" + connectedUser.getAuthUser().getId());
        System.out.println(likeStateService.getLikedShops(1L));
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

    /*
     * Get Number of likes by shop id
     * */
    @GetMapping("/likes/{id}")
    Long getLikesCount(@PathVariable Long id) {
        return likeStateService.getLikesCount(id);
    }

    /*
     * Get Number of dislikes by shop id
     * */
    @GetMapping("/dislikes/{id}")
    Long getDisLikesCount(@PathVariable Long id) {
        return likeStateService.getDislikesCount(id);
    }

    @PostMapping("/like/{id}")
    public void likeShop(@PathVariable Long id) {
        likeStateService.likeShop(connectedUser.getAuthUser().getId(), id);
    }

    @PostMapping("/dislike/{id}")
    public void disLikeShop(@PathVariable Long id) {
        likeStateService.dislikeShop(connectedUser.getAuthUser().getId(), id);
    }


}
