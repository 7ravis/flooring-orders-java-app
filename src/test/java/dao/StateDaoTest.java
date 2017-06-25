/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.State;
import java.math.BigDecimal;
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
public class StateDaoTest {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("ApplicationContextTest.xml");
    StateDao instance = ctx.getBean("StateDao", StateDaoImpl.class);
    
    public StateDaoTest() {
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
     * Test of open method, of class StateDao.
     */
    @Test
    public void testOpen() throws Exception {
        try {
        instance.open();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown during open() method");
        }
        assertTrue(instance.getStates() != null);
    }

    /**
     * Test of close method, of class StateDao.
     */
    @Test
    public void testClose() {
        try {
        instance.open();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown during open() method");
        }
        try {
        instance.close();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown during close() method");
        }
    }

    /**
     * Test of getStates method, of class StateDao.
     */
    @Test
    public void testGetStates() {
        try {
        instance.open();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown during open() method");
        }
        assertTrue(instance.getStates().size() == 4);
    }

    /**
     * Test of getStateByName method, of class StateDao.
     */
    @Test
    public void testGetStateByName() {
        try {
        instance.open();
        } catch (DataPersistenceException e) {
            fail("DataPersistenceException thrown during open() method");
        }
        State state = instance.getStateByName("IN");
        assertTrue(state.getStateAbbr().equals("IN"));
        assertTrue(state.getTaxRate().compareTo(new BigDecimal(".06")) == 0);
    }
}
