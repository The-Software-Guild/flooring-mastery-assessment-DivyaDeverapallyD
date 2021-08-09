/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aj.flooringmastery.service;

import com.aj.flooringmastery.model.Order;
import com.aj.flooringmastery.model.Product;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author DivyaDeverapally
 */
public class FlooringMasteryServiceImplTest {
    
    ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
     FlooringMasteryService service = ctx.getBean("serviceLayer", FlooringMasteryService.class);
     
     
     
     
     @Test
     public void testGetOrders(){
         assertEquals(1, service.getAllOrders().size(),"size should be 1");
     }
     //2,Navya,Texas,4.45,Tile,1200,3.50,4.15,4200.00,4980.00,9180.00,18360.00
    @Test
    public void testGetOrdersForDate(){
       Order  order= new Order();
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
        
        
        List<Order> orders= service.getOrdersFordate(LocalDate.parse("2011-11-11"));
        assertEquals(1, orders.size(),"Should 1 order");
      //  assertTrue(orders.contains(order));
        
    }
    
    @Test
    public void gettProducts(){
        Product product= new Product();
          product.setProduct_type("Tile");
        product.setCost_per_square_foot(new BigDecimal("3.50"));
        product.setLabour_cost_per_square_foot(new BigDecimal("4.15"));
        
        List<Product> p= service.getProducts();
        assertTrue(p.size()>0);
        assertEquals(product.getProduct_type(), p.get(0).getProduct_type(),"Should be same");
    }
    
    @Test
    public void testGetOrderInfo(){
        Order order= service.getOrderInfo(LocalDate.parse("2011-11-11"),2);
        assertNotNull(order);
        assertEquals(new BigDecimal("18360.00"), order.getTotal());
    }
    
    @Test
    public void testSaveOrder(){
          Order  order= new Order();
     //   order.setOrder_number(3);
//order.setOrder_number(2);
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
      //  order.setOrder_date(LocalDate.parse("2022-11-28"));
        
        int result= service.saveOrder(order);
        assertEquals(2, result,"should be same");
    }
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    
}
