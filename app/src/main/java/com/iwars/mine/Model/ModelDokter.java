package com.iwars.mine.Model;

public class ModelDokter {

    String nama, jenis_kelamin, no_hp, alamat, foto, ket_akses;

    public ModelDokter(){}

    public ModelDokter(String nama, String jenis_kelamin, String no_hp, String alamat, String foto, String ket_akses) {
        this.nama = nama;
        this.jenis_kelamin = jenis_kelamin;
        this.no_hp = no_hp;
        this.alamat = alamat;
        this.foto = foto;
        this.ket_akses = ket_akses;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis_kelamin(){
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin){
        this.jenis_kelamin= jenis_kelamin;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getKet_akses() {
        return ket_akses;
    }

    public void setKet_akses(String ket_akses) {
        this.ket_akses = ket_akses;
    }

}
