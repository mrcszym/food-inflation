package com.example.springsocial.controller;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public class ReadXml {
    public static void readXml() {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try (InputStream is = readXmlFileIntoInputStream("ceny_zywiec.xml")) {

            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(is);

            if (doc.hasChildNodes()) {
                printNote(doc.getChildNodes());
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void printNote(NodeList nodeList) {

        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);

            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
                System.out.println(tempNode.getTextContent());

                if (tempNode.hasAttributes()) {
                    NamedNodeMap nodeMap = tempNode.getAttributes();
                    for (int i = 0; i < nodeMap.getLength(); i++) {
                        Node node = nodeMap.item(i);
                        System.out.println(node.getNodeName());
                        System.out.println(node.getNodeValue());
                    }
                }
            }
        }
    }
    private static InputStream readXmlFileIntoInputStream(final String fileName) {
        return ReadXml.class.getClassLoader().getResourceAsStream(fileName);
    }
}
