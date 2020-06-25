package com.iwars.mine.Model;

public class ModelRiwayat {

    String tanggal, ket_poli, diagnosa, tindakan, resep_obat;

    public ModelRiwayat(){}

    public ModelRiwayat(String tanggal, String poli, String diagnosa, String tindakan, String obat) {
        this.tanggal= tanggal;
        this.ket_poli = poli;
        this.diagnosa = diagnosa;
        this.tindakan = tindakan;
        this.resep_obat = obat;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPoli(){
        return ket_poli;
    }

    public void setPoli(String poli){
        this.ket_poli = poli;
    }

    public String getTindakan() {
        return tindakan;
    }

    public void setTindakan(String tindakan) {
        this.tindakan = tindakan;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public String getObat() {
        return resep_obat;
    }

    public void setObat(String obat) {
        this.resep_obat = obat;
    }
}
