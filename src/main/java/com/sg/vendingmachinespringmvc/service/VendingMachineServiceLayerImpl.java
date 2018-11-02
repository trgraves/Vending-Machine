/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author tgraves
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private VendingMachineDao dao;

    @Inject
    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public Item getItemById(int itemId) {
        return dao.getItemById(itemId);
    }

    @Override
    public Item createItem(Item item) {
        return dao.createItem(item);
    }

    @Override
    public void removeItem(int itemId) {
        dao.removeItem(itemId);
    }

    @Override
    public void updateItem(Item item) {
        dao.updateItem(item);
    }

    @Override
    public List<Item> getAllItems() {
        return dao.getAllItems();
    }

    @Override
    public void loadInventory() {
        dao.loadInventory();
    }

    @Override
    public void validateItemStock(Item item) throws VendingMachineOutOfStockException {
        if (item.getQuantity() < 1) {
            throw new VendingMachineOutOfStockException("SOLD OUT!!!");
        }
    }

    @Override
    public void validateFunds(BigDecimal money, Item item) throws VendingMachineInsufficientFundsException {
        if (item.getPrice().compareTo(money) > 0) {
            throw new VendingMachineInsufficientFundsException("Please deposit: $" + item.getPrice().subtract(money));
        }
    }

}
