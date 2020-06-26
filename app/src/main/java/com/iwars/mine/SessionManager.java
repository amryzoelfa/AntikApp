package com.iwars.mine;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public Context context;
    //int MODE_PRIVATE = 0;

    public static final String PREF_NAME = "LOGIN";
    public static final String LOGIN_STATUS = "false";
    public static final String ID_USER = "ID_USER";
    public static final String ID_AKSES = "ID_AKSES";
    public static final String NO_IDENTITAS = "NO_IDENTITAS";
    public static final String NAMA = "NAMA";
    public static final String ALAMAT = "ALAMAT";
    public static final String USERNAME = "USERNAME";
    public static final String FOTO = "FOTO";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String id_user, String id_akses, String no_identitas, String nama, String alamat, String username, String foto) {
        editor.putBoolean(LOGIN_STATUS, true);
        editor.putString(ID_USER, id_user);
        editor.putString(ID_AKSES, id_akses);
        editor.putString(NO_IDENTITAS, no_identitas);
        editor.putString(NAMA, nama);
        editor.putString(ALAMAT, alamat);
        editor.putString(USERNAME, username);
        editor.putString(FOTO, foto);
        editor.apply();
    }

    public boolean isLogin(){
        return sharedPreferences.getBoolean(LOGIN_STATUS,false);
    }

    public void checkLogin(){
        if(!this.isLogin()){
            Intent kembaliLogin = new Intent(context, LoginActivity.class);
            context.startActivity(kembaliLogin);
            ((BerandaActivity)context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(ID_USER,sharedPreferences.getString(ID_USER,null));
        user.put(ID_AKSES,sharedPreferences.getString(ID_AKSES,null));
        user.put(NO_IDENTITAS,sharedPreferences.getString(NO_IDENTITAS,null));
        user.put(NAMA,sharedPreferences.getString(NAMA,null));
        user.put(ALAMAT, sharedPreferences.getString(ALAMAT, null));
        user.put(USERNAME,sharedPreferences.getString(USERNAME, null));
        user.put(FOTO,sharedPreferences.getString(FOTO, null));

        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();

        Intent login = new Intent(context, LoginActivity.class);
        context.startActivity(login);
        ((ProfilActivity)context).finish();
    }

//    public String getUsername() {
//        return sharedPreferences.getString(USERNAME, null);
//    }
//
//    public String getNoIdentitas() {
//        return sharedPreferences.getString(NO_IDENTITAS, null);
//    }
//
//    public String getNama() {
//        return sharedPreferences.getString(NAMA, null);
//    }
//
//    public String getJenisKelamin() {
//        return sharedPreferences.getString(JENIS_KELAMIN, null);
//    }
//
//    public String getAlamat() {
//        return sharedPreferences.getString(ALAMAT, null);
//    }
//
//    public String getTempatLahir() {
//        return sharedPreferences.getString(TEMPAT_LAHIR, null);
//    }
//
//    public String getTanggalLahir() {
//        return sharedPreferences.getString(TANGGAL_LAHIR, null);
//    }
//
//    public String getNoHp() {
//        return sharedPreferences.getString(NO_HP, null);
//    }
//
//    public String getIdUser() {
//        return sharedPreferences.getString(ID_USER, null);
//    }
//
//    public String getIdAkses() {
//        return sharedPreferences.getString(ID_AKSES, null);
//    }
//
//    public String getFoto() {
//        return sharedPreferences.getString(FOTO, null);
//    }
}
