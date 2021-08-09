/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.flooringmastery.view;

import com.aj.flooringmastery.model.Order;
import com.aj.flooringmastery.model.Product;
import com.aj.flooringmastery.model.Tax;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author DivyaDeverapally
 */
public class FlooringMasteryView {

    private UserIO io;

    private FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Display Orders");
        io.print("2. Add an Order");
        io.print("3. Edit an Order");
        io.print("4. Remove an Order");
        io.print("5. Export All Data");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public Order getNewOrderIfo(List<Product> productList, List<Tax> taxes) {
        //orderdate  local date
        //Customer Name string
        //state string
        //product type  //display products from products file ask to choose
        //Area - minimum 100sft
        Order order = new Order();
        String order_date = io.readString("Enter Order Date(yyyy-mm-dd)");
        if (LocalDate.parse(order_date).isAfter(LocalDate.now())) {
            order.setOrder_date(LocalDate.parse(order_date));
        } else {
            System.out.println("date should be future");
            order_date = io.readString("Enter Order Date(yyyy-mm-dd)");

        }
        String customer_name = io.readString("Enter Customer Name");
        String state = io.readString("Enter State");
        boolean isExist = false;
        for (Tax tax : taxes) {
            if (tax.getState_name().equals(state)) {
                isExist = true;
                break;
            }

        }
        if (isExist) {
            order.setState(state);
        } else {
            System.out.println("We dont sell in entered state");
            state = io.readString("Enter State");
        }

        String area = io.readString("Enter Area");
        if (new BigDecimal(area).compareTo(new BigDecimal("100")) == 1) {
            order.setArea(new BigDecimal(area));
        } else {
            System.out.println("Area  should be greater than 100");
            area = io.readString("Enter Area");

        }
        displayProducts(productList);

        String tempDate = "";
        int product_index = io.readInt("Enter Product Index from above");

        // String product_type= io.readString("Select Product Type");
        order.setOrder_date(LocalDate.parse(order_date));
        order.setCustomer_name(customer_name);

        order.setProduct_type(productList.get(product_index - 1).getProduct_type());
        order.setArea(new BigDecimal(area));

        return order;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayProducts(List<Product> list) {

        System.out.println("Index" + "\t" + "ProductType" + "\t\t" + "CostPerSFT" + "\t\t" + "LabourCostPerSFT");
        int index = 1;
        for (Product product : list) {
            System.out.println(index + "\t" + product.getProduct_type() + "\t\t\t" + product.getCost_per_square_foot() + "\t\t\t" + product.getLabour_cost_per_square_foot());
            index++;
        }
    }

    public String displayConfirmationOptions() {
        String save = io.readString("Press Y to save N to Cancel");
        return save;
    }

    public LocalDate getDate() {
        String order_date = io.readString("Enter Order Date(yyyy-mm-dd)");
        LocalDate date = LocalDate.parse(order_date);
        return date;
    }

    public void displayOrders(List<Order> orders) {
        
         for (Order order : orders) {
             System.out.println("Order Number :\t " + order.getOrder_number());
             System.out.println("Customer Name :\t " + order.getCustomer_name());
             System.out.println("State:\t " + order.getState());
             System.out.println("Tax Rate:\t " + order.getTaxRate());
             System.out.println("Product type :\t " + order.getProduct_type());
             System.out.println("Area :\t " + order.getArea());
             System.out.println("Cost Per Square Foot:\t " + order.getCost_per_square_foot());
             System.out.println("Labour Cost Per Square Foot:\t " + order.getLabour_cost_per_square_foot());
             System.out.println("Material Cost:\t " + order.getLabour_cost());
             System.out.println("Tax :\t " + order.getTax());
             System.out.println("Total :\t " + order.getTotal());
 
             System.out.println("**********************************************");
             
         }
         
         
         System.out.println("------------------------------------------------------------------");
        
     /*   System.out.println("OrderNumber" + "\t" + "CustomerName" + "\t" + "State" + "\t" + "TaxRate" + "\t" + "ProductType" + "Area" + "\t" + "CostPerSquareFoot" + "\t" + "LaborCostPerSquareFoot" + "\t" + "MaterialCost" + "\t" + "LaborCost" + "\t" + "Tax" + "\t" + "Total");
        System.out.println("******************************************************************************");
        for (Order order : orders) {
            System.out.println(order.getOrder_number() + "\t\t" + order.getCustomer_name() + "\t" + order.getState() + "\t" + order.getTaxRate() + "\t" + order.getProduct_type() + "\t" + order.getArea() + "\t" + order.getCost_per_square_foot() + "\t" + order.getLabour_cost_per_square_foot() + "\t" + order.getMaterial_cost() + "\t" + order.getLabour_cost() + "\t" + order.getTax() + "\t" + order.getTotal());
        }
*/

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getOrderNumber() {
        return io.readInt("Please enter number ");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void displayOrder(Order order) {
        System.out.println("OrderNumber" + "\t" + "CustomerName" + "\t" + "State" + "\t" + "TaxRate" + "\t" + "ProductType" + "Area" + "\t" + "CostPerSquareFoot" + "\t" + "LaborCostPerSquareFoot" + "\t" + "MaterialCost" + "\t" + "LaborCost" + "\t" + "Tax" + "\t" + "Total");
        System.out.println("******************************************************************************");

        System.out.println(order.getOrder_number() + "\t\t" + order.getCustomer_name() + "\t" + order.getState() + "\t" + order.getTaxRate() + "\t" + order.getProduct_type() + "\t" + order.getArea() + "\t" + order.getCost_per_square_foot() + "\t" + order.getLabour_cost_per_square_foot() + "\t" + order.getMaterial_cost() + "\t" + order.getLabour_cost() + "\t" + order.getTax() + "\t" + order.getTotal());

//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Order getEditOrderdata(List<Product> productList, List<Tax> taxes) {
        Order order = new Order();

        String customer_name = io.readString("Enter Customer Name");
        String state = io.readString("Enter State");
        String area = io.readString("Enter Area");
        if (new BigDecimal(area).compareTo(new BigDecimal("100")) == 1) {
            order.setArea(new BigDecimal(area));
        } else {
            System.out.println("Area  should be greater than 100");
            area = io.readString("Enter Area");

        }
        displayProducts(productList);

        String tempDate = "";
        int product_index = io.readInt("Enter Product Index from above");

        // String product_type= io.readString("Select Product Type");
        // order.setOrder_date(LocalDate.parse(order_date));
        order.setCustomer_name(customer_name);
        order.setState(state);
        order.setProduct_type(productList.get(product_index - 1).getProduct_type());
        order.setArea(new BigDecimal(area));

        return order;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
