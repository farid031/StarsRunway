package com.farid.starsrunway.crud.pi;

public class Pi {
    private String p_id;
    private String p_tanggal;
    private String p_jam;
    private String p_latitude;
    private String p_longitude;
    private String p_kd_toko;
    private String p_status;
    private String p_ket;

    public void setP_Id (String p_id) {
        this.p_id = p_id;
    }

    public String getP_Id() {
        return p_id;
    }

    public void setTanggal (String p_tanggal) {
        this.p_tanggal = p_tanggal;
    }

    public String getTanggal() {
        return p_tanggal;
    }

    public void setJam (String p_jam) {
        this.p_jam = p_jam;
    }

    public String getJam() {
        return p_jam;
    }

    public void setLatitude (String p_latitude) {
        this.p_latitude = p_latitude;
    }

    public String getLatitude() {
        return p_latitude;
    }

    public void setLongitude (String p_longitude) {
        this.p_longitude = p_longitude;
    }

    public String getLongitude() {
        return p_longitude;
    }

    public void setKd_Toko (String p_kd_toko) {
        this.p_kd_toko = p_kd_toko;
    }

    public String getKd_toko() {
        return p_kd_toko;
    }

    public void setStatus (String p_status) {
        this.p_status = p_status;
    }

    public String getStatus() {
        return p_status;
    }

    public void setKet (String p_ket) {
        this.p_ket = p_ket;
    }

    public String getKet() {
        return p_ket;
    }
}
