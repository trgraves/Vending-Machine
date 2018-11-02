/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tgraves
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao {

    private Map<Integer, Item> itemMap = new HashMap<>();

    public VendingMachineDaoInMemImpl() {
        loadInventory();
    }

    @Override
    public Item getItemById(int itemId) {
        return itemMap.get(itemId);
    }

    @Override
    public Item createItem(Item item) {
        return itemMap.put(item.getItemId(), item);
    }

    @Override
    public void removeItem(int itemId) {
        itemMap.remove(itemId);
    }

    @Override
    public void updateItem(Item item) {
        itemMap.put(item.getItemId(), item);
    }

    @Override
    public List<Item> getAllItems() {
        return new ArrayList(itemMap.values());
    }

    @Override
    public void loadInventory() {
        Item snickers = new Item(1, "Snickers", new BigDecimal("1.85"), 9);
        Item mAndMs = new Item(2, "M&Ms", new BigDecimal("1.50"), 2);
        Item pringles = new Item(3, "Pringles", new BigDecimal("2.10"), 5);
        Item reeses = new Item(4, "Reese's", new BigDecimal("1.85"), 4);
        Item pretzels = new Item(5, "Pretzels", new BigDecimal("1.25"), 9);
        Item twinkies = new Item(6, "Twinkies", new BigDecimal("1.95"), 3);
        Item doritos = new Item(7, "Doritos", new BigDecimal("1.75"), 11);
        Item almondJoy = new Item(8, "Almond Joy", new BigDecimal("1.85"), 0);
        Item trident = new Item(9, "Trident", new BigDecimal("1.95"), 6);

        itemMap.put(snickers.getItemId(), snickers);
        itemMap.put(mAndMs.getItemId(), mAndMs);
        itemMap.put(pringles.getItemId(), pringles);
        itemMap.put(reeses.getItemId(), reeses);
        itemMap.put(pretzels.getItemId(), pretzels);
        itemMap.put(twinkies.getItemId(), twinkies);
        itemMap.put(doritos.getItemId(), doritos);
        itemMap.put(almondJoy.getItemId(), almondJoy);
        itemMap.put(trident.getItemId(), trident);
    }

}
