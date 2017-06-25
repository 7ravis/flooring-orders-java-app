package dto;

import java.math.BigDecimal;

/**
 *
 * @author Travis Rogers
 */

public class Product {
    private String type;
    private BigDecimal matCostPerSqFt;
    private BigDecimal laborCostPerSqFt;
    
    public Product() {}
    public Product(String type, BigDecimal matCostPerSqFt, BigDecimal laborCostPerSqFt) {
        this.type = type;
        this.matCostPerSqFt = matCostPerSqFt;
        this.laborCostPerSqFt = laborCostPerSqFt;
    }
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public BigDecimal getMatCostPerSqFt() {
        return matCostPerSqFt;
    }
    public void setMatCostPerSqFt(BigDecimal matCostPerSqFt) {
        this.matCostPerSqFt = matCostPerSqFt;
    }
    public BigDecimal getLaborCostPerSqFt() {
        return laborCostPerSqFt;
    }
    public void setLaborCostPerSqFt(BigDecimal laborCostPerSqFt) {
        this.laborCostPerSqFt = laborCostPerSqFt;
    }  
}
