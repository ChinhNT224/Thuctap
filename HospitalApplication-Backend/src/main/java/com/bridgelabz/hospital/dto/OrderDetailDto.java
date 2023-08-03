package com.bridgelabz.hospital.dto;

import java.sql.Date;
import java.sql.Time;

public class OrderDetailDto {
    private int orderId;
    private String trang_thai;
    private String ho_ten_nguoi_benh;
    private String gioi_tinh;
    private Date ngay_sinh;
    private String dien_thoai;
    private String email;
    private String dia_chi;
    private Date ngay_hen;
    private Time gio_hen;
    private String createdByCustomerName; // Tên của customer tạo order
    private String confirmedByUserName; // Tên của user xác nhận order

    // Constructors, Getters, Setters (tùy theo nhu cầu)

    // Constructors
    public OrderDetailDto() {
    }

    public OrderDetailDto(int orderId, String trang_thai, String ho_ten_nguoi_benh, String gioi_tinh, Date ngay_sinh,
                          String dien_thoai, String email, String dia_chi, Date ngay_hen, Time gio_hen,
                          String createdByCustomerName, String confirmedByUserName) {
        this.orderId = orderId;
        this.trang_thai = trang_thai;
        this.ho_ten_nguoi_benh = ho_ten_nguoi_benh;
        this.gioi_tinh = gioi_tinh;
        this.ngay_sinh = ngay_sinh;
        this.dien_thoai = dien_thoai;
        this.email = email;
        this.dia_chi = dia_chi;
        this.ngay_hen = ngay_hen;
        this.gio_hen = gio_hen;
        this.createdByCustomerName = createdByCustomerName;
        this.confirmedByUserName = confirmedByUserName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getHo_ten_nguoi_benh() {
        return ho_ten_nguoi_benh;
    }

    public void setHo_ten_nguoi_benh(String ho_ten_nguoi_benh) {
        this.ho_ten_nguoi_benh = ho_ten_nguoi_benh;
    }

    public String getGioi_tinh() {
        return gioi_tinh;
    }

    public void setGioi_tinh(String gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public Date getNgay_sinh() {
        return ngay_sinh;
    }

    public void setNgay_sinh(Date ngay_sinh) {
        this.ngay_sinh = ngay_sinh;
    }

    public String getDien_thoai() {
        return dien_thoai;
    }

    public void setDien_thoai(String dien_thoai) {
        this.dien_thoai = dien_thoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public Date getNgay_hen() {
        return ngay_hen;
    }

    public void setNgay_hen(Date ngay_hen) {
        this.ngay_hen = ngay_hen;
    }

    public Time getGio_hen() {
        return gio_hen;
    }

    public void setGio_hen(Time gio_hen) {
        this.gio_hen = gio_hen;
    }

    public String getCreatedByCustomerName() {
        return createdByCustomerName;
    }

    public void setCreatedByCustomerName(String createdByCustomerName) {
        this.createdByCustomerName = createdByCustomerName;
    }

    public String getConfirmedByUserName() {
        return confirmedByUserName;
    }

    public void setConfirmedByUserName(String confirmedByUserName) {
        this.confirmedByUserName = confirmedByUserName;
    }

}
