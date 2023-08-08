package com.bridgelabz.hospital.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class OrderAccDTO {
    private int order_id;
    private String nguoi_tao_don;
    private String nguoi_xac_nhan;
    private Date ngay_tao;
    private Date ngay_tiep_nhan;

    public OrderAccDTO(int order_id, String nguoi_tao_don, String nguoi_xac_nhan, Date ngay_tao, Date ngay_tiep_nhan) {
        this.order_id = order_id;
        this.nguoi_tao_don = nguoi_tao_don;
        this.nguoi_xac_nhan = nguoi_xac_nhan;
        this.ngay_tao = ngay_tao;
        this.ngay_tiep_nhan = ngay_tiep_nhan;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getNguoi_tao_don() {
        return nguoi_tao_don;
    }

    public void setNguoi_tao_don(String nguoi_tao_don) {
        this.nguoi_tao_don = nguoi_tao_don;
    }

    public String getNguoi_xac_nhan() {
        return nguoi_xac_nhan;
    }

    public void setNguoi_xac_nhan(String nguoi_xac_nhan) {
        this.nguoi_xac_nhan = nguoi_xac_nhan;
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

    public void add(OrderAccDTO orderAccDTO) {
    }
}
