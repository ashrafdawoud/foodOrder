package com.example.foodorder.model;

import java.util.List;

public class Section {
    List <String> _id, name, image;

    public Section(List <String> _id, List <String> name, List <String> image) {
        this._id = _id;
        this.name = name;
        this.image = image;
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

    public List <String> getImage() {
        return image;
    }

    public void setImage(List <String> image) {
        this.image = image;
    }
}