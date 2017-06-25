package dto;

import java.math.BigDecimal;

/**
 *
 * @author Travis Rogers
 */

public class State {
    private String stateAbbr;
    private BigDecimal taxRate;
    
    public State() {}
    public State(String stateAbbr, BigDecimal taxRate) {
        this.stateAbbr = stateAbbr;
        this.taxRate = taxRate;
    }
    
    public String getStateAbbr() {
        return stateAbbr;
    }
    public BigDecimal getTaxRate() {
        return taxRate;
    }
}
