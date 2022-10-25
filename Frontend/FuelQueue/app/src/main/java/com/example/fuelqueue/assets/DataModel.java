package com.example.fuelqueue.assets;

import com.example.fuelqueue.correct.model.FuelStock;

import java.util.List;

public class DataModel {

    private final List<FuelStock> nestedList;
    private final String itemText;
    private boolean isExpandable;

    public DataModel(List<FuelStock> itemList, String itemText) {
        this.nestedList = itemList;
        this.itemText = itemText;
        isExpandable = false;
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
}