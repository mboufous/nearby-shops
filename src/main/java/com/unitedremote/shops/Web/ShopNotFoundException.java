package com.unitedremote.shops.Web;

public class ShopNotFoundException extends RuntimeException {

    public ShopNotFoundException() {
    }

    public ShopNotFoundException(Long id) {
        super("Shop with id: "+id+" Not found.");
    }

    public ShopNotFoundException(String k) {
        super("Shop with id: "+k+" Not found.");
    }
}
