package com.example.lyy.dao;

import com.example.lyy.pojo.Shopper;

public interface ShopMapper {
    public int registerShop(Shopper shopper);
    public Shopper findShopperByname(String name);
}
