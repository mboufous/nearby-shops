package com.unitedremote.shops.DAO.Entities.LikeState;

import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.DAO.Entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeStateShop implements Serializable {
    @EmbeddedId
    LikeStateShopPrimaryKey id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("shop_id")
    @JoinColumn(name = "shop_id")
    Shop shop;

    LikeStateEnum likeState = LikeStateEnum.None;
}
