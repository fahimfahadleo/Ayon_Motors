package com.ayonmotors.ayonmotors;

import androidx.annotation.NonNull;

public class bikemodel {
    String customarname,phonenumber,address,date,
            bikename,registrationnumber,chassisnumber,enginenumber,
            color,manufactureyear,manufacturecountry,cc,brand,price,description;

    public String getCustomarname() {
        return customarname;
    }

    public void setCustomarname(String customarname) {
        this.customarname = customarname;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBikename() {
        return bikename;
    }

    public void setBikename(String bikename) {
        this.bikename = bikename;
    }

    public String getRegistrationnumber() {
        return registrationnumber;
    }

    public void setRegistrationnumber(String registrationnumber) {
        this.registrationnumber = registrationnumber;
    }

    public String getChassisnumber() {
        return chassisnumber;
    }

    public void setChassisnumber(String chassisnumber) {
        this.chassisnumber = chassisnumber;
    }

    public String getEnginenumber() {
        return enginenumber;
    }

    public void setEnginenumber(String enginenumber) {
        this.enginenumber = enginenumber;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getManufactureyear() {
        return manufactureyear;
    }

    public void setManufactureyear(String manufactureyear) {
        this.manufactureyear = manufactureyear;
    }

    public String getManufacturecountry() {
        return manufacturecountry;
    }

    public void setManufacturecountry(String manufacturecountry) {
        this.manufacturecountry = manufacturecountry;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return getCustomarname()+" "+getPhonenumber()+" "+getAddress()+" "+getBikename()+" "+getChassisnumber()+" "+getRegistrationnumber()+" "+getEnginenumber();
    }
}
