/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.flooringmastery.model;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author DivyaDeverapally
 */
public class Order{
    private int order_number;
    private String customer_name;
    private String state;
    private BigDecimal TaxRate;
    private String product_type;
    private BigDecimal area;
    private BigDecimal cost_per_square_foot;
    private BigDecimal labour_cost_per_square_foot;
    private BigDecimal material_cost;
    private BigDecimal labour_cost;
    private BigDecimal tax;
    private BigDecimal total;

    @Override
    public String toString() {
        return "Order{"  + ", customer_name=" + customer_name + ", state=" + state + ", TaxRate=" + TaxRate + ", product_type=" + product_type + ", area=" + area + ", cost_per_square_foot=" + cost_per_square_foot + ", labour_cost_per_square_foot=" + labour_cost_per_square_foot + ", material_cost=" + material_cost + ", labour_cost=" + labour_cost + ", tax=" + tax + ", total=" + total + ", order_date=" + order_date + '}';
    }
    private LocalDate order_date;

    /**
     * @return the order_number
     */
    public int getOrder_number() {
        return order_number;
    }

    /**
     * @param order_number the order_number to set
     */
    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    /**
     * @return the customer_name
     */
    public String getCustomer_name() {
        return customer_name;
    }

    /**
     * @param customer_name the customer_name to set
     */
    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the TaxRate
     */
    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    /**
     * @param TaxRate the TaxRate to set
     */
    public void setTaxRate(BigDecimal TaxRate) {
        this.TaxRate = TaxRate;
    }

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
     * @return the area
     */
    public BigDecimal getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(BigDecimal area) {
        this.area = area;
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

    /**
     * @return the material_cost
     */
    public BigDecimal getMaterial_cost() {
        return material_cost;
    }

    /**
     * @param material_cost the material_cost to set
     */
    public void setMaterial_cost(BigDecimal material_cost) {
        this.material_cost = material_cost;
    }

    /**
     * @return the labour_cost
     */
    public BigDecimal getLabour_cost() {
        return labour_cost;
    }

    /**
     * @param labour_cost the labour_cost to set
     */
    public void setLabour_cost(BigDecimal labour_cost) {
        this.labour_cost = labour_cost;
    }

    /**
     * @return the tax
     */
    public BigDecimal getTax() {
        return tax;
    }

    /**
     * @param tax the tax to set
     */
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    /**
     * @return the total
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    /**
     * @return the order_date
     */
    public LocalDate getOrder_date() {
        return order_date;
    }

    /**
     * @param order_date the order_date to set
     */
    public void setOrder_date(LocalDate order_date) {
        this.order_date = order_date;
    }
    
    
    
    
    
    
}
