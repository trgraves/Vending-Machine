/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tgraves
 */
public class VendingMachineDaoTest {

    private VendingMachineDao dao;

    public VendingMachineDaoTest() {
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
        dao = ctx.getBean("dao", VendingMachineDao.class);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void createGetRemoveItem() {
        Item newItem = new Item(1, "Snickers", new BigDecimal("1.50"), 5);
        dao.createItem(newItem);
        Item fromDao = dao.getItemById(newItem.getItemId());
        assertEquals(newItem, fromDao);
        dao.removeItem(newItem.getItemId());
        assertNull(dao.getItemById(newItem.getItemId()));
    }

    @Test
    public void createUpdateItem() {
        Item newItem = new Item(1, "Snickers", new BigDecimal("1.50"), 5);
        dao.createItem(newItem);
        newItem.setName("Reese's");
        dao.updateItem(newItem);
        Item fromDao = dao.getItemById(newItem.getItemId());
        assertEquals(newItem, fromDao);
    }

    @Test
    public void getAllItemsLoadInventory() {
        // loadInventory called in the constructor of VendingMachineDaoInMemImpl
        assertEquals(dao.getAllItems().size(), 9);
    }
}
