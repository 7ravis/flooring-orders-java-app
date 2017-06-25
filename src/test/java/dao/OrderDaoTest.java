/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package dao;

import dto.Order;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Travis Rogers
 */
public class OrderDaoTest {
    
    public OrderDaoTest() {
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
     * Test of open method, of class OrderDao.
     */
    @Test
    public void testOpen() throws Exception {
        OrderDao instance = new OrderDaoImpl("train");
        try {
            instance.open();
        } catch (DataPersistenceException e) {}
        assertTrue("open/getOrders",instance.getOrders() != null);
    }
    
    /**
     * Test of close method, of class OrderDao.
     */
    @Test
    public void testClose() throws Exception {
        OrderDao instance = new OrderDaoImpl("prod");
        try {
            instance.open();
        } catch (DataPersistenceException e) {}
        try {
        instance.close();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown");
        }
    }
    
    /**
     * Test of saveCurrentWork method, of class OrderDao.
     */
    @Test
    public void testSaveCurrentWork() throws Exception {
        OrderDao instance = new OrderDaoImpl("prod");
        try {
            instance.open();
        } catch (DataPersistenceException e) {}
        try {
        instance.saveCurrentWork();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown");
        }
    }
    
    /**
     * Test of getOrdersByDate method, of class OrderDao.
     */
    @Test
    public void testGetOrdersByDate() {
        OrderDao instance = new OrderDaoImpl("train");
        try {
            instance.open();
        } catch (DataPersistenceException e) {}
        assertTrue("getOrdersByDate",instance.getOrdersByDate(LocalDate.parse("1111-01-01")).size() == 1);
    }
    
    /**
     * Test of getOrder method, of class OrderDao.
     */
    @Test
    public void testGetOrder() {
        OrderDao instance = new OrderDaoImpl("train");
        try {
            instance.open();
        } catch (DataPersistenceException e) {}
        Order order = instance.getOrder(LocalDate.parse("1111-01-01"), 1);
        assertTrue("getOrderCompareNum",order.getOrderNum() == 1);
        assertTrue("getOrderCompareName",order.getCustomerName().equals("Target"));
        assertTrue("getOrderCompareStateAbbr", order.getState().getStateAbbr().equals("PA"));
        assertTrue("getOrderCompareStateTax", order.getState().getTaxRate().compareTo(new BigDecimal("0.0675")) == 0);
        assertTrue("getOrderCompareProductType", order.getProduct().getType().equals("Tile"));
        assertTrue("getOrderCompareOrderTotal", order.getOrderTotal().compareTo(new BigDecimal("453.64")) == 0);
    }
    
    /**
     * Test of addOrder method, of class OrderDao.
     */
    @Test
    public void testAddOrder() {
        OrderDao instance = new OrderDaoImpl("train");
        try {
            instance.open();
        } catch (DataPersistenceException e) {}
        Order order = new Order(2147483647);
        order.setCustomerName("John Smith");
        assertTrue(instance.addOrder(order));
        Order returnedOrder = instance.getOrder(LocalDate.now(), 2147483647);
        assertTrue(order.getOrderNum() == returnedOrder.getOrderNum());
        assertTrue(order.getCustomerName().equals(returnedOrder.getCustomerName()));
    }
    
    /**
     * Test of removeOrder method, of class OrderDao.
     */
    @Test
    public void testRemoveOrder() {
        OrderDao instance = new OrderDaoImpl("train");
        try {
            instance.open();
        } catch (DataPersistenceException e) {}
        Order order = new Order(2147483647);
        order.setCustomerName("John Smith");
        assertTrue(instance.addOrder(order));
        assertTrue(instance.getOrder(LocalDate.now(), 2147483647) != null);
        assertTrue(instance.removeOrder(order, LocalDate.now()));
        assertTrue(instance.getOrder(LocalDate.now(),2147483647) == null);
    }
    
}
