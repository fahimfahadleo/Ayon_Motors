package com.ayonmotors.ayonmotors;

import androidx.annotation.NonNull;

public class transactionmodel {
    String customarname,bikename,chassis,price,date,status;

    public String getCustomarname() {
        return customarname;
    }

    public void setCustomarname(String customarname) {
        this.customarname = customarname;
    }

    public String getBikename() {
        return bikename;
    }

    public void setBikename(String bikename) {
        this.bikename = bikename;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getChassis() {
        return chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @NonNull
    @Override
    public String toString() {
        return getCustomarname()+" "+getCustomarname()+" "+getBikename()+" "+getBikename()+" "+getDate()+" "+getChassis()+" "+getStatus();
    }
}
