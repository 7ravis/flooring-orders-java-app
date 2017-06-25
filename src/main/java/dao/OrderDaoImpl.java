package dao;

import dto.Order;
import dto.Product;
import dto.State;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Travis Rogers
 */

public class OrderDaoImpl implements OrderDao{
    private String programMode;
    private HashMap<LocalDate, ArrayList<Order>> orders;
    private final String DELIM = "::";
    
    public OrderDaoImpl(String programMode) {
        this.programMode = programMode;
    }
    
    private void writeFile() throws DataPersistenceException {
        if (programMode.equalsIgnoreCase("training") || programMode.equalsIgnoreCase("train")) {
        } else {
            String exceptionLog = "";
            PrintWriter out = null;
            Set<LocalDate> keys = orders.keySet();
            for (LocalDate key : keys) {
                String formattedDate = key.format(DateTimeFormatter.ofPattern("MMdduuuu"));
                try {
                    out = new PrintWriter("orders/Orders_" + formattedDate + ".txt");
                    ArrayList<Order> ordersFromDay = orders.get(key);
                    out.println("OrderNum,CustomerName,StateAbbr,TaxRate,ProductType,AreaSqFt,MatCostPerSqFt,LaborCostPerSqFt,OrderMatCost,OrderLaborCost,OrderTax,OrderTotal");
                    for (Order order : ordersFromDay) {
                        out.println(
                                order.getOrderNum() + DELIM
                                        + order.getCustomerName() + DELIM
                                        + order.getState().getStateAbbr() + DELIM
                                        + order.getState().getTaxRate() + DELIM
                                        + order.getProduct().getType() + DELIM
                                        + order.getAreaSqFt() + DELIM
                                        + order.getProduct().getMatCostPerSqFt() + DELIM
                                        + order.getProduct().getLaborCostPerSqFt() + DELIM
                                        + order.getOrderMatCost() + DELIM
                                        + order.getOrderLaborCost() + DELIM
                                        + order.getOrderTax() + DELIM
                                        + order.getOrderTotal());
                    }
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    exceptionLog += key + " ";
                }
            }
            if (exceptionLog.length() != 0) {
                throw new DataPersistenceException("ERROR: data could not be persisted for date(s): " + exceptionLog);
            }
        }
    }
    private void loadFile(LocalDate date) throws DataPersistenceException {
        ArrayList<Order> ordersForDay = new ArrayList();
        Scanner sc;
        String formattedDate = date.format(DateTimeFormatter.ofPattern("MMdduuuu"));
        try {
            sc = new Scanner(new BufferedReader(new FileReader("orders/Orders_" + formattedDate + ".txt")));
            sc.nextLine(); //GETTING RID OF FILE HEADER
            while (sc.hasNextLine()) {
                String[] orderData = sc.nextLine().split(DELIM);
                Product product = new Product();
                Order order = new Order(Integer.parseInt(orderData[0]));                
                order.setProduct(product);
                
                order.setCustomerName(orderData[1]);
                order.setState(new State(orderData[2], new BigDecimal(orderData[3])));
                product.setType(orderData[4]);
                order.setAreaSqFt(new BigDecimal(orderData[5]));
                product.setMatCostPerSqFt(new BigDecimal(orderData[6]));
                product.setLaborCostPerSqFt(new BigDecimal(orderData[7]));
                order.setOrderMatCost(new BigDecimal(orderData[8]));
                order.setOrderLaborCost(new BigDecimal(orderData[9]));
                order.setOrderTax(new BigDecimal(orderData[10]));
                order.setOrderTotal(new BigDecimal(orderData[11]));
                
                ordersForDay.add(order);
            }
            orders.put(date, ordersForDay);
        } catch (IOException e) {
            throw new DataPersistenceException("No orders were found for " + date + ".");
        }
    }
    
    @Override
    public void open() throws DataPersistenceException {
        orders = new HashMap<>();
        try {
            loadFile(LocalDate.now());
        } catch (DataPersistenceException e) {
            orders.put(LocalDate.now(), new ArrayList<>());
            throw new DataPersistenceException(e.getMessage());
        }
    }
    @Override
    public void close() throws DataPersistenceException {
        writeFile();
    }
    @Override
    public void saveCurrentWork() throws DataPersistenceException {
        writeFile();
    }
    @Override
    public ArrayList<Order> getOrdersByDate(LocalDate date) {
        if (orders.containsKey(date)) {
            return orders.get(date);
        } else {
            try {
                loadFile(date);
                return orders.get(date);
            } catch (DataPersistenceException e) {
                return null;
            }
        }
    }
    @Override
    public Order getOrder(LocalDate date, int orderNum) {
        ArrayList<Order> ordersByDate = getOrdersByDate(date);
        if (ordersByDate == null) { return null; }
        try {
        return ordersByDate.stream().filter(o -> o.getOrderNum() == orderNum).findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
    @Override
    public HashMap<LocalDate, ArrayList<Order>> getOrders() {
        return orders;
    }
    @Override
    public boolean addOrder(Order order) {
        if (orders.containsKey(LocalDate.now())) {
            return orders.get(LocalDate.now()).add(order);
        } else {
            orders.put(LocalDate.now(), new ArrayList<>());
            return orders.get(LocalDate.now()).add(order);
        }
    }
    @Override
    public boolean removeOrder(Order order, LocalDate date) {
        return orders.get(date).remove(order);
    }
}
