/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.model.Coin;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.VendingMachineOutOfStockException;
import com.sg.vendingmachinespringmvc.service.VendingMachineServiceLayer;
import java.math.BigDecimal;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tgraves
 */
@Controller
public class VendingMachineController {

    private VendingMachineServiceLayer service;
    private BigDecimal totalMoney = new BigDecimal("0.00");
    private Item selectedItem;
    private int quarters;
    private int dimes;
    private int nickels;
    private String message;

    @Inject
    public VendingMachineController(VendingMachineServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        // Get items for item-container div
        List<Item> itemList = service.getAllItems();
        // Add variables to model for index.jsp
        model.addAttribute("itemList", itemList);
        model.addAttribute("totalMoney", totalMoney);
        model.addAttribute("selectedItem", selectedItem);
        model.addAttribute("quarters", quarters);
        model.addAttribute("dimes", dimes);
        model.addAttribute("nickels", nickels);
        model.addAttribute("message", message);
        return "index";
    }

    // Each add button updates totalMoney with each of their values
    @RequestMapping(value = "/addMoney", method = RequestMethod.GET)
    public String addMoney(HttpServletRequest request) {
        String moneyString = request.getParameter("moneyValue");
        // In case user edits href attribute checks if parameter can be parsed
        // as a BigDecimal. Then checks if value is 1, .25, .10, or .05
        BigDecimal money = InputValidation.validateBigDecimal(moneyString);
        if (money != null) {
            totalMoney = totalMoney.add(money);
            resetChange();
            resetMessage();
        } else {
            message = "Error adding money.";
        }
        return "redirect:/";
    }

    // Updates selectedItem when item button is clicked
    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request) {
        String itemIdString = request.getParameter("itemId");
        // In case user edits the href attribute checks if parameter is an Integer
        Integer itemId = InputValidation.validateInteger(itemIdString);
        if (itemId != null) {
            selectedItem = service.getItemById(itemId);
            // In case user edits href attribute and the service can't find the item
            if (selectedItem != null) {
                resetChange();
                resetMessage();
            } else {
                message = "Error selecting item.";
            }            
        } else {
            message = "Error selecting item.";
        }

        return "redirect:/";
    }

    // Returns change and resets totalMoney to 0
    @RequestMapping(value = "/returnChange", method = RequestMethod.GET)
    public String returnChange(HttpServletRequest request) {
        makeChange(totalMoney);
        resetMessage();
        return "redirect:/";
    }

    // Vends item if user has made a selection. Throws errors if item is 
    // out of stock or insufficient funds.
    @RequestMapping(value = "/purchaseItem", method = RequestMethod.GET)
    public String purchaseItem(HttpServletRequest request) {
        if (selectedItem != null) {
            // Validates item stock and funds, throws out of stock and insufficient funds
            // if there are errors. If validation is good, subtracts item cost from change
            // then returns change, updates item stock, and displays "thank you" message
            try {
                service.validateItemStock(selectedItem);
                service.validateFunds(totalMoney, selectedItem);
                BigDecimal change = totalMoney.subtract(selectedItem.getPrice());
                makeChange(change);
                selectedItem.setQuantity(selectedItem.getQuantity() - 1);
                service.updateItem(selectedItem);
                message = "Thank You!!!";
            } catch (VendingMachineOutOfStockException | VendingMachineInsufficientFundsException e) {
                message = e.getMessage();
            }
        } else {
            message = "Please select an item.";
        }
        return "redirect:/";
    }

    // Helper function to calculate how much change to return
    private void makeChange(BigDecimal changeBD) {
        // Multiply by 100 to remove decimal for easy math
        changeBD = changeBD.multiply(new BigDecimal("100.00"));
        int change = changeBD.intValue();
        // divide change by coin values for how many of each coin to return
        // then subtract from change to update how much change is left
        quarters = change / Coin.QUARTER.getValue();
        change -= quarters * Coin.QUARTER.getValue();
        dimes = change / Coin.DIME.getValue();
        change -= dimes * Coin.DIME.getValue();
        nickels = change / Coin.NICKEL.getValue();
        // Reset totalMoney to 0 after change return
        totalMoney = totalMoney.subtract(totalMoney);
    }

    private void resetChange() {
        // Reset change to 0 so change form is accurate
        quarters = 0;
        dimes = 0;
        nickels = 0;
    }

    private void resetMessage() {
        message = "";
    }
}
