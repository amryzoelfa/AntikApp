package com.iwars.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class BerandaActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;
    //String username, nama, id_user, id_akses, foto;

    private TextView username, id_user, nama;
    private ImageButton btnDokter, btnRiwayat, btnAntrian, btnProfil;
    private ImageView foto;

    private String mId_user, mNama, mUsername, mFoto, URL_FOTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beranda);

        initControl();

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        mId_user = user.get(sessionManager.ID_USER);
        mUsername = user.get(sessionManager.USERNAME);
        mNama = user.get(sessionManager.NAMA);
        mFoto = user.get(sessionManager.FOTO);
        URL_FOTO = "http://192.168.43.34/CIANTIK/assets/img/"+mFoto;

        //set nama dari session
        nama.setText(mNama);
        //set foto
        Glide.with(BerandaActivity.this)
                // LOAD URL DARI INTERNET
                .load(URL_FOTO)
                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                //. LOAD GAMBAR SAAT TERJADI KESALAHAN MEMUAT GMBR UTAMA
                .error(R.drawable.ic_account_circle_black_24dp)
                .into(foto);
    }

    private void initControl() {
        foto = (ImageView) findViewById(R.id.ivFoto);
        nama = (TextView) findViewById(R.id.tvNama);
        btnDokter = (ImageButton) findViewById(R.id.btnDokter);
        btnDokter.setOnClickListener(this);
        btnAntrian = (ImageButton) findViewById(R.id.btnAntrian);
        btnAntrian.setOnClickListener(this);
        btnRiwayat = (ImageButton) findViewById(R.id.btnRiwayat);
        btnRiwayat.setOnClickListener(this);
        btnProfil = (ImageButton) findViewById(R.id.btnProfil);
        btnProfil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDokter:
                Intent dokter = new Intent(BerandaActivity.this, DokterActivity.class);
                startActivity(dokter);
                break;
            case R.id.btnAntrian:
                Intent antrian = new Intent(BerandaActivity.this, AntrianActivity.class);
                startActivity(antrian);
                break;
            case R.id.btnRiwayat:
                Intent riwayat = new Intent(BerandaActivity.this, RiwayatActivity.class);
                startActivity(riwayat);
                break;
            case R.id.btnProfil:
                Intent profil = new Intent(BerandaActivity.this, ProfilActivity.class);
                startActivity(profil);
                break;
        }
    }

}
