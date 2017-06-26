/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advice;

import dto.Order;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Travis Rogers
 */
public class LoggingAdvice {
    private final String ORDER_AUDIT = "orderAudit.txt";
    
    public void logAddOrder(Order order) {
        String auditEntry = " ADD | Order Number: " + order.getOrderNum() + ", Customer: " + order.getCustomerName() + ", Area: " + order.getAreaSqFt() + " sq ft, Material: " + order.getProduct().getType() + ", Total Cost: $" + order.getOrderTotal();
        writeToAuditFile(auditEntry);
    }    
    public void logRemoveOrder(Order order, LocalDate date) {
        String auditEntry = " REMOVE | Order Date: " + date.toString() + ", Order Number: " + order.getOrderNum() + ", Customer: " + order.getCustomerName() + ", Area: " + order.getAreaSqFt() + " sq ft, Material: " + order.getProduct().getType() + ", Total Cost: $" + order.getOrderTotal();
        writeToAuditFile(auditEntry);
    }
    public void logEditOrder(Order order, LocalDate date) {
        String auditEntry = " EDIT | Order Date: " + date.toString() + ", Order Number: " + order.getOrderNum() + ", Customer: " + order.getCustomerName() + ", Area: " + order.getAreaSqFt() + " sq ft, Material: " + order.getProduct().getType() + ", Total Cost: $" + order.getOrderTotal();
        writeToAuditFile(auditEntry);
    }
    
    private void writeToAuditFile(String auditEntry) {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter(ORDER_AUDIT, true));
            out.println(LocalDateTime.now().toString() + "|" + auditEntry);
            out.flush();
            out.close();
        } catch (IOException e) {
            System.err.println("Error: Could not create audit entry in LoggingAdvice.");
        }        
    }
}
