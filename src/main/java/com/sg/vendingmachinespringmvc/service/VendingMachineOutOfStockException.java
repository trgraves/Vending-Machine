/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

/**
 *
 * @author tgraves
 */
public class VendingMachineOutOfStockException extends Exception {

    public VendingMachineOutOfStockException(String msg) {
        super(msg);
    }

    public VendingMachineOutOfStockException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
