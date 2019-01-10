package com.unitedremote.shops.DAO.Entities;

import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShop;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    String email;
    @Embedded
    Location location;
    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    List<LikeStateShop> likes = new ArrayList<>();
}
