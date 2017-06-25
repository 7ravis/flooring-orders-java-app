package ui;

import dto.Order;
import dto.Product;
import dto.State;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Travis Rogers
 */

public class View {
    private UserIO io;
    public View(UserIO io) {
        this.io = io;
    }
    
    public LocalDate readDate() {
        return io.readDate("\nDate (use the following format: \"yyyy-mm-dd\"):");
    }
    public int readOrderNum() {
        return io.readInt("\nOrder Number: ");
    }
    public void displayOrders(ArrayList<Order> orders, LocalDate date) {
        io.printHeader("ORDERS FOR " + date);
        if (orders == null || orders.size() == 0) {
            io.println("There are no results to display.");
        } else {
            for (Order order : orders) {
                displayOrder(order);
            }
        }
    }
    public void displayOrder(Order order) {
        io.println("\nOrder Number: " + order.getOrderNum());
        io.println("Customer Name: " + order.getCustomerName());
        io.println("Customer State: " + order.getState().getStateAbbr());
        io.println("Project Area (in square feet): " + order.getAreaSqFt());
        io.println("Product Type: " + order.getProduct().getType());
        io.println("Order Total: $" + order.getOrderTotal());
    }
    public int mainMenu() {
        io.printHeader("Main Menu");
        return io.readInt("\n1. Display Orders \n2. Add an Order \n3. Edit an Order \n4. Remove an Order \n5. Save Current Work \n6. Quit", 1, 6);
    }
    public void displayHeader(String message) { io.printHeader(message); }
    public void displayMessage(String message) {
        io.println("\n" + message);
    }
    public Order addOrder(int orderNum, HashMap<String,State> states, HashMap<String,Product> products) {
        Order order = new Order(orderNum);
        io.printHeader("New Order");
        order.setCustomerName(io.readString("\nCustomer Name: "));
        io.println("Please select from one of the following states: ");
        int listCounter = 1;
        for (State state : states.values()) {
            io.println(listCounter + ": " + state.getStateAbbr());
            listCounter++;
        }
        int choiceInt = io.readInt("", 1, states.size());
        listCounter = 1;
        for (State state: states.values()) {
            if (listCounter == choiceInt) {
                order.setState(state);
            }
            listCounter++;
        }
        io.println("Please select from one of the following products: ");
        listCounter = 1;
        for (Product product : products.values()) {
            io.println(listCounter + ": " + product.getType());
            listCounter++;
        }
        choiceInt = io.readInt("", 1, products.size());
        listCounter = 1;
        for (Product product : products.values()) {
            if (listCounter == choiceInt) {
                order.setProduct(product);
            }
            listCounter++;
        }
        order.setAreaSqFt(io.readBigDecimal2("Square Footage: "));
        return order;
    }
    public boolean confirmRemoval(Order order) {
        if (order == null) {
            io.println("\nThere are no records matching your search.");
            return false;
        } else {
            io.printHeader("Order to Remove");
            displayOrder(order);
            return 1 == io.readInt("\nAre you sure you would like to remove this order? \n1. Yes \n2. No", 1, 2);
        }
    }
    public boolean confirmSubmission(Order order) {
        displayOrder(order);
        return 1 == io.readInt("\nSubmit order? \n1. Yes \n2. No", 1, 2);
    }
    public void editOrder(Order order, HashMap<String, Product> products, HashMap<String, State> states) {
        io.printHeader("Edit Order");
        displayOrder(order);
        io.println("\nFor each field, enter new data or press the \"Enter\" key to skip.");
        String customerName = io.readString("Customer Name (" + order.getCustomerName() + "): ");
        if (customerName.trim().length() != 0) { order.setCustomerName(customerName); }
        io.print("State (" + order.getState().getStateAbbr() + "), Valid Choices: ");
        for (String key : states.keySet()) { io.print("\"" + key + "\" "); }
        String stateAbbr = io.readString("").trim().toUpperCase();
        if (stateAbbr.length() != 0 && states.containsKey(stateAbbr)) {
            order.setState(states.get(stateAbbr));
        }
        io.print("Product (" + order.getProduct().getType() + "), Valid Choices: ");
        for (String key : products.keySet()) { io.print("\"" + key + "\" "); }
        String productInput = io.readString("").trim();
        if (productInput.length() != 0) {
            String productType = productInput.substring(0, 1).toUpperCase() + productInput.substring(1).toLowerCase();
            if (products.containsKey(productType)) {
                order.setProduct(products.get(productType));
            }
        }
        boolean keepRunning = true;
        BigDecimal area = null;
        do {
            String areaString = io.readString("Area (" + order.getAreaSqFt() + " square feet): ");
            if (areaString.trim().length() == 0) {
                keepRunning = false;
            } else {
                try {
                    area = new BigDecimal(areaString);
                    order.setAreaSqFt(area);
                    keepRunning = false;
                } catch (NumberFormatException e) {
                    io.println("ERROR: area must match one of the following numerical formats: 2.00 or 2");
                }
            }
        } while (keepRunning);
    }
}
