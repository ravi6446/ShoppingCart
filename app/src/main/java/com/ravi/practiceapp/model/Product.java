package com.ravi.practiceapp.model;

import java.util.List;

/**
 * Created by ravi on 01/10/17.
 */

public class Product {

    private String productname;
    private Float price;
    private String vendorname;
    private String vendoraddress;
    private String productImg;
    private List<String> productGallery = null;
    private String phoneNumber;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getVendorname() {
        return vendorname;
    }

    public void setVendorname(String vendorname) {
        this.vendorname = vendorname;
    }

    public String getVendoraddress() {
        return vendoraddress;
    }

    public void setVendoraddress(String vendoraddress) {
        this.vendoraddress = vendoraddress;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public List<String> getProductGallery() {
        return productGallery;
    }

    public void setProductGallery(List<String> productGallery) {
        this.productGallery = productGallery;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
