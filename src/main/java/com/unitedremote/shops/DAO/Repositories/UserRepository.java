package com.unitedremote.shops.DAO.Repositories;

import com.unitedremote.shops.DAO.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Component;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User, Long> {
}
