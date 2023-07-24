package com.bridgelabz.hospital.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @ManyToOne
    @JoinColumn(name = "user_id_created_by")
    private Users userCreatedBy;

    @ManyToOne
    @JoinColumn(name = "user_id_confirmed_by")
    private Users userConfirmedBy;

    private Date ngay_dang_ky;
    private String trang_thai;
    private String ho_ten_nguoi_benh;
    private String gioi_tinh;
    private Date ngay_sinh;
    private String dien_thoai;
    private String email;
    private String dia_chi;
    private String xac_nhan;
    private Date ngay_hen;
    private Time gio_hen;
    private Date ngay_tao;
    private Date ngay_tiep_nhan;

    // Constructors
    public Order() {
    }

    public Order(int order_id, Users userCreatedBy, Users userConfirmedBy, Date ngay_dang_ky,
                 String trang_thai, String ho_ten_nguoi_benh, String gioi_tinh, Date ngay_sinh, String dien_thoai,
                 String email, String dia_chi, String xac_nhan, Date ngay_hen, Time gio_hen, Date ngay_tao,
                 Date ngay_tiep_nhan) {
        this.order_id = order_id;
        this.userCreatedBy = userCreatedBy;
        this.userConfirmedBy = userConfirmedBy;
        this.ngay_dang_ky = ngay_dang_ky;
        this.trang_thai = trang_thai;
        this.ho_ten_nguoi_benh = ho_ten_nguoi_benh;
        this.gioi_tinh = gioi_tinh;
        this.ngay_sinh = ngay_sinh;
        this.dien_thoai = dien_thoai;
        this.email = email;
        this.dia_chi = dia_chi;
        this.xac_nhan = xac_nhan;
        this.ngay_hen = ngay_hen;
        this.gio_hen = gio_hen;
        this.ngay_tao = ngay_tao;
        this.ngay_tiep_nhan = ngay_tiep_nhan;
    }

    // Getters and setters
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Users getUserCreatedBy() {
        return userCreatedBy;
    }

    public void setUserCreatedBy(Users userCreatedBy) {
        this.userCreatedBy = userCreatedBy;
    }

    public Users getUserConfirmedBy() {
        return userConfirmedBy;
    }

    public void setUserConfirmedBy(Users userConfirmedBy) {
        this.userConfirmedBy = userConfirmedBy;
    }

    public Date getNgay_dang_ky() {
        return ngay_dang_ky;
    }

    public void setNgay_dang_ky(Date ngay_dang_ky) {
        this.ngay_dang_ky = ngay_dang_ky;
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

    public String getXac_nhan() {
        return xac_nhan;
    }

    public void setXac_nhan(String xac_nhan) {
        this.xac_nhan = xac_nhan;
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

    public Date getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_tao(Date ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public Date getNgay_tiep_nhan() {
        return ngay_tiep_nhan;
    }

    public void setNgay_tiep_nhan(Date ngay_tiep_nhan) {
        this.ngay_tiep_nhan = ngay_tiep_nhan;
    }
}