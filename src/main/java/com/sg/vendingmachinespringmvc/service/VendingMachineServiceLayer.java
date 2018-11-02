/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author tgraves
 */
public interface VendingMachineServiceLayer {

    public Item getItemById(int itemId);

    public Item createItem(Item item);

    public void removeItem(int itemId);

    public void updateItem(Item item);

    public List<Item> getAllItems();

    public void loadInventory();

    public void validateItemStock(Item item) throws VendingMachineOutOfStockException;

    public void validateFunds(BigDecimal money, Item item) throws VendingMachineInsufficientFundsException;
}
