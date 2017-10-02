package com.ravi.practiceapp.DBentities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.nio.channels.FileLock;

/**
 * Created by ravi on 01/10/17.
 */

@Entity
public class CartProducts {

    @PrimaryKey(autoGenerate = true)
    private int slNo;

    private String productName;

    @ColumnInfo(name = "productImage")
    private String image;

    private String vendorName;

    private String vendorAddress;

    private String vendorPhone;

    private Float productPrice;

    public Float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Float productPrice) {
        this.productPrice = productPrice;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getVendorPhone() {
        return vendorPhone;
    }

    public void setVendorPhone(String vendorPhone) {
        this.vendorPhone = vendorPhone;
    }

    public int getSlNo() {
        return slNo;
    }

    public void setSlNo(int slNo) {
        this.slNo = slNo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
