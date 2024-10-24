package com.parser.SAX;

import com.structure.Details;
import com.structure.Device;
import com.structure.PortInfo;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SAXDeviceHandler extends DefaultHandler {
    private ArrayList<Device> devices;
    private String content;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        switch (qName) {
            case "Devices":
                devices = new ArrayList<>();
                break;
            case "Device":
                devices.add(new Device());
                devices.getLast().setId(Integer.parseInt(attributes.getValue("id")));
                break;
            case "type":
                devices.getLast().setDetails(new Details());
                break;
            case "port":
                devices.getLast().getDetails().addPort(new PortInfo());
                devices.getLast().getDetails().getPorts().getLast().setQuantity(Integer.parseInt(attributes.getValue("quantity")));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        content = String.valueOf(ch, start, length);
    }


    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        switch (qName) {
            case "name":
                devices.getLast().setName(content);
                break;
            case "origin":
                devices.getLast().setOrigin(content);
                break;
            case "price":
                devices.getLast().setPrice(Integer.parseInt(content));
                break;
            case "critical":
                devices.getLast().setCritical(Boolean.parseBoolean(content));
                break;
            case "peripheral":
                devices.getLast().getDetails().setPeripheral(Boolean.parseBoolean(content));
                break;
            case "energyConsumption":
                devices.getLast().getDetails().setEnergyConsumption(Integer.parseInt(content));
                break;
            case "hasCooler":
                devices.getLast().getDetails().setHasCooler(Boolean.parseBoolean(content));
                break;
            case "group":
                devices.getLast().getDetails().setGroup(content);
                break;
            case "port":
                devices.getLast().getDetails().getPorts().getLast().setPortType(content);
        }
    }

    public ArrayList<Device> getResult() {
        return devices;
    }
}

