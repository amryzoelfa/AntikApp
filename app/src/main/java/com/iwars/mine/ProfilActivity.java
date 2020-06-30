package com.iwars.mine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.iwars.mine.Model.ModelProfil;
import com.iwars.mine.Model.ModelRiwayat;
import com.iwars.mine.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class ProfilActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;
    //String username, nama, id_user, id_akses, foto;

    private TextView id_user, no_identitas, nama, jenis_kelamin, ttl, no_hp, alamat;
    private Button editProfil, gantiPassword, logout;
    private ImageView foto;
    //private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        //mQueue = Volley.newRequestQueue(this);

        initControl();
        //loadProfil();

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String mIdUser = user.get(sessionManager.ID_USER);
        String mID = user.get(sessionManager.NO_IDENTITAS);
        String mNama = user.get(sessionManager.NAMA);
        String mJk = user.get(sessionManager.JENIS_KELAMIN);
        String mTTL = user.get(sessionManager.TEMPAT_LAHIR) + "," + user.get(sessionManager.TANGGAL_LAHIR);
        String mAlamat = user.get(sessionManager.ALAMAT);
        String mNo = user.get(sessionManager.NO_HP);
        String mFoto = user.get(sessionManager.FOTO);
        String URL_FOTO = "http://antik.mif-project.com/assets/img/"+mFoto;

        //set foto
        Glide.with(ProfilActivity.this)
                // LOAD URL DARI INTERNET
                .load(URL_FOTO)
                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                //. LOAD GAMBAR SAAT TERJADI KESALAHAN MEMUAT GMBR UTAMA
                .error(R.drawable.ic_account_circle_black_24dp)
                .into(foto);
        no_identitas.setText(mID);
        nama.setText(mNama);
        jenis_kelamin.setText(mJk);
        ttl.setText(mTTL);
        no_hp.setText(mNo);
        alamat.setText(mAlamat);
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
