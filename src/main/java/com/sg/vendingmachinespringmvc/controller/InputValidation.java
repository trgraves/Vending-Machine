/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import java.math.BigDecimal;

/**
 *
 * @author tgraves
 */
public class InputValidation {

    // Validates add money buttons in case user edits href attribute
    // and only excepts values of 1, .25, .10, .05
    public static BigDecimal validateBigDecimal(String numberAsStr) {
        BigDecimal number;
        try {
            number = new BigDecimal(numberAsStr);
            if(number.equals(new BigDecimal("1")) || number.equals(new BigDecimal(".25")) ||
               number.equals(new BigDecimal(".10")) || number.equals(new BigDecimal(".05"))) {
                return number;
            } else {
                number = null;
            }
        } catch (NumberFormatException e) {
            number = null;
        }
        return number;
    }
    
    // Validates item buttons in case user edits href attribute
    public static Integer validateInteger(String numberAsStr) {
        Integer number;
        try {
            number = Integer.parseInt(numberAsStr);
        } catch (NumberFormatException e) {
            number = null;
        }
        return number;
    }
}
