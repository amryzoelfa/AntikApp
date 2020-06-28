package com.iwars.mine.Model;

public class ModelProfil {

    String ttl, no_identitas, nama, jenis_kelamin, no_hp, alamat, foto, tanggal_lahir, tempat_lahir;

    public ModelProfil(){}

    public ModelProfil(String ttl, String no_identitas, String nama, String jenis_kelamin, String no_hp, String alamat, String foto, String tempat_lahir, String tanggal_lahir ) {
        this.no_identitas = no_identitas;
        this.nama = nama;
        this.jenis_kelamin = jenis_kelamin;
        this.no_hp = no_hp;
        this.alamat = alamat;
        this.foto = foto;
        this.tempat_lahir = tempat_lahir;
        this.tanggal_lahir = tanggal_lahir;
        this.ttl = ttl;
    }

    public String getNo_identitas() {
        return no_identitas;
    }

    public void setNo_identitas(String no_identitas) {
        this.no_identitas = no_identitas;
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

    public String getTanggal_lahir() {
        return tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getTempat_lahir() {
        return tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String tempat_lahir) {
        this.ttl = ttl;
    }
}
