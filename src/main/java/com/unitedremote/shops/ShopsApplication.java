package com.unitedremote.shops;

import com.unitedremote.shops.DAO.Entities.LikeState.LikeStateEnum;
import com.unitedremote.shops.DAO.Entities.Location;
import com.unitedremote.shops.DAO.Entities.Shop;
import com.unitedremote.shops.DAO.Entities.User;
import com.unitedremote.shops.DAO.Repositories.LikeStateShopRepository;
import com.unitedremote.shops.DAO.Repositories.ShopRepository;
import com.unitedremote.shops.DAO.Repositories.UserRepository;
import com.unitedremote.shops.Services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

@SpringBootApplication
public class ShopsApplication {

    Random random = new Random();

    public static void main(String[] args) {
        SpringApplication.run(ShopsApplication.class, args);
    }

    @Bean
    public CommandLineRunner initApp(UserRepository userRepository, ShopRepository shopRepository, LikeStateShopRepository likeStateShopRepository,
                                     IShopService shopService, IUserService userService, ILikeStateService likeStateService, INearbyShopsService nearbyShopsService) {
        return args -> {
            Stream.of("Mohamed", "Nihal", "Otmane", "Moustafa")
                    .forEach(userName -> userRepository.save(User.builder()
                            .name(userName)
                            .email(userName + "@gmail.com")
                            .roles(Arrays.asList("USER"))
                            .password(passwordEncoder().encode(userName))
                            .location(new Location(random.nextInt(100) + Math.random(), random.nextInt(100) + Math.random()))
                            .build()
                    ));

            Stream.of("Marjane", "IKEA", "Carfourre", "Acima", "Aswak Essalam", "Walmart")
                    .forEach(shopName -> shopRepository.save(Shop.builder()
                            .name(shopName)
                            .location(new Location(random.nextInt(100) + Math.random(), random.nextInt(100) + Math.random()))
                            .build()));

//            userRepository.findAll().forEach(System.out::println);
//            shopRepository.findAll().forEach(System.out::println);
//
            likeStateService.likeShop(1L, 5L);
//            likeStateService.likeShop(2L, 5L);
//            likeStateService.likeShop(3L, 5L);
            likeStateService.likeShop(1L, 6L);
//            likeStateService.dislikeShop(1L, 7L);
//            likeStateService.likeShop(2L, 5L);
            //System.out.println(nearbyShopsService.getNearbyShops());
            System.out.println(likeStateService.getLikedShops(1L));
            likeStateService.removeLikeShop(1L, 5L);
            System.out.println(likeStateService.getLikedShops(1L));




        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}

