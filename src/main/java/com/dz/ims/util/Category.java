package com.dz.ims.util;

public enum Category {

    GROCERIES("Groceries"),

    HOMEGOODS("Home Goods"),


    CLOTHING("Clothing"),

    ELECTRONICS("Electronics");

    private String value;

    private Category(String value){
        this.value = value;
    }

    public String value(){
        return value;
    }
}
