package com.structure;

public class PortInfo {
    String portType;
    Integer quantity;

    public String getPortType() {
        return portType;
    }

    public void setPortType(String portType) {
        this.portType = portType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PortInfo{" +
                "portType='" + portType + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
