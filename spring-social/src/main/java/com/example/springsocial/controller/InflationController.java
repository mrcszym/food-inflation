package com.example.springsocial.controller;

import com.example.springsocial.model.Inflation;
import com.example.springsocial.repository.InflationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

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

    @GetMapping("/inflation/createTable")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    public static Inflation[] getInflationDateValueFromDb(@RequestParam("s") String startDate, @RequestParam("e") String endDate) throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://mysqldb:3306/foodinfdb", "root", "SpaceMysql1!");
        Statement stmt = conn.createStatement();

        int returnedInflationInt1 = getSelectDate(startDate, conn);
        int returnedInflationInt2 = getSelectDate(endDate, conn);

        return makeBetweenStatement(conn, returnedInflationInt2, returnedInflationInt1);
    }

    static Inflation[] makeBetweenStatement(Connection conn, int returnedInflationInt1, int returnedInflationInt2) throws SQLException {

        String finalQuery = "SELECT id, month_name, inf_value FROM inflation WHERE id BETWEEN ? AND ? ORDER BY id ASC";
        PreparedStatement betweenStatement = conn.prepareStatement(finalQuery);
        betweenStatement.setInt(1, returnedInflationInt1);
        betweenStatement.setInt(2, returnedInflationInt2);
        ResultSet betweenResult = betweenStatement.executeQuery();

        Inflation[] inflationArray = new Inflation[returnedInflationInt2-returnedInflationInt1 + 1];
        int counter = 0;

        while (betweenResult.next()) {
            inflationArray[counter] = new Inflation(betweenResult.getLong("id"),
                   betweenResult.getString("month_name"),
                    betweenResult.getString("inf_value"));
            counter++;
        }

        return inflationArray;
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
