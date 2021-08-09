/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.flooringmastery.dao;

import com.aj.flooringmastery.model.Order;
import com.aj.flooringmastery.model.Product;
import com.aj.flooringmastery.model.Tax;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.springframework.util.FileSystemUtils;

/**
 *
 * @author DivyaDeverapally
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    Map<String, Product> productsMap = new HashMap<>();//ptype product
    Map<String, Tax> taxesMap = new HashMap<>(); //state, tax
    Map<Integer, Order> ordersMap = new ConcurrentSkipListMap<>(); //orderid, order

    @Override
    public Order orderSummary(Order order) {

        BigDecimal cost_per_sft = new BigDecimal(productsMap.get(order.getProduct_type()).getCost_per_square_foot().toString());
        BigDecimal material_cost = order.getArea().multiply(cost_per_sft);
        BigDecimal Labor_cost_Per_sft = new BigDecimal(productsMap.get(order.getProduct_type()).getLabour_cost_per_square_foot().toString());
        BigDecimal labour_cost = order.getArea().multiply(Labor_cost_Per_sft);
        //  System.out.println("Divya tax maps"+ taxesMap.get("Texas"));
        BigDecimal tax_rate = taxesMap.get(order.getState()).getTax_rate();

        BigDecimal tax = ((material_cost.add(labour_cost))).multiply((tax_rate.divide(tax_rate)));

        BigDecimal total = material_cost.add(labour_cost).add(tax);
        order.setCost_per_square_foot(cost_per_sft);
        order.setLabour_cost_per_square_foot(Labor_cost_Per_sft);
        order.setMaterial_cost(material_cost);
        order.setLabour_cost(labour_cost);
        order.setTaxRate(tax_rate);
        order.setTax(tax);
        order.setTotal(total);

        return order;
    }

    @Override
    public int saveOrder(Order order) {

        Set<Integer> keys = ordersMap.keySet();
        List<Integer> kyList = new ArrayList<>(keys);

        if (kyList.isEmpty()) {
            order.setOrder_number(1);
        } else {
            Collections.sort(kyList);
            int max = Collections.max(kyList);
            order.setOrder_number(max + 1);
        }

        ordersMap.put(order.getOrder_number(), order);

        return order.getOrder_number();

        //return 0;
    }

    @Override
    public List<Order> getAllOrders() {

        return new ArrayList<>(ordersMap.values());
    }

    @Override
    public void loadProductsData() throws IOException, FileNotFoundException {
        File file = new File("Data/Products.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        br.readLine();
        //line = br.readLine();
        while ((line = br.readLine()) != null) {
            // String line = br.readLine();
            String[] words = line.split(",");
            String pType = words[0];
            String cp = words[1];
            String lc = words[2];

            Product p = new Product();
            p.setProduct_type(pType);
            p.setCost_per_square_foot(new BigDecimal(cp));
            p.setLabour_cost_per_square_foot(new BigDecimal(lc));
            //  System.out.println("Products from map to file" + p.toString());
            productsMap.put(p.getProduct_type(), p);

        }
    }

    @Override
    public void loadTaxData() throws IOException, FileNotFoundException {
        File file = new File("Data/Taxes.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        //TX,Texas,4.45
        String line;
        br.readLine();
        while ((line = br.readLine()) != null) {
            // String line = br.readLine();
            String[] words = line.split(",");
            Tax tax = new Tax();

            tax.setState_abbrevation(words[0]);
            tax.setState_name(words[1]);
            tax.setTax_rate(new BigDecimal(words[2]));

            //   System.out.println("Tax from file to map" + tax.toString());
            taxesMap.put(tax.getState_name(), tax);

        }
    }

    @Override
    public void loadOrders() {
        //  List<Order> orders= new ArrayList<Order>();
        //  Map<Integer, Order> ordersMap= new HashMap<>();
        String[] filesNames = getAllFileNames();
        for (String fn : filesNames) {
            List<Order> eachFileData = getEachFileDate(fn);

            for (Order order : eachFileData) {
                ordersMap.put(order.getOrder_number(), order);

            }
        }

    }

    private String[] getAllFileNames() {
        File file = new File("Orders");
        return file.list();

    }

    private List<Order> getEachFileDate(String fn) {
        String[] d = fn.split("_");
        //default, ISO_LOCAL_DATE

        List<Order> ordersList = new ArrayList<>();
        String orderDate = d[1].substring(4, 8) + "-" + d[1].substring(0, 2) + "-" + d[1].substring(2, 4);
        //  System.out.println("fibal"+ finDate);
        LocalDate date1 = LocalDate.parse(orderDate);
        File file = new File("Orders/" + fn);
        Map<Integer, Order> ordersMap1 = new TreeMap<>();

        if (file.exists()) {

            //  System.out.println("File exists in diaply orders");
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    Order order = new Order();
                    String[] orderFields = line.split(",");

//OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,
//LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total      
                    order.setOrder_number(Integer.parseInt(orderFields[0]));
                    order.setCustomer_name(orderFields[1]);
                    order.setState(orderFields[2]);
                    order.setTaxRate(new BigDecimal(orderFields[3]));
                    order.setProduct_type(orderFields[4]);
                    order.setArea(new BigDecimal(orderFields[5]));
                    order.setCost_per_square_foot(new BigDecimal(orderFields[6]));
                    order.setLabour_cost_per_square_foot(new BigDecimal(orderFields[7]));
                    order.setMaterial_cost(new BigDecimal(orderFields[8]));
                    order.setLabour_cost(new BigDecimal(orderFields[9]));
                    order.setTax(new BigDecimal(orderFields[10]));
                    order.setTotal(new BigDecimal(orderFields[11]));
                    order.setOrder_date(date1);

                    //  ordersMap1.put(order.getOrder_number(), order);
                    ordersList.add(order);

                }
                br.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(FlooringMasteryDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(FlooringMasteryDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return ordersList;
    }

    @Override
    public List<Product> getProducts() {
        return new ArrayList<>(productsMap.values());
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tax> getTaxes() {
        return new ArrayList<>(taxesMap.values());
    }

    @Override
    public List<Order> getOrdersFordate(LocalDate date) {
        //  System.out.println("Inside get prders fro date"+ date);
        List<Order> ordersList = new ArrayList<>();
        for (Map.Entry<Integer, Order> entry : ordersMap.entrySet()) {
            if ((entry.getValue().getOrder_date()).compareTo(date) == 0) {
                //  System.out.println(entry.getValue());
                ordersList.add(entry.getValue());
            }

        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return ordersList;
    }

    @Override
    public Order getOrderInfo(LocalDate order_date, int orderNum) {
        for (Map.Entry<Integer, Order> entry : ordersMap.entrySet()) {
            if (((entry.getValue().getOrder_date()).compareTo(order_date) == 0 && entry.getValue().getOrder_number() == orderNum)) {
                // System.out.println(entry.getValue());
                // ordersList.add(entry.getValue());
                return entry.getValue();
            }

        }
        return null;
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveEditedOrder(Order newEditedOrder) {

        for (Map.Entry<Integer, Order> entry : ordersMap.entrySet()) {
            if (entry.getKey() == newEditedOrder.getOrder_number()) {
                ordersMap.put(newEditedOrder.getOrder_number(), newEditedOrder);
                //System.out.println(entry.getValue());
                // ordersList.add(entry.getValue());
                //return entry.getValue();
            }

        }
        //write to file
        //    saveToFile();

        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveToFile() {
        try {
            deleteOldData();
        } catch (IOException ex) {
            Logger.getLogger(FlooringMasteryDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrintWriter pw = null;
        for (Map.Entry<Integer, Order> entry : ordersMap.entrySet()) {
            //  System.out.println("Inside save to file-------------------------- " + entry.getValue().toString());
            Order order = entry.getValue();
            String fileName = getFileName(order.getOrder_date());
            String header = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,"
                    + "LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";

            String orderAsString = order.getOrder_number() + "," + order.getCustomer_name() + "," + order.getState() + "," + order.getTaxRate() + ","
                    + order.getProduct_type() + "," + order.getArea() + "," + order.getCost_per_square_foot() + ","
                    + order.getLabour_cost_per_square_foot() + "," + order.getMaterial_cost() + "," + order.getLabour_cost() + "," + order.getTax() + "," + order.getTotal();

            File file = new File("Orders/" + fileName);
            /*    if (file.exists()) {
          //  System.out.println("Inside file exist");
            file.delete();
            file.deleteOnExit();
//             FileUtils.forceDelete(file);
          // file.delete();
        }
             */
            //   System.out.println("Inside file exist  check " + file.exists());
            if (file.exists()) {
                try {
                    //  System.out.println("Insised save order file exists");
                    //  pw = new PrintWriter(new FileWriter(file)); // true
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    pw = new PrintWriter(bw);
                    //   System.out.println("Inside file exists---------" + orderAsString);
                    pw.println(orderAsString);
                    pw.flush();
                    //   pw.close();

                } catch (IOException ex) {
                    Logger.getLogger(FlooringMasteryDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    if (file.createNewFile()) {
                        //   System.out.println("Insised save order file create");
                        pw = new PrintWriter(new FileWriter(file), true);
                        pw.println(header);
                        pw.println(orderAsString);
                        pw.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FlooringMasteryDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                pw.close();

            }

        }

    }

    public String getFileName(LocalDate date) {
        String fileName = "Orders_";

        int day = date.getDayOfMonth();
        //  System.out.println("Dayyy in get  file name--------------" + day);
        int month = date.getMonthValue();
        int year = date.getYear();

        //Orders_08212017.txt.Orders_MMDDYYYY.txt.
        if (String.valueOf(month).length() == 1) {
            fileName += "0" + String.valueOf(month);
        } else {
            fileName += String.valueOf(month);
        }
        if (String.valueOf(day).length() == 1) {
            fileName += "0" + String.valueOf(day);
        } else {
            fileName += String.valueOf(day);
        }

        fileName += String.valueOf(year) + ".txt";

        return fileName;
    }

    @Override
    public void removeOrder(Order order) {

        for (Map.Entry<Integer, Order> entry : ordersMap.entrySet()) {
            if (entry.getKey() == order.getOrder_number()) {
                // ordersMap.put(newEditedOrder.getOrder_number(), newEditedOrder);
                ordersMap.remove(order.getOrder_number());
                //System.out.println(entry.getValue());
                // ordersList.add(entry.getValue());
                //return entry.getValue();
            }

        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void exportData() {
        PrintWriter pw = null;
        File file = new File("Backup/backup.txt");
        if(file.exists()){
            try {
                FileUtils.forceDelete(file);
            } catch (IOException ex) {
                Logger.getLogger(FlooringMasteryDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        for (Map.Entry<Integer, Order> entry : ordersMap.entrySet()) {
            // System.out.println("Inside save to file-------------------------- " + entry.getValue().toString());
            Order order = entry.getValue();
            String fileName = getFileName(order.getOrder_date());
            String header = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,"
                    + "LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,OrderDte";

            String orderAsString = order.getOrder_number() + "," + order.getCustomer_name() + "," + order.getState() + "," + order.getTaxRate() + ","
                    + order.getProduct_type() + "," + order.getArea() + "," + order.getCost_per_square_foot() + ","
                    + order.getLabour_cost_per_square_foot() + "," + order.getMaterial_cost() + "," + order.getLabour_cost() + "," + order.getTax() + "," + order.getTotal() + "," + order.getOrder_date();

            //  File file = new File("Orders/" + fileName);
            if (file.exists()) {
                try {
                    //  pw = new PrintWriter(new FileWriter(file)); // true
                    FileWriter fw = new FileWriter(file, true);
                    BufferedWriter bw = new BufferedWriter(fw);
                    pw = new PrintWriter(bw);
                    //System.out.println("Inside file exists---------" + orderAsString);
                    pw.println(orderAsString);
                    pw.flush();

                } catch (IOException ex) {
                    Logger.getLogger(FlooringMasteryDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                try {
                    if (file.createNewFile()) {

                        pw = new PrintWriter(new FileWriter(file), true);
                        pw.println(header);
                        pw.println(orderAsString);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(FlooringMasteryDaoFileImpl.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void deleteOldData() throws IOException {
        PrintWriter pw = null;
        File file1 = new File("Orders");
        String[] pathName = file1.list();

        FileUtils.cleanDirectory(file1);
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
