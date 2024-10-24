package com.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


public class DOMParser {
    Document document;
    AbstractDOMHandler<?> mapper;

    public void parse(String xml, AbstractDOMHandler<?> mapper) throws ParserConfigurationException, IOException, SAXException {
        this.mapper = mapper;
        buildDocumentByPath(xml);
        parse(document.getDocumentElement());
    }

    private void parse(Node node) {
        mapper.initContainer(node.getNodeName());
        NodeList childNodes = node.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node childNode = childNodes.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                if (childNode.getChildNodes().getLength() > 0)
                    parse(childNode);
                if (childNode.hasAttributes())
                    mapper.mapAttributes(childNode);
            }
            if (childNode.getNodeType() == Node.TEXT_NODE) {
                mapper.mapValue(childNode.getParentNode().getNodeName(), childNode.getNodeValue());
            }
        }
    }

    private void buildDocumentByPath(String path) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        this.document = builder.parse(new File(path));
    }
}
