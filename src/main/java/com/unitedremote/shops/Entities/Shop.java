package com.unitedremote.shops.Entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Shop implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shop_id")
    Long id;
    String name;
    @Embedded
    Location location;

}
