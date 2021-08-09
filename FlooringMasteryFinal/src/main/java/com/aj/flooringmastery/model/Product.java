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
public class Product {
    
    private String product_type;
    private BigDecimal cost_per_square_foot;

    @Override
    public String toString() {
        return "Product{" + "product_type=" + product_type + ", cost_per_square_foot=" + cost_per_square_foot + ", labour_cost_per_square_foot=" + labour_cost_per_square_foot + '}';
    }
    private BigDecimal labour_cost_per_square_foot;

    /**
     * @return the product_type
     */
    public String getProduct_type() {
        return product_type;
    }

    /**
     * @param product_type the product_type to set
     */
    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    /**
     * @return the cost_per_square_foot
     */
    public BigDecimal getCost_per_square_foot() {
        return cost_per_square_foot;
    }

    /**
     * @param cost_per_square_foot the cost_per_square_foot to set
     */
    public void setCost_per_square_foot(BigDecimal cost_per_square_foot) {
        this.cost_per_square_foot = cost_per_square_foot;
    }

    /**
     * @return the labour_cost_per_square_foot
     */
    public BigDecimal getLabour_cost_per_square_foot() {
        return labour_cost_per_square_foot;
    }

    /**
     * @param labour_cost_per_square_foot the labour_cost_per_square_foot to set
     */
    public void setLabour_cost_per_square_foot(BigDecimal labour_cost_per_square_foot) {
        this.labour_cost_per_square_foot = labour_cost_per_square_foot;
    }
    
}
