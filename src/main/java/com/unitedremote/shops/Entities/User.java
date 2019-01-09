package com.unitedremote.shops.Entities;

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
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "shop_id")
    List<Shop> favoriteShops = new ArrayList<>();
}
