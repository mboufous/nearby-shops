package com.unitedremote.shops.DAO.Entities;

import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateShop;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String name;
    @Embedded
    Location location;
    @OneToMany(mappedBy = "shop")
    @ToString.Exclude
    List<LikeStateShop> likes;

}
