package com.unitedremote.shops.DAO.Entities.LikeState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class LikeStateShopPrimaryKey implements Serializable {
    Long user_id;
    Long shop_id;
}
