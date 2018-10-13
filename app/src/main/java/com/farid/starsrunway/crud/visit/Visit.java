package com.farid.starsrunway.crud.visit;

public class Visit {
    private String v_id;
    private String v_tanggal;
    private String v_jam;
    private String v_latitude;
    private String v_longitude;
    private String v_kd_toko;
    private String v_status;
    private String v_ket;
    private String v_kd_asm;

    public void setV_Id (String v_id) {
        this.v_id = v_id;
    }

    public String getV_Id() {
        return v_id;
    }

    public void setTanggal (String v_tanggal) {
        this.v_tanggal = v_tanggal;
    }

    public String getTanggal() {
        return v_tanggal;
    }

    public void setJam (String v_jam) {
        this.v_jam = v_jam;
    }

    public String getJam() {
        return v_jam;
    }

    public void setLatitude (String v_latitude) {
        this.v_latitude = v_latitude;
    }

    public String getLatitude() {
        return v_latitude;
    }

    public void setLongitude (String v_longitude) {
        this.v_longitude = v_longitude;
    }

    public String getLongitude() {
        return v_longitude;
    }

    public void setKd_Toko (String v_kd_toko) {
        this.v_kd_toko = v_kd_toko;
    }

    public String getKd_toko() {
        return v_kd_toko;
    }

    public void setKet (String v_ket) {
        this.v_ket = v_ket;
    }

    public String getKet() {
        return v_ket;
    }

    public void setStatus (String v_status) {
        this.v_status = v_status;
    }

    public String getStatus() {
        return v_status;
    }

    public void setKd_Asm (String v_kd_asm) {
        this.v_kd_asm = v_kd_asm;
    }

    public String getKd_Asm() {
        return v_kd_asm;
    }

    public String getLatitudem(String v_latitude) {
        return v_latitude;
    }

    public String getLongitudem(String string) {
        return v_latitude;
    }
}
