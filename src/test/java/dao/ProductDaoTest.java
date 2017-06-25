/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.Product;
import java.math.BigDecimal;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Travis Rogers
 */
public class ProductDaoTest {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContextTest.xml");
    ProductDao instance = ctx.getBean("ProductDao", ProductDaoImpl.class);
    
    public ProductDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of open method, of class ProductDao.
     */
    @Test
    public void testOpen() throws Exception {
        try {
        instance.open();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown during open() method.");
        }
        assertTrue(instance.getProducts() != null);
    }

    /**
     * Test of close method, of class ProductDao.
     */
    @Test
    public void testClose() throws Exception {
        try {
        instance.open();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown during open() method.");
        }
        try {
            instance.close();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown during close() method.");
        }
    }

    /**
     * Test of getProducts method, of class ProductDao.
     */
    @Test
    public void testGetProducts() {
        try {
        instance.open();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown during open() method.");
        }
        assertTrue(instance.getProducts().size() == 4);
    }

    /**
     * Test of getProductByType method, of class ProductDao.
     */
    @Test
    public void testGetProductByType() {
        try {
        instance.open();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown during open() method.");
        }
        Product product = instance.getProductByType("Tile");
        assertTrue(product.getType().equals("Tile"));
        assertTrue(product.getMatCostPerSqFt().compareTo(new BigDecimal("3.50")) == 0);
        assertTrue(product.getLaborCostPerSqFt().compareTo(new BigDecimal("4.15")) == 0);
    }
}
