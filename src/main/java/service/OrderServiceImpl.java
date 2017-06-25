package service;

import dao.DataPersistenceException;
import dao.OrderDao;
import dao.ProductDao;
import dao.StateDao;
import dto.Order;
import dto.Product;
import dto.State;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Travis Rogers
 */

public class OrderServiceImpl implements OrderService{
    private StateDao stateDao;
    private ProductDao productDao;
    private OrderDao orderDao;
    private int nextOrderNum = 1;
    
    public OrderServiceImpl(StateDao stateDao, ProductDao productDao, OrderDao orderDao) {
        this.stateDao = stateDao;
        this.productDao = productDao;
        this.orderDao = orderDao;
    }
    
    @Override
    public void open() throws DataPersistenceException {
        String combinedMessage = "";
        try {
            orderDao.open();
        } catch (DataPersistenceException e) {
            combinedMessage += "\n" + e.getMessage();
        }
        try {
            stateDao.open();
        } catch (DataPersistenceException e) {
            combinedMessage += "\n" + e.getMessage();
        }
        try {
            productDao.open();
        } catch (DataPersistenceException e) {
            combinedMessage += "\n" + e.getMessage();
        }
        ArrayList<Order> orders = orderDao.getOrdersByDate(LocalDate.now());
        int maxOrderNum = 0;
        if (orders.size() != 0) {
            for (Order order : orders) {
                if (order.getOrderNum() > maxOrderNum) {
                    maxOrderNum = order.getOrderNum();
                }
            }
            nextOrderNum = maxOrderNum + 1;
        }
        if (combinedMessage.length() != 0) {
            throw new DataPersistenceException(combinedMessage);
        }
    }
    @Override
    public void close() throws DataPersistenceException {
        orderDao.close();
    }
    @Override
    public ArrayList<Order> getOrdersByDate(LocalDate date) {
        return orderDao.getOrdersByDate(date);
    }
    @Override
    public Order getOrder(LocalDate date, int orderNum) {
        return orderDao.getOrder(date, orderNum);
    }
    @Override
    public boolean addOrder(Order order) {
        if (orderDao.addOrder(order)) {
            nextOrderNum++;
            return true;
        }
        return false;
    }
    @Override
    public boolean removeOrder(Order order, LocalDate date) {
        return orderDao.removeOrder(order, date);
    }
    @Override
    public void saveCurrentWork() throws DataPersistenceException {
        orderDao.saveCurrentWork();
    }
    @Override
    public int getNextOrderNum() {
        if (orderDao.getOrdersByDate(LocalDate.now()) == null) { //THIS CODE BLOCK RESETS THE THE ORDER NUMBERING IF THE PROGRAM WAS RUNNING WHEN THE DAY CHANGED
            nextOrderNum = 1;
        }
        return nextOrderNum;
    }
    @Override
    public HashMap<String, State> getStates() {
        return stateDao.getStates();
    }
    @Override
    public HashMap<String,Product> getProducts() {
        return productDao.getProducts();
    }
    @Override
    public void calculateCosts(Order order) {
        order.setOrderMatCost(order.getProduct().getMatCostPerSqFt().multiply(order.getAreaSqFt()).setScale(2, RoundingMode.HALF_UP));
        order.setOrderLaborCost(order.getProduct().getLaborCostPerSqFt().multiply(order.getAreaSqFt()).setScale(2, RoundingMode.HALF_UP));
        BigDecimal totalBeforeTax = order.getOrderMatCost().add(order.getOrderLaborCost()).setScale(2, RoundingMode.HALF_UP);
        order.setOrderTax(totalBeforeTax.multiply(order.getState().getTaxRate()).setScale(2, RoundingMode.HALF_UP));
        order.setOrderTotal(totalBeforeTax.add(order.getOrderTax()).setScale(2, RoundingMode.HALF_UP));
    }
}