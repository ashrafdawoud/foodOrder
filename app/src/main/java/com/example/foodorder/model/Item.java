package com.example.foodorder.model;

import java.util.List;

public class Item {
    List<String> _id,name,size , discount ,price,discription,image_url;

    public Item(List <String> _id, List <String> name, List <String> size, List <String> discount, List <String> price, List <String> discription, List <String> image_url) {
        this._id = _id;
        this.name = name;
        this.size = size;
        this.discount = discount;
        this.price = price;
        this.discription = discription;
        this.image_url = image_url;
    }

    public List <String> get_id() {
        return _id;
    }

    public void set_id(List <String> _id) {
        this._id = _id;
    }

    public List <String> getName() {
        return name;
    }

    public void setName(List <String> name) {
        this.name = name;
    }

    public List <String> getSize() {
        return size;
    }

    public void setSize(List <String> size) {
        this.size = size;
    }

    public List <String> getDiscount() {
        return discount;
    }

    public void setDiscount(List <String> discount) {
        this.discount = discount;
    }

    public List <String> getPrice() {
        return price;
    }

    public void setPrice(List <String> price) {
        this.price = price;
    }

    public List <String> getDiscription() {
        return discription;
    }

    public void setDiscription(List <String> discription) {
        this.discription = discription;
    }

    public List <String> getImage_url() {
        return image_url;
    }

    public void setImage_url(List <String> image_url) {
        this.image_url = image_url;
    }
}
