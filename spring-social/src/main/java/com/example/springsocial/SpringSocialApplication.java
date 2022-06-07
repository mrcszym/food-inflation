package com.example.springsocial;

import com.example.springsocial.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.sql.SQLException;

import static com.example.springsocial.controller.InflationController.getInflationDateValueFromDb;
import static com.example.springsocial.controller.ProductController.getProductsDatePriceFromDb;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringSocialApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		SpringApplication.run(SpringSocialApplication.class, args);
//
//		String nameOfProduct = "chleb 1000g";
//		String startDate = "2021-05";
//		String endDate = "2022-05";
//		selectsCheck(nameOfProduct, startDate, endDate);
	}

	public static void selectsCheck(String nameOfProduct, String startDate, String endDate) throws SQLException, ClassNotFoundException {
		System.out.println("\ngetProductsDatePriceFromDb:");
		System.out.println(getProductsDatePriceFromDb(nameOfProduct, startDate, endDate));
		System.out.println("\ngetInflationDateValueFromDb");
		System.out.println(getInflationDateValueFromDb(startDate, endDate));
	}
}
