package service;

import dao.DataPersistenceException;
import dto.Order;
import dto.Product;
import dto.State;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Travis Rogers
 */
public interface OrderService {
    public void open() throws DataPersistenceException;
    public void close() throws DataPersistenceException;
    public ArrayList<Order> getOrdersByDate(LocalDate date);
    public Order getOrder(LocalDate date, int orderNum);
    public boolean addOrder(Order order);
    public boolean removeOrder(Order order, LocalDate date);
    public void saveCurrentWork() throws DataPersistenceException;
    public int getNextOrderNum();
    public HashMap<String,State> getStates();
    public HashMap<String,Product> getProducts();
    public void calculateCosts(Order order);
}
