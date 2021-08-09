/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.flooringmastery.dao;

import com.aj.flooringmastery.model.Order;
import com.aj.flooringmastery.model.Product;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author DivyaDeverapally
 */
public class FlooringMasteryDaoFileImplTest {
    
    
    FlooringMasteryDao testDao;
    
    public FlooringMasteryDaoFileImplTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        testDao= new FlooringMasteryDaoFileImpl();
        testDao.loadOrders();
        testDao.loadProductsData();
        testDao.loadTaxData();
    }
    
    
    @Test
    public void testGetOrdersForDate(){
        //3,sdsad,Texas,4.45,Wood,19,5.15,4.75,97.85,90.25,188.10,376.20
        Order order= new Order();
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
        order.setOrder_date(LocalDate.parse("2021-11-11"));
        
        
        List<Order> orders= testDao.getOrdersFordate(LocalDate.parse("2021-11-11"));
        
        assertNotNull(orders,"Orders must not be null");
        assertEquals(1, orders.size(),"Size must be 1");
       
         
        
        
    }
    
    
    @Test
    public void testGetProducts(){
        
        List<Product> products= testDao.getProducts();
        Product p= new Product();
        //Tile,3.50,4.15
        p.setProduct_type("Tile");
        p.setCost_per_square_foot(new BigDecimal("3.50"));
        p.setLabour_cost_per_square_foot(new BigDecimal("4.15"));
        
        assertNotNull(products,"Orders must not be null");
        assertEquals(4, products.size(),"Size must be 4");
      //  assertTrue(products.contains(p),"List must contain product");
        
        
        
    }
    @Test
    public void testGetOrderInfo(){
       Order order= new Order();
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
        order.setOrder_date(LocalDate.parse("2021-11-11"));
        
        String date= "2021-11-11";
        
        Order order1= testDao.getOrderInfo(LocalDate.parse(date),2);
        assertNotNull(order1,"Order1 should not be null");
    }
    
    
    @Test
    public void testSaveOrder(){
         Order order= new Order();
      //  order.setOrder_number(3);
       //  order.setOrder_number(2);
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
        order.setOrder_date(LocalDate.parse("2021-11-11"));
        
        
        int orderNum= testDao.saveOrder(order);
       
       // assertEquals(expected,orderNum,"should be 8");
        assertTrue(orderNum>0);
        
        
        
    }
    
    @Test
    public void testOrderSummary(){
         Order order= new Order();
    
        order.setCustomer_name("test");
        order.setState("Texas");
        order.setProduct_type("Wood");
        //order.setTaxRate(new BigDecimal("4.45"));
        order.setArea(new BigDecimal("19"));
        Order orderSum= testDao.orderSummary(order);
        assertEquals(new BigDecimal("4.45"), orderSum.getTaxRate(),"Both should be equals");
        
        
    }
    
    @AfterEach
    public void tearDown() {
       
    }

   
    
}
