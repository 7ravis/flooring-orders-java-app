package dto;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Travis Rogers
 */

public class Order {
    private int orderNum;
    private String customerName;
    private State state;
    private Product product;
    private BigDecimal areaSqFt;
    private BigDecimal orderMatCost;
    private BigDecimal orderLaborCost;
    private BigDecimal orderTax;
    private BigDecimal orderTotal;
    
    public Order(int orderNum) {
        this.orderNum = orderNum;
    }
    
    public int getOrderNum() {
        return orderNum;
    }
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public BigDecimal getAreaSqFt() {
        return areaSqFt;
    }
    public void setAreaSqFt(BigDecimal areaSqFt) {
        this.areaSqFt = areaSqFt;
    }
    public BigDecimal getOrderMatCost() {
        return orderMatCost;
    }
    public void setOrderMatCost(BigDecimal orderMatCost) {
        this.orderMatCost = orderMatCost;
    }
    public BigDecimal getOrderLaborCost() {
        return orderLaborCost;
    }
    public void setOrderLaborCost(BigDecimal orderLaborCost) {
        this.orderLaborCost = orderLaborCost;
    }
    public BigDecimal getOrderTax() {
        return orderTax;
    }
    public void setOrderTax(BigDecimal orderTax) {
        this.orderTax = orderTax;
    }
    public BigDecimal getOrderTotal() {
        return orderTotal;
    }
    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }
}
