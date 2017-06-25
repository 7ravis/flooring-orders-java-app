/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.DataPersistenceException;
import dao.OrderDao;
import dao.OrderDaoStubImpl;
import dto.Order;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Travis Rogers
 */
public class OrderServiceTest {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContextTest.xml");
    OrderDao orderDao = ctx.getBean("OrderDaoStub", OrderDaoStubImpl.class);
    OrderService instance = ctx.getBean("OrderService", OrderServiceImpl.class);
    
    public OrderServiceTest() {
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
        orderDao.getOrders().clear();
    }

    /**
     * Test of getNextOrderNum method, of class OrderService.
     */
    @Test
    public void testGetNextOrderNumCurrentDay() throws DataPersistenceException {
        orderDao.open();
        assertTrue(instance.getNextOrderNum() == 1);
    }
    @Test
    public void testGetNextOrderNumNewDay() {
        assertTrue(instance.getNextOrderNum() == 1);
    }

    /**
     * Test of calculateCosts method, of class OrderService.
     */
    @Test
    public void testCalculateCosts() throws DataPersistenceException {
        instance.open();
        Order order = new Order(1);
        order.setProduct(instance.getProducts().get("Laminate"));
        order.setState(instance.getStates().get("MI"));
        order.setAreaSqFt(new BigDecimal("100"));
        instance.calculateCosts(order);
        assertTrue("OrderMatCost does not match", order.getOrderMatCost().compareTo(new BigDecimal("175.00")) == 0);
        assertTrue("OrderLaborCost does not match", order.getOrderLaborCost().compareTo(new BigDecimal("210.00")) == 0);
        assertTrue("OrderTax does not match", order.getOrderTax().compareTo(new BigDecimal("22.14")) == 0);
        assertTrue("OrderTotal does not match", order.getOrderTotal().compareTo(new BigDecimal("407.14")) == 0);
    }

}
