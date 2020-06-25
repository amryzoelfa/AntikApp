package com.iwars.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iwars.mine.Util.AppController;
import com.iwars.mine.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private String id_user;
    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin, buttonKeluar;

    private ProgressBar loading;
    private String URL_LOGIN;

    SharedPreferences sharedPreferences;
    boolean doubleBackToExitPressedOnce = false;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);

        ServerAPI login = new ServerAPI();
        URL_LOGIN = login.getURL_LOGIN();
        initControl();

//        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
//        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
//        buttonLogin = (Button) findViewById(R.id.buttonLogin);
//        buttonKeluar = (Button) findViewById(R.id.buttonKeluar);
//
//        //sessionManager = new SessionManager(this);
//        sharedPreferences = getSharedPreferences("DATA", Context.MODE_PRIVATE);
//
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            //mengaktifkan button untuk login
//            @Override
//            public void onClick(View view) {
//                if (!editTextUsername.getText().toString().equals("") || !editTextPassword.getText().toString().equals(""))
//                {
//                    postLogin();
//                }
//                else
//                {
//                    Toast.makeText(LoginActivity.this,"Username atau Password belum terisi", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    private void  initControl(){
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        buttonKeluar = (Button) findViewById(R.id.buttonKeluar);
        buttonKeluar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                String mUsername = editTextUsername.getText().toString().trim();
                String mPassword = editTextUsername.getText().toString().trim();

                if(!mUsername.isEmpty() || !mPassword.isEmpty()){
                    login(mUsername, mPassword);
                }else{
                    editTextUsername.setError("Tolong Masukkan Username");
                    editTextPassword.setError("Tolong Masukkan Password");
                }
                break;
            case R.id.buttonKeluar:
                finish();
                break;
        }
    }

    private void login(final String user, final String pass) {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray responArray = new JSONArray(response);

                            JSONObject data = responArray.getJSONObject(0);
                            String username = data.getString("username");
                            String id_akses = data.getString("id_akses");
                            String id_user = data.getString("id_user");
                            String no_identitas = data.getString("no_identitas");
                            String nama = data.getString("nama").trim();
                            String jenis_kelamin = data.getString("jenis_kelamin");
                            String alamat = data.getString("alamat").trim();
                            String tempat_lahir = data.getString("tempat_lahir");
                            String tanggal_lahir = data.getString("tanggal_lahir");
                            String no_hp = data.getString("no_hp");
                            String foto = data.getString("foto");

                            sessionManager.createSession(username, nama, no_identitas, jenis_kelamin, tempat_lahir, tanggal_lahir, alamat, no_hp, id_akses, id_user, foto);
                            Intent intent = new Intent(LoginActivity.this, BerandaActivity.class);
                            intent.putExtra("id_akses",id_akses);
                            intent.putExtra("id_user",id_user);
                            intent.putExtra("username",username);
                            intent.putExtra("nama",nama);
                            intent.putExtra("no_identitas",no_identitas);
                            intent.putExtra("jenis_kelamin",jenis_kelamin);
                            intent.putExtra("tempat_lahir",tempat_lahir);
                            intent.putExtra("tanggal_lahir",tanggal_lahir);
                            intent.putExtra("alamat",alamat);
                            intent.putExtra("no_hp",no_hp);
                            intent.putExtra("foto",foto);
                            startActivity(intent);
                            progressDialog.dismiss();
                            finish();

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(LoginActivity.this,
                                    "Error!. " +e.toString(),
                                    Toast.LENGTH_SHORT).show();
                            System.out.println(e.toString());
                            progressDialog.dismiss();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,
                                "Error!. " +error.toString(),
                                Toast.LENGTH_SHORT).show();
                        System.out.println(error.toString());
                        progressDialog.dismiss();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("username", editTextUsername.getText().toString());
                params.put("password", editTextPassword.getText().toString());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onBackPressed() {
        // tombol keluar aplikasi
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
