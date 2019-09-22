package com.example.foodorder.model;

import java.util.ArrayList;

public class Cart {
    String u_id;
    ArrayList<String>i_id,name,price,image_path;

    public Cart(String u_id, ArrayList <String> i_id, ArrayList <String> name, ArrayList <String> price, ArrayList <String> image_path) {
        this.u_id = u_id;
        this.i_id = i_id;
        this.name = name;
        this.price = price;
        this.image_path = image_path;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public ArrayList <String> getI_id() {
        return i_id;
    }

    public void setI_id(ArrayList <String> i_id) {
        this.i_id = i_id;
    }

    public ArrayList <String> getName() {
        return name;
    }

    public void setName(ArrayList <String> name) {
        this.name = name;
    }

    public ArrayList <String> getPrice() {
        return price;
    }

    public void setPrice(ArrayList <String> price) {
        this.price = price;
    }

    public ArrayList <String> getImage_path() {
        return image_path;
    }

    public void setImage_path(ArrayList <String> image_path) {
        this.image_path = image_path;
    }
}
