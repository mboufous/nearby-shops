package com.unitedremote.shops.DAO.Entities.LikeState;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeStateShopPrimaryKey implements Serializable {
    @Column(name = "user_id")
    Long userId;
    @Column(name = "shop_id")
    Long shopId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LikeStateShopPrimaryKey)) return false;
        LikeStateShopPrimaryKey that = (LikeStateShopPrimaryKey) o;
        return Objects.equals(getUserId(), that.getUserId()) &&
                Objects.equals(getShopId(), that.getShopId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getShopId());
    }
}
