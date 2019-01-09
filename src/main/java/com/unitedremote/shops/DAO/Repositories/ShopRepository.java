package com.unitedremote.shops.DAO.Repositories;
import com.unitedremote.shops.DAO.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}
