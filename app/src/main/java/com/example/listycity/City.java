package com.example.listycity;

public class City {

    private String city;
    private String province;

    public City(String city, String province) {
        this.city = city;
        this.province = province;
    }

    public String getCityName() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvinceName() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
