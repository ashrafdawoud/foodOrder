package com.example.foodorder.model;

public class User {
    String _id,firstName,secoundName,email,phone,password;

    public User() {
    }

    public User(String _id, String firstName, String secoundName, String email, String phone, String password) {
        this._id = _id;
        this.firstName = firstName;
        this.secoundName = secoundName;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecoundName() {
        return secoundName;
    }

    public void setSecoundName(String secoundName) {
        this.secoundName = secoundName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
