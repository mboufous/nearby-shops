package com.unitedremote.shops.Services;

import com.unitedremote.shops.DAO.Entities.Location;
import com.unitedremote.shops.DAO.Entities.Shop;

import java.util.List;

public interface INearbyShopsService {
    List<Shop> getNearbyShops();
}
