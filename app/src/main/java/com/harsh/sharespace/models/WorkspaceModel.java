package com.harsh.sharespace.models;

public class WorkspaceModel {
    String name;
    String resources;
    Double pricePerDay;
    String address;
    String owner;

    public WorkspaceModel(String name, String resources, Double pricePerDay, String address, String owner){
        this.name = name;
        this.resources = resources;
        this.pricePerDay = pricePerDay;
        this.address = address;
        this.owner = owner;
    }

    public String getName() {
        return name;
    }
    public String getResources() {
        return resources;
    }
    public Double getPricePerDay() {
        return pricePerDay;
    }
    public String getAddress() {
        return address;
    }
    public String getOwner() {
        return owner;
    }
}
