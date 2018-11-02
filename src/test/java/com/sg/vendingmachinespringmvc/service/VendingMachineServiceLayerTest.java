/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tgraves
 */
public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        service = ctx.getBean("service", VendingMachineServiceLayerImpl.class);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of validateItemStock method, of class VendingMachineServiceLayer.
     */
    @Test(expected = VendingMachineOutOfStockException.class)
    public void testValidateItemStock() throws Exception {
        Item newItem = new Item(1, "Snickers", new BigDecimal("1.00"), 0);
        service.validateItemStock(newItem);
    }

    /**
     * Test of validateFunds method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testValidateFunds() throws Exception {
        Item newItem = new Item(1, "Snickers", new BigDecimal("1.00"), 5);
        BigDecimal money = new BigDecimal(".75");
        try {
            service.validateFunds(money, newItem);
            fail("Expected VendingMachineInsufficientFundsException was not thrown");
        } catch (VendingMachineInsufficientFundsException e) {
            return;
        }
    }

    // All other methods in service are pass through methods to the dao
}
