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
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DivyaDeverapally
 */
public class FlooringMasteryServiceImpl implements FlooringMasteryService {

    private FlooringMasteryDao dao;

    public FlooringMasteryServiceImpl(FlooringMasteryDao dao) {
        this.dao = dao;

    }

    @Override
    public void loadProductsData() throws IOException, FileNotFoundException {
        dao.loadProductsData();
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadTaxData() throws IOException, FileNotFoundException {
        dao.loadTaxData();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadOrders() {
        dao.loadOrders();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Product> getProducts() {
        return dao.getProducts();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order orderSummary(Order order) {
        return dao.orderSummary(order);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int saveOrder(Order order) {
        return dao.saveOrder(order);
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() {
        return dao.getAllOrders();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getOrdersFordate(LocalDate date) {
      //  System.out.println("In service netered date" + date);
        return dao.getOrdersFordate(date);
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrderInfo(LocalDate order_date, int orderNum) {
        return dao.getOrderInfo(order_date, orderNum);
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveEditedOrder(Order newEditedOrder) {
        dao.saveEditedOrder(newEditedOrder);
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeOrder(Order order) {
        dao.removeOrder(order);
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportData() {
        dao.exportData();
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveToFile() {
        dao.saveToFile();
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tax> getTaxes() {
        return dao.getTaxes();
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
