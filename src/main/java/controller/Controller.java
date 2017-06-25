package controller;

import dao.DataPersistenceException;
import dto.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import service.OrderService;
import ui.View;

/**
 *
 * @author Travis Rogers
 */

public class Controller {
    private OrderService service;
    private View view;
    
    public Controller(OrderService service, View view) {
        this.service = service;
        this.view = view;
    }
    
    public void run() {
        open();
        boolean keepRunning = true;
        do {
            switch (view.mainMenu()) {
                case 1: //Display orders
                    displayOrders();
                    break;
                case 2: //Add an order
                    addOrder();
                    break;
                case 3: //Edit an order
                    editOrder();
                    break;
                case 4: //Remove an order
                    removeOrder();
                    break;
                case 5: //Save current work
                    saveCurrentWork();
                    break;
                case 6: //Quit
                    keepRunning = false;
                    break;
                default:
            }
        } while (keepRunning);
        close();
    }
    
    public void open() {
        try {
            service.open();
        } catch (DataPersistenceException e) {
            view.displayMessage(e.getMessage());
        }
    }
    public void close() {
        try {
            service.close();
        } catch (DataPersistenceException e) {
            view.displayMessage(e.getMessage());
        }
    }
    public void displayOrders() {
        LocalDate date = view.readDate();
        ArrayList<Order> ordersForDate = service.getOrdersByDate(date);
        view.displayOrders(ordersForDate, date);
    }
    public void addOrder() {
        Order order = view.addOrder(service.getNextOrderNum(), service.getStates(), service.getProducts());
        service.calculateCosts(order);
        if (view.confirmSubmission(order)) {
            service.addOrder(order);
        }
    }
    public void editOrder() {
        LocalDate date = view.readDate();
        int orderNum = view.readOrderNum();
        Order order = service.getOrder(date, orderNum);
        if (order == null) {
            view.displayMessage("There are no records matching your search.");
        } else {
            view.editOrder(order, service.getProducts(), service.getStates());
            service.calculateCosts(order);
            view.displayHeader("Order Updated");
            view.displayOrder(order);
        }
    }
    public void removeOrder() {
        LocalDate date = view.readDate();
        int orderNum = view.readOrderNum();
        Order order = service.getOrder(date, orderNum);
        if (view.confirmRemoval(order)) {
            service.removeOrder(order, date);
        }
    }
    public void saveCurrentWork() {
        try {
            service.saveCurrentWork();
        } catch (DataPersistenceException e) {
            view.displayMessage(e.getMessage());
        }
    }
}