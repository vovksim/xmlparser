package com.parser;

import com.structure.Details;
import com.structure.Device;
import com.structure.PortInfo;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.ArrayList;

public class DeviceDOMHandler extends AbstractDOMHandler<Device> {
    @Override
    public void mapValue(String tagName, String nodeValue) {
        switch (tagName) {
            case "name":
                result.getLast().setName(nodeValue);
                break;
            case "origin":
                result.getLast().setOrigin(nodeValue);
                break;
            case "price":
                Integer tmp = Integer.valueOf(nodeValue);
                result.getLast().setPrice(tmp);
                break;
            case "critical":
                result.getLast().setCritical(Boolean.parseBoolean(nodeValue));
                break;
            case "peripheral":
                result.getLast().getDetails().setPeripheral(Boolean.parseBoolean(nodeValue));
                break;
            case "energyConsumption":
                result.getLast().getDetails().setEnergyConsumption(Integer.valueOf(nodeValue));
                break;
            case "hasCooler":
                result.getLast().getDetails().setHasCooler(Boolean.parseBoolean(nodeValue));
                break;
            case "group":
                result.getLast().getDetails().setGroup(nodeValue);
                break;
            case "port":
                result.getLast().getDetails().getPorts().getLast().setPortType(nodeValue);
        }
    }

    @Override
    public void initContainer(String containerNodeName) {
        switch (containerNodeName) {
            case "Devices":
                result = new ArrayList<>();
                break;
            case "Device":
                result.add(new Device());
                break;
            case "type":
                result.getLast().setDetails(new Details());
                break;
            case "port":
                result.getLast().getDetails().addPort(new PortInfo());
        }
    }

    @Override
    public void mapAttributes(Node node) {
        NamedNodeMap attributes = node.getAttributes();
        String tagName = node.getNodeName();
        switch (tagName) {
            case "Device":
                Integer value = Integer.valueOf(attributes.getNamedItem("id").getNodeValue());
                result.getLast().setId(value);
                break;
            case "port":
                result.getLast().getDetails().getPorts().getLast().setQuantity(Integer.valueOf(attributes.getNamedItem("quantity").getNodeValue()));
        }
    }
}
