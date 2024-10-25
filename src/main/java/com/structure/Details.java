package com.structure;

import java.util.ArrayList;

public class Details {
    boolean isPeripheral;
    Integer energyConsumption;
    boolean hasCooler;
    String group;
    ArrayList<PortInfo> ports;

    public Details() {
        ports = new ArrayList<>();
    }

    public Details(boolean isPeripheral, boolean hasCooler, Integer energyConsumption, String group, ArrayList<PortInfo> ports) {
        this.isPeripheral = isPeripheral;
        this.energyConsumption = energyConsumption;
        this.group = group;
        this.ports = ports;
        this.hasCooler = hasCooler;
    }

    public boolean isPeripheral() {
        return isPeripheral;
    }

    public void setPeripheral(boolean peripheral) {
        isPeripheral = peripheral;
    }

    public Integer getEnergyConsumption() {
        return energyConsumption;
    }

    public void setEnergyConsumption(Integer energyConsumption) {
        this.energyConsumption = energyConsumption;
    }

    public boolean isHasCooler() {
        return hasCooler;
    }

    public void setHasCooler(boolean hasCooler) {
        this.hasCooler = hasCooler;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public ArrayList<PortInfo> getPorts() {
        return ports;
    }

    public void setPorts(ArrayList<PortInfo> ports) {
        this.ports = ports;
    }

    public void addPort(PortInfo port) {
        this.ports.add(port);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Details:\n");
        sb.append("\t\t").append("isPeripheral: ").append(isPeripheral).append("\n");
        sb.append("\t\t").append("energyConsumption: ").append(energyConsumption).append("\n");
        sb.append("\t\t").append("hasCooler: ").append(hasCooler).append("\n");
        sb.append("\t\t").append("group: ").append(group).append("\n");
        sb.append("\t\t").append("ports: ").append("\n");

        for (PortInfo port : ports) {
            sb.append("\t\t\t").append(port).append("\n"); // Calls PortInfo's toString method
        }

        return sb.toString();
    }
}
