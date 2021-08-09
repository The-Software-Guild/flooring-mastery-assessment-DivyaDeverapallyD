/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.flooringmastery.controller;

import com.aj.flooringmastery.model.Order;
import com.aj.flooringmastery.model.Product;
import com.aj.flooringmastery.model.Tax;
import com.aj.flooringmastery.service.FlooringMasteryService;
import com.aj.flooringmastery.view.FlooringMasteryView;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author DivyaDeverapally
 */
public class FlooringMasteryController {

    private FlooringMasteryService service;
    private FlooringMasteryView view;

    public FlooringMasteryController(FlooringMasteryService service, FlooringMasteryView view) {
        this.service = service;
        this.view = view;
    }

    public void run() throws IOException {
        System.out.println("COntroller run method");
        service.loadProductsData();
        service.loadTaxData();
        service.loadOrders();

        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            menuSelection = getMenuSelection();
            switch (menuSelection) {
                case 1:
                    // Display Orders
                    displayOrders();
                    break;
                case 2:
                    // Add an Order
                    addOrder();
                    break;
                case 3:
                    // Edit an Order
                    editOrder();
                    break;
                case 4:
                    //Remove an Order
                    removeOrder();
                    break;
                case 5:
                    //  Export All Data
                    exportAllData();
                    break;
                case 6:
                    keepGoing = false;
                    saveToFile();
                    break;
                default:
                    unknownCommand();

            }
        }
    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() {
        

        System.out.println("Display Orders");
        LocalDate date = view.getDate();
        System.out.println("Below are the orders for date :  " + date);
        System.out.println("----------------------------------------------------");
        List<Order> orders = service.getOrdersFordate(date);

        view.displayOrders(orders);

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void addOrder() throws IOException {
        List<Product> productsList = service.getProducts();
        List<Tax> taxes = service.getTaxes();
        Order order = view.getNewOrderIfo(productsList, taxes);
        Order newOrder = service.orderSummary(order);

        System.out.println("newOrder" + newOrder.toString());
        String save = view.displayConfirmationOptions();
        if (save.equalsIgnoreCase("y")) {
            //svae to collection and return order number
            int order_number = service.saveOrder(newOrder);
            System.out.println("Your order has been created and order number is : " + order_number);
        }
        if (save.equalsIgnoreCase("n")) {
            run();
        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void editOrder() throws IOException {
        LocalDate order_date = view.getDate();
        int orderNum = view.getOrderNumber();
        Order order = service.getOrderInfo(order_date, orderNum);
        if (order == null) {
            System.out.println("No data exists for entered input!!! please try again");
            run();
        } else {
            view.displayOrder(order);
            List<Product> productsList = service.getProducts();
            List<Tax> taxes = service.getTaxes();
            Order editOrder = view.getEditOrderdata(productsList, taxes);
            order.setCustomer_name(editOrder.getCustomer_name());
            order.setState(editOrder.getState());
            order.setArea(editOrder.getArea());
            order.setProduct_type(editOrder.getProduct_type());

            Order newEditedOrder = service.orderSummary(order);
            System.out.println("Order Summary ");
            view.displayOrder(newEditedOrder);
            System.out.println("newOrder" + newEditedOrder.toString());
            String save = view.displayConfirmationOptions();
            if (save.equalsIgnoreCase("y")) {
                //svae to collection and return order number
                service.saveEditedOrder(newEditedOrder);
                // System.out.println("Your order has been created and order number is : " +order_number);
            }
            if (save.equalsIgnoreCase("n")) {
                run();
            }

        }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void removeOrder() throws IOException {
        LocalDate order_date = view.getDate();
        int orderNum = view.getOrderNumber();

        Order order = service.getOrderInfo(order_date, orderNum);

        view.displayOrder(order);
        String save = view.displayConfirmationOptions();
        if (save.equalsIgnoreCase("y")) {
            //svae to collection and return order number
            service.removeOrder(order);
            // System.out.println("Your order has been created and order number is : " +order_number);
        }
        if (save.equalsIgnoreCase("n")) {
            run();
        }

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void exportAllData() {
        service.exportData();
        // new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void unknownCommand() {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        view.displayUnknownCommandBanner();
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void saveToFile() {
        service.saveToFile();
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
