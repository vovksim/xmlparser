package com.structure;

public class Device {
    Integer id;
    String name;
    String origin;
    Integer price;
    Boolean isCritical;
    Details details;

    public Device() {
        details = new Details();
    }

    public Device(Integer id, String name, String origin, Integer price, Boolean isCritical, Details details) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.isCritical = isCritical;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getCritical() {
        return isCritical;
    }

    public void setCritical(Boolean critical) {
        isCritical = critical;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Device:\n");
        sb.append('\t').append("id: ").append(id).append("\n");
        sb.append('\t').append("name: ").append(name).append("\n");
        sb.append('\t').append("origin: ").append(origin).append("\n");
        sb.append('\t').append("price: ").append(price).append("\n");
        sb.append('\t').append("isCritical: ").append(isCritical).append("\n");
        sb.append('\t').append(details); // Calls the toString() of Details class
        return sb.toString();
    }
}
