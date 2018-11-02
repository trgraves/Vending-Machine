/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

/**
 *
 * @author tgraves
 */
public enum Coin {
    QUARTER(25), DIME(10), NICKEL(5);

    private final int VALUE;

    private Coin(int value) {
        VALUE = value;
    }

    public int getValue() {
        return this.VALUE;
    }

}
