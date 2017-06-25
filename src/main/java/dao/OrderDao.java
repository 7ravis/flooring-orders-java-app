package dao;

import dto.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Travis Rogers
 */

public interface OrderDao {
    public void open() throws DataPersistenceException;
    public void close() throws DataPersistenceException;
    public void saveCurrentWork() throws DataPersistenceException;
    public ArrayList<Order> getOrdersByDate(LocalDate date);
    public Order getOrder(LocalDate date, int orderNum);
    public HashMap<LocalDate, ArrayList<Order>> getOrders();
    public boolean addOrder(Order order);
    public boolean removeOrder(Order order, LocalDate date);    
}
