package com.example.springsocial.controller;

import com.example.springsocial.model.Inflation;
import com.example.springsocial.repository.InflationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class InflationController {

    @Autowired
    private InflationRepository inflationRepository;

    @GetMapping("/inflation/{month}")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public Inflation getInflationByMonth(@PathVariable String month){
        return inflationRepository.findInflationByMonth(month);
    }

<<<<<<< Updated upstream
    public static Map<String, String> getInflationDateValueFromDb(String startDate, String endDate) throws ClassNotFoundException, SQLException {
        Map<String, String> monthValueMap = new HashMap<>();

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3305/foodinfdb", "root", "SpaceMysql1!");
=======
    @GetMapping("/inflation/createTable")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public static Inflation[] getInflationDateValueFromDb(@RequestParam("s") String startDate, @RequestParam("e") String endDate) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodinfdb", "root", "");
>>>>>>> Stashed changes
        Statement stmt = conn.createStatement();

        int returnedInflationInt1 = getSelectDate(startDate, conn);
        int returnedInflationInt2 = getSelectDate(endDate, conn);

        String finalQuery = "SELECT month_name, InfValue FROM inflation WHERE id BETWEEN ? AND ?";
        return makeBetweenStatement(monthValueMap, conn, returnedInflationInt1, returnedInflationInt2, finalQuery);
    }

    static Map<String, String> makeBetweenStatement(Map<String, String> monthValueMap, Connection conn, int returnedInflationInt1, int returnedInflationInt2, String finalQuery) throws SQLException {
        PreparedStatement betweenStatement = conn.prepareStatement(finalQuery);
        betweenStatement.setInt(1, returnedInflationInt1);
        betweenStatement.setInt(2, returnedInflationInt2);

        ResultSet betweenResult = betweenStatement.executeQuery();

        while (betweenResult.next()) {
            monthValueMap.put(betweenResult.getString("month_name"), betweenResult.getString("InfValue"));
        }

        return monthValueMap;
    }

    private static int getSelectDate(String givenDate, Connection conn) throws SQLException {
        String querySelectDate = "SELECT id FROM inflation WHERE month_name = ?";
        PreparedStatement statement = conn.prepareStatement(querySelectDate);
        statement.setString(1, givenDate);

        ResultSet resultSet = statement.executeQuery();
        int returned_product_id = 0;

        while (resultSet.next()) {
            returned_product_id = resultSet.getInt("id");
        }
        return returned_product_id;
    }
}
