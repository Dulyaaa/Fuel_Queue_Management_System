package com.example.fuelqueue.screen.user.fuelType;

import com.example.fuelqueue.model.FuelStock;

import java.util.List;

public class DataModel {

    private final List<FuelStock> nestedList;
    private final String itemText;
    private final String status;
    private boolean isExpandable;

    public DataModel(List<FuelStock> nestedList, String itemText, String status) {
        this.nestedList = nestedList;
        this.itemText = itemText;
        this.status = status;
    }

    public void setExpandable(boolean expandable) {
        isExpandable = expandable;
    }

    public List<FuelStock> getNestedList() {
        return nestedList;
    }

    public String getItemText() {
        return itemText;
    }

    public boolean isExpandable() {
        return isExpandable;
    }

    public String getStatus() {
        return status;
    }
}