package com.iwars.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.iwars.mine.Util.ServerAPI;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfilActivity extends AppCompatActivity  {

    //    id user yang login, sementara manual
    //String idUser = "1";

    //ProgressDialog pd;
    SessionManager sessionManager;

    TextInputLayout txtNama, txtAlamat, txtJk, txtTanggal, txtTempat, txtNo;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    Button btnUpdateProfil, btnBatal;

    private String mIdUser, mNama, mJk, mTempat, mTanggal, mAlamat, mNo, mFoto, URL_FOTO;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        initViews();

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        mIdUser = user.get(sessionManager.ID_USER);
        mNama = user.get(sessionManager.NAMA);
        mJk = user.get(sessionManager.JENIS_KELAMIN);
        mTempat = user.get(sessionManager.TEMPAT_LAHIR);
        mTanggal = user.get(sessionManager.TANGGAL_LAHIR);
        mAlamat = user.get(sessionManager.ALAMAT);
        mNo = user.get(sessionManager.NO_HP);
        mFoto = user.get(sessionManager.FOTO);
        URL_FOTO = "http://192.168.43.34/CIANTIK/assets/img/"+mFoto;

        //set foto
        Glide.with(EditProfilActivity.this)
                // LOAD URL DARI INTERNET
                .load(URL_FOTO)
                // LOAD GAMBAR AWAL SEBELUM GAMBAR UTAMA MUNCUL, BISA DARI LOKAL DAN INTERNET
                .placeholder(R.drawable.ic_account_circle_black_24dp)
                //. LOAD GAMBAR SAAT TERJADI KESALAHAN MEMUAT GMBR UTAMA
                .error(R.drawable.ic_account_circle_black_24dp)
                .into(foto);

        btnUpdateProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput(txtNama, "Harap isi Nama") & validateInput(txtAlamat, "Harap isi Alamat") & validateInput(txtJk, "Harap isi Jenis Kelamin") & validateInput(txtTanggal, "Harap Isi Tanggal Lahir") & validateInput(txtTempat, "Harap isi Tempat Lahir") & validateInput(txtNo, "Harap isi No Handphone")) {
                    processUpdate();
                    //                    Toast.makeText(getApplicationContext(), "Mantul", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(EditProfilActivity.this, ProfilActivity.class));
                finish();
            }
        });
    }

    private void initViews() {
        foto = (ImageView) findViewById(R.id.ivfoto);
        txtNama = findViewById(R.id.txtnama);
        txtJk = findViewById(R.id.txtjeniskelamin);
        txtTanggal = findViewById(R.id.txttanggal);
        txtTempat = findViewById(R.id.txttempat);
        txtNo = findViewById(R.id.no_hp);
        txtAlamat = findViewById(R.id.txtalamat);
        btnUpdateProfil = findViewById(R.id.buttonEditProfil);
        btnBatal = findViewById(R.id.buttonBatal);

        progressDialog = new ProgressDialog(this);
        requestQueue = Volley.newRequestQueue(this);
    }

    private boolean validateInput(TextInputLayout editText, String error) {
        String input = editText.getEditText().getText().toString().trim();

        if (input.isEmpty()) {
            editText.setError(error);
            return false;
        } else {
            editText.setError(null);
            editText.setErrorEnabled(false);
            return true;
        }
    }

    private void processUpdate() {
        progressDialog.setMessage("Merubah data diri....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = ServerAPI.UPDATE_PROFIL + mIdUser;

        StringRequest updateRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressDialog.dismiss();

                try{
                    JSONObject hasil = new JSONObject(response);

                    String status = hasil.getString("status");
                    String message = hasil.getString("message");

                    Toast.makeText(getApplicationContext(), status+" - "+message, Toast.LENGTH_LONG).show();

                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", mIdUser);
                params.put("nama", txtNama.getEditText().getText().toString().trim());
                params.put("jenis_kelamin", txtJk.getEditText().getText().toString().trim());
                params.put("tempat_lahir", txtTempat.getEditText().getText().toString().trim());
                params.put("tanggal_lahir", txtTanggal.getEditText().getText().toString().trim());
                params.put("no_hp", txtNo.getEditText().getText().toString().trim());
                params.put("alamat", txtAlamat.getEditText().getText().toString().trim());
                params.put("foto", mFoto);
                return params;
            }
        };

        requestQueue.add(updateRequest);
    }
}
