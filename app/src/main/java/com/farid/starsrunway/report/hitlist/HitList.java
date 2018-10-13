package com.farid.starsrunway.report.hitlist;

public class HitList {
    private String kodeAsm;
    private String namaAsm;
    private String tgl;
    private String kodeToko;
    private String namaToko;
    private String kodeAksi;
    private String verifikasi;
    private String hasilAksi;

    public void setKd_Asm (String kodeAsm){
        this.kodeAsm = kodeAsm;
    }
    public String getKd_Asm(){
        return kodeAsm;
    }

    public void setNama_Asm (String namaAsm){
        this.kodeAsm = namaAsm;
    }
    public String getNama_Asm(){
        return namaAsm;
    }

    public void setTgl (String tgl){
        this.tgl = tgl;
    }
    public String getTgl(){
        return tgl;
    }

    public void setKd_Toko (String kodeToko){
        this.kodeToko = kodeToko;
    }
    public String getKd_Toko(){
        return kodeToko;
    }

    public void setNama_toko (String namaToko){
        this.namaToko = namaToko;
    }
    public String getNamaToko(){
        return namaToko;
    }

    public void setKd_Aksi (String kodeAksi){
        this.kodeAksi = kodeAksi;
    }
    public String getKd_Aksi(){
        return kodeAksi;
    }

    public void setVerifikasi (String verifikasi){
        this.verifikasi = verifikasi;
    }
    public String getVerifikasi(){
        return verifikasi;
    }

    public void setHasilAksi (String hasilAksi){
        this.hasilAksi = hasilAksi;
    }
    public String getHasilAksi(){
        return hasilAksi;
    }
}
