package com.example.springsocial.controller;

import com.example.springsocial.model.Product;
import com.example.springsocial.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.*;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/id/{id}")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @GetMapping("/product/name/{name}")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public List<Product> getProductByName(@PathVariable String name) {
        return productService.getProductPricesByName(name);
    }

    public static Map<String, String> getProductsDatePriceFromDb(String nameOfProduct, String startDate, String endDate) throws SQLException, ClassNotFoundException {
        Map<String, String> monthPriceMap = new HashMap<>();

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/foodinfdb", "root", "SpaceMysql1!");
        Statement stmt = conn.createStatement();

        int returnedProductId1 = getSelectDate(nameOfProduct, startDate, conn);
        int returnedProductId2 = getSelectDate(nameOfProduct, endDate, conn);

        String finalQuery = "SELECT month_name, price FROM products WHERE id BETWEEN ? AND ?";
        return makeBetweenStatement(monthPriceMap, conn, returnedProductId2, returnedProductId1, finalQuery);
    }

    static Map<String, String> makeBetweenStatement(Map<String, String> monthPriceMap, Connection conn, int returnedProductId1, int returnedProductId2, String finalQuery) throws SQLException {
        PreparedStatement betweenStatement = conn.prepareStatement(finalQuery);
        betweenStatement.setInt(1, returnedProductId1);
        betweenStatement.setInt(2, returnedProductId2);

        ResultSet betweenResult = betweenStatement.executeQuery();

        while (betweenResult.next()) {
            monthPriceMap.put(betweenResult.getString("month_name"), betweenResult.getString("price"));
        }

        return monthPriceMap;
    }

    private static int getSelectDate(String nameOfProduct, String givenDate, Connection conn) throws SQLException {
        String querySelectDate = "SELECT id FROM products WHERE product_name = ? AND month_name = ?";
        PreparedStatement statement = conn.prepareStatement(querySelectDate);
        statement.setString(1, nameOfProduct);
        statement.setString(2, givenDate);

        ResultSet startResult = statement.executeQuery();

        int returned_product_id = 0;
        while (startResult.next()) {
            returned_product_id = startResult.getInt("id");
        }
        return returned_product_id;
    }
}
