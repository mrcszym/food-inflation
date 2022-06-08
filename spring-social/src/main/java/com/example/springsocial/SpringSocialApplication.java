package com.example.springsocial;

import com.example.springsocial.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.sql.SQLException;
import java.util.Arrays;

import static com.example.springsocial.controller.InflationController.getInflationDateValueFromDb;
import static com.example.springsocial.controller.ProductController.getProductsDatePriceFromDb;
import static com.example.springsocial.controller.ReadJson.readJson;
import static com.example.springsocial.controller.ReadXml.readXml;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringSocialApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringSocialApplication.class, args);

		String nameOfProduct = "chleb 1000g";
		String startDate = "2021-05";
		String endDate = "2022-05";

//		getProductsToJson();
//		getInflationToJson();

		selectsCheck(nameOfProduct, startDate, endDate);

//		getProductsToXml();
//		getInflationToXml();

//		readXml();
//		readJson();
	}

	public static void selectsCheck(String nameOfProduct, String startDate, String endDate) throws SQLException, ClassNotFoundException {
		System.out.println("\ngetProductsDatePriceFromDb:");
		System.out.println(Arrays.toString(getProductsDatePriceFromDb(nameOfProduct, startDate, endDate)));
		System.out.println("\ngetInflationDateValueFromDb:");
		System.out.println(Arrays.toString(getInflationDateValueFromDb(startDate, endDate)));
	}
}
