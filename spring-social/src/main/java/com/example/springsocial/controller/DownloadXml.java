package com.example.springsocial.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;
import java.util.Objects;

public class DownloadXml {
    public static ResultSet RetrieveData(String type) throws Exception {
        return getResultSet(type);
    }

    static ResultSet getResultSet(String type) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://mysqldb:3306/foodinfdb", "root", "SpaceMysql1!");
        Statement stmt = conn.createStatement();
        ResultSet rs;

        if (Objects.equals(type, "products")) {
            rs = stmt.executeQuery("SELECT id, product_name, month_name, price FROM products");
            return rs;
        }

        rs = stmt.executeQuery("SELECT id, month_name, inf_value FROM inflation");
        return rs;
    }

    public static void getProductsToXml() throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        ResultSet rs = RetrieveData("products");

        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("products");
        doc.appendChild(rootElement);

        while (rs.next()) {

            Element products = doc.createElement("product");
            rootElement.appendChild(products);
            products.setAttribute("id", rs.getString("id"));

            Element name = doc.createElement("name");
            name.setTextContent(rs.getString("product_name"));
            products.appendChild(name);

            Element date = doc.createElement("date");
            date.setTextContent(rs.getString("month_name"));
            products.appendChild(date);

            Element price = doc.createElement("price");
            price.setAttribute("currency", "PLN");
            price.setTextContent(rs.getString("price"));
            products.appendChild(price);

            try (FileOutputStream output =
                         new FileOutputStream("C:/Users/Szymon/Desktop/food-inflation/spring-social/products.xml")) {
                writeXml(doc, output);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void getInflationToXml() throws Exception {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        ResultSet rs = RetrieveData("inflation");

        Document doc = docBuilder.newDocument();

        Element rootElement = doc.createElement("intlations");
        doc.appendChild(rootElement);

        while (rs.next()) {

            Element inflations = doc.createElement("inflation");
            rootElement.appendChild(inflations);
            inflations.setAttribute("id", rs.getString("id"));

            Element date = doc.createElement("date");
            date.setTextContent(rs.getString("month_name"));
            inflations.appendChild(date);

            Element inf_value = doc.createElement("inf_value");
            inf_value.setAttribute("currency", "%");
            inf_value.setTextContent(rs.getString("inf_value"));
            inflations.appendChild(inf_value);

            try (FileOutputStream output =
                         new FileOutputStream("C:/Users/Szymon/Desktop/food-inflation/spring-social/inflation.xml")) {
                writeXml(doc, output);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static void writeXml(Document doc, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);

    }
}
