package com.bridgelabz.hospital.dto;

public class OrderDto {
    private Long customerId;
    private String trang_thai;

    // Constructors, getters, and setters
    // ...

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }
}

