package com.farid.starsrunway.helper.markerhelper;

import com.google.gson.annotations.SerializedName;

public class LocationModel {
    @SerializedName("tanggal")
    private String tanggal;
    @SerializedName("jam")
    private String jam;
    @SerializedName("latitude")
    private String latitude;
    @SerializedName("longitude")
    private String longitude;
    @SerializedName("kodetoko")
    private String kodetoko;
    @SerializedName("kodestatus")
    private String kodestatus;
    @SerializedName("kodeasm")
    private String kodeasm;


    public LocationModel(
            String tanggal,
            String jam,
            String latutide,
            String longitude,
            String kodetoko,
            String kodestatus,
            String kodeasm) {
        this.tanggal = tanggal;
        this.jam = jam;
        this.latitude = latutide;
        this.longitude = longitude;
        this.kodetoko = kodetoko;
        this.kodestatus = kodestatus;
        this.kodeasm = kodeasm;
    }

    public LocationModel() {

    }

    public String getTanggal() {
        return tanggal;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getJam() {
        return jam;
    }
    public void setJam(String jam) {
        this.jam = jam;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getKodeToko() {
        return kodetoko;
    }
    public void setKodeToko(String kodetoko) {
        this.kodetoko = kodetoko;
    }

    public String getKodeStatus() {
        return kodestatus;
    }
    public void setKodeStatus(String kodestatus) {
        this.kodestatus = kodestatus;
    }

    public String getKodeAsm() {
        return kodeasm;
    }
    public void setKodeAsm(String kodeasm) {
        this.kodeasm = kodeasm;
    }
}

