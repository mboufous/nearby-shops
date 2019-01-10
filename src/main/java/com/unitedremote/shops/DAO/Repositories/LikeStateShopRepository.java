package com.unitedremote.shops.DAO.Repositories;

import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateEnum;
import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShop;
import com.unitedremote.shops.DAO.Entities.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface LikeStateShopRepository extends JpaRepository<LikeStateShop, Long> {
    //    Integer countByUserIdAndLikeState(Long id, LikeStateEnum likeState);
//    SELECT e FROM Employee e JOIN FETCH e.address
    @Query("SELECT l.shop FROM LikeStateShop l where l.user.id=?1 AND l.likeState = ?2")
    List<Shop> findShopByUserIdAndLikeState(Long userId, LikeStateEnum likeState);

}
