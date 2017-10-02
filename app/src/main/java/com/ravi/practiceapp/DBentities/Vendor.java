package com.ravi.practiceapp.DBentities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by ravi on 01/10/17.
 */

@Entity
public class Vendor {

    @PrimaryKey(autoGenerate = true)
    private int vendorId;

    private String vendorName;

    private String vendorAddress;

    private String vendorPhone;

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
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
}
