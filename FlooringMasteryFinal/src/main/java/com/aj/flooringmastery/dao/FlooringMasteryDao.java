/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.flooringmastery.dao;

import com.aj.flooringmastery.model.Order;
import com.aj.flooringmastery.model.Product;
import com.aj.flooringmastery.model.Tax;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DivyaDeverapally
 */
public interface FlooringMasteryDao {

    public void loadProductsData() throws IOException, FileNotFoundException;

    public void loadTaxData() throws IOException, FileNotFoundException;

    public void loadOrders();

    public Order orderSummary(Order order);

    public int saveOrder(Order order);

    public List<Product> getProducts();

    public List<Order> getAllOrders();
    // public Order orderSummary(Order order);

    public List<Order> getOrdersFordate(LocalDate date);

    public Order getOrderInfo(LocalDate order_date, int orderNum);

    public void saveEditedOrder(Order newEditedOrder);

    public void removeOrder(Order order);

    public void exportData();

    public void saveToFile();

    public List<Tax> getTaxes();

}
