package dao;

import dto.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Travis Rogers
 */

public class OrderDaoStubImpl implements OrderDao {
    private HashMap<LocalDate, ArrayList<Order>> orders = new HashMap<>();

    @Override
    public void open() throws DataPersistenceException {
        orders.put(LocalDate.now(), new ArrayList<>());
    }

    @Override
    public void close() throws DataPersistenceException {
        
    }

    @Override
    public void saveCurrentWork() throws DataPersistenceException {
        
    }

    @Override
    public ArrayList<Order> getOrdersByDate(LocalDate date) {
        if (orders.containsKey(date)) {
            return orders.get(date);
        }
        return null;
    }

    @Override
    public Order getOrder(LocalDate date, int orderNum) {
        if (orders.containsKey(date)) {
            for (Order order : orders.get(date)) {
                if (order.getOrderNum() == orderNum) {
                    return order;
                }
            }
        }
        return null;
    }

    @Override
    public HashMap<LocalDate, ArrayList<Order>> getOrders() {
        return orders;
    }

    @Override
    public boolean addOrder(Order order) {
        return true;
    }

    @Override
    public boolean removeOrder(Order order, LocalDate date) {
        return true;
    }

}
