package com.unitedremote.shops.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
class LikeStateShop implements Serializable {
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
