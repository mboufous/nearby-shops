package com.unitedremote.shops.Utils;

import com.unitedremote.shops.DAO.Entities.Location;

import java.util.Random;

public class RandomLocationSingleton implements IRandomLocation {
    private Random randomInteger = new Random();
    private static RandomLocationSingleton Instance = new RandomLocationSingleton();

    public static RandomLocationSingleton getInstance() {
        return Instance;
    }

    private RandomLocationSingleton() {
    }


    @Override
    public Location fetch() {

        return new Location(Math.random() + randomInteger.nextInt(99),
                Math.random() + randomInteger.nextInt(99));
    }
}
