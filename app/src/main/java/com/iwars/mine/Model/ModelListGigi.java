package com.iwars.mine.Model;

public class ModelListGigi {

    String tanggal, ket_status, nama, no_antrian;

    public ModelListGigi(){}

    public ModelListGigi(String no_antrian, String nama, String ket_status, String tanggal) {
        this.tanggal= tanggal;
        this.no_antrian = no_antrian;
        this.nama = nama;
        this.ket_status = ket_status;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKet_status(){
        return ket_status;
    }

    public void setKet_status(String ket_status){
        this.ket_status = ket_status;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_antrian() {
        return no_antrian;
    }

    public void setNo_antrian(String no_antrian) {
        this.no_antrian = no_antrian;
    }

}
