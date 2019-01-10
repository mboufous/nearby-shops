package com.unitedremote.shops.DAO.Repositories;

import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateEnum;
import com.unitedremote.shops.DAO.Entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> findByNameContainingIgnoreCase(@Param("k") String keyword);

    @Query("SELECT l.shop FROM LikeStateShop l where l.user.id=?1 AND l.likeState = ?2")
    List<Shop> findShopsByUserIdAndLikeState(Long userId, LikeStateEnum likeState);

    @Query("SELECT l.shop FROM LikeStateShop l where l.user.id=?1 AND l.likeState != ?2")
    List<Shop> findShopsNotCurrentLikeState(Long userId, LikeStateEnum currentLikeState);

}
