package com.unitedremote.shops.DAO.Controllers;

public class ShopNotFoundException extends RuntimeException {
    public ShopNotFoundException(Long id) {
        System.err.println("Shop with id: "+id+" Not found.");
    }

    public ShopNotFoundException(String k) {
        System.err.println("Shop with id: "+k+" Not found.");
    }
}
