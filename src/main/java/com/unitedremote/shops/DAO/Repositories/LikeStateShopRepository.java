package com.unitedremote.shops.DAO.Repositories;

import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateEnum;
import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeStateShopRepository extends JpaRepository<LikeStateShop, Long> {
    Long countByShopIdAndLikeState(Long shopId, LikeStateEnum likeState);

}
