/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.flooringmastery.service;

import com.aj.flooringmastery.dao.FlooringMasteryDao;
import com.aj.flooringmastery.model.Order;
import com.aj.flooringmastery.model.Product;
import com.aj.flooringmastery.model.Tax;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DivyaDeverapally
 */
public class FlooringMasteryDaoStubImpl  implements FlooringMasteryDao{
    
    private Order order;
   // private Product product;
    
   public  FlooringMasteryDaoStubImpl(){
        order= new Order();
         order.setOrder_number(2);
        order.setCustomer_name("Navya");
        order.setState("Texas");
        order.setProduct_type("Tile");
        order.setTaxRate(new BigDecimal("4.45"));
        order.setArea(new BigDecimal("1200"));
        order.setCost_per_square_foot(new BigDecimal("3.50"));
        order.setLabour_cost_per_square_foot(new BigDecimal("4.15"));
        order.setMaterial_cost(new BigDecimal("4200.00"));
        order.setLabour_cost(new BigDecimal("4980.00"));
        order.setTax(new BigDecimal("9180.00"));
        order.setTotal(new BigDecimal("18360.00"));
        order.setOrder_date(LocalDate.parse("2011-11-11"));
    
    }
  public  FlooringMasteryDaoStubImpl(Order testOrder){
       this.order=testOrder;
   }
  @Override
    public List<Product> getProducts() {
        List<Product> products= new ArrayList<>();
        Product product = new Product();
          product.setProduct_type("Tile");
        product.setCost_per_square_foot(new BigDecimal("3.50"));
        product.setLabour_cost_per_square_foot(new BigDecimal("4.15"));
        products.add(product);
        return products;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> ordersList= new ArrayList<>();
        ordersList.add(order);
        return ordersList;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getOrdersFordate(LocalDate date) {
         List<Order> ordersList= new ArrayList<>();
        ordersList.add(order);
        return ordersList;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrderInfo(LocalDate order_date, int orderNum) {
        return order;
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void loadProductsData() throws IOException, FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadTaxData() throws IOException, FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadOrders() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order orderSummary(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saveOrder(Order order1) {
        return order.getOrder_number();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public void saveEditedOrder(Order newEditedOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeOrder(Order order) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveToFile() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tax> getTaxes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
