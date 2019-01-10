package com.unitedremote.shops.Utils;

import com.unitedremote.shops.DAO.Entities.Location;

public interface IRandomLocation {
    /*
     * Return Location from a webService (google map API, Random location(test), ...)
     * */
    Location fetch();
}
