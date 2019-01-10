package com.unitedremote.shops.DAO.Repositories;
import com.unitedremote.shops.DAO.Entities.*;
import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource
public interface ShopRepository extends JpaRepository<Shop, Long> {
    Optional<Shop> findByNameContains(@Param("k") String keyword);
    Integer findAllByIdAndLikesEquals(Long id, LikeStateEnum likeState);
}
