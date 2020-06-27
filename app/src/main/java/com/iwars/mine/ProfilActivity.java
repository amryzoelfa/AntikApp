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

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;
    //String username, nama, id_user, id_akses, foto;

    private TextView id_user, no_identitas, nama, jenis_kelamin, ttl, no_hp, alamat;
    private Button editProfil, gantiPassword, logout;
    private ImageView foto;
    //SessionManager sessionManager;

    private String mIdUser, mID, mNama, mJk, mTTL, mNo, mUsername, mAlamat, mFoto, URL_FOTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        initControl();
        loadprofil();

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        mIdUser = user.get(sessionManager.ID_USER);
        mFoto = user.get(sessionManager.FOTO);
        URL_FOTO = "http://192.168.43.34/CIANTIK/assets/img/"+mFoto;

        //set nama dari session
//        no_identitas.setText(mNoIdentitas);
//        nama.setText(mNama);
//        alamat.setText(mAlamat);
        //set foto
        Glide.with(ProfilActivity.this)
                // LOAD URL DARI INTERNET
                .load(URL_FOTO)
                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                //. LOAD GAMBAR SAAT TERJADI KESALAHAN MEMUAT GMBR UTAMA
                .error(R.drawable.ic_account_circle_black_24dp)
                .into(foto);
    }

    private void loadprofil() {

    }

    private void initControl() {
        foto = (ImageView) findViewById(R.id.ivfoto);
        no_identitas = (TextView) findViewById(R.id.tvID);
        nama = (TextView) findViewById(R.id.tvNama);
        jenis_kelamin = (TextView) findViewById(R.id.tvJK);
        ttl = (TextView) findViewById(R.id.tvTTL);
        no_hp = (TextView) findViewById(R.id.tvNo);
        alamat = (TextView) findViewById(R.id.tvAlamat);
        editProfil = (Button) findViewById(R.id.editProfil);
        editProfil.setOnClickListener(this);
        gantiPassword = (Button) findViewById(R.id.gantiPassword);
        gantiPassword.setOnClickListener(this);
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.editProfil:
                Intent edit = new Intent(ProfilActivity.this, EditProfilActivity.class);
                startActivity(edit);
                break;
            case R.id.gantiPassword:
                Intent update = new Intent(ProfilActivity.this, GantiPasswordActivity.class);
                startActivity(update);
                break;
            case R.id.logout:
                sessionManager.logout();
                finish();
                break;
        }
    }

}
