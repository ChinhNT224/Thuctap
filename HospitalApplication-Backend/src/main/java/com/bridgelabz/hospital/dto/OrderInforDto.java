package com.bridgelabz.hospital.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class OrderInforDto {
    private String ho_ten_nguoi_benh;
    private LocalDateTime ngay_tao;
    private LocalDateTime ngay_tiep_nhan;
    private String trang_thai;

    public OrderInforDto(String ho_ten_nguoi_benh, LocalDateTime ngay_tao, LocalDateTime ngay_tiep_nhan, String trang_thai) {
        this.ho_ten_nguoi_benh = ho_ten_nguoi_benh;
        this.ngay_tao = ngay_tao;
        this.ngay_tiep_nhan = ngay_tiep_nhan;
        this.trang_thai = trang_thai;
    }

    public OrderInforDto() {

    }

    public String getHo_ten_nguoi_benh() {
        return ho_ten_nguoi_benh;
    }

    public void setHo_ten_nguoi_benh(String ho_ten_nguoi_benh) {
        this.ho_ten_nguoi_benh = ho_ten_nguoi_benh;
    }

    public LocalDateTime getNgay_tao() {
        return ngay_tao;
    }

    public void setNgay_tao(LocalDateTime ngay_tao) {
        this.ngay_tao = ngay_tao;
    }

    public LocalDateTime getNgay_tiep_nhan() {
        return ngay_tiep_nhan;
    }

    public void setNgay_tiep_nhan(LocalDateTime ngay_tiep_nhan) {
        this.ngay_tiep_nhan = ngay_tiep_nhan;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

}

