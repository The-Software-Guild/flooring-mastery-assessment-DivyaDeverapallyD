/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.flooringmastery.model;

import java.math.BigDecimal;

/**
 *
 * @author DivyaDeverapally
 */
public class Tax {
    private String state_name;
    private String state_abbrevation;

    @Override
    public String toString() {
        return "Tax{" + "state_name=" + state_name + ", state_abbrevation=" + state_abbrevation + ", tax_rate=" + tax_rate + '}';
    }
    private BigDecimal tax_rate;

    /**
     * @return the state_name
     */
    public String getState_name() {
        return state_name;
    }

    /**
     * @param state_name the state_name to set
     */
    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    /**
     * @return the state_abbrevation
     */
    public String getState_abbrevation() {
        return state_abbrevation;
    }

    /**
     * @param state_abbrevation the state_abbrevation to set
     */
    public void setState_abbrevation(String state_abbrevation) {
        this.state_abbrevation = state_abbrevation;
    }

    /**
     * @return the tax_rate
     */
    public BigDecimal getTax_rate() {
        return tax_rate;
    }

    /**
     * @param tax_rate the tax_rate to set
     */
    public void setTax_rate(BigDecimal tax_rate) {
        this.tax_rate = tax_rate;
    }
    
    
    
}
