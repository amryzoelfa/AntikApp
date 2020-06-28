package com.iwars.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.iwars.mine.Util.AppController;
import com.iwars.mine.Util.ServerAPI;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AntrianActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonvumum, buttonvgigi, buttonUmum, buttonGigi;
    ProgressDialog pd;
    SessionManager sessionManager;

    private String mIdUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrian);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        mIdUser = user.get(sessionManager.ID_USER);

        initControl();
    }

    private void initControl() {
        buttonvumum = (Button) findViewById(R.id.buttonvumum);
        buttonvumum.setOnClickListener(this);
        buttonvgigi = (Button) findViewById(R.id.buttonvgigi);
        buttonvgigi.setOnClickListener(this);
        buttonUmum = (Button) findViewById(R.id.buttonUmum);
        buttonUmum.setOnClickListener(this);
        buttonGigi = (Button) findViewById(R.id.buttonGigi);
        buttonGigi.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonvumum:
                Intent lihatUmum = new Intent(AntrianActivity.this, AntrianUmumActivity.class);
                startActivity(lihatUmum);
                break;
            case R.id.buttonvgigi:
                Intent lihatGigi = new Intent(AntrianActivity.this, AntrianGigiActivity.class);
                startActivity(lihatGigi);
                break;
            case R.id.buttonUmum:
                insertUmum();
                break;
            case R.id.buttonGigi:
                insertGigi();
                break;
        }
    }

    private void insertGigi() {
        pd.setMessage("Mohon Tunggu...");
        pd.setCancelable(false);
        pd.show();

        String url = ServerAPI.INSERT_GIGI + mIdUser;

        StringRequest sendData = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(AntrianActivity.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(AntrianActivity.this,vAntrianGigiActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(AntrianActivity.this, "pesan : Gagal Ambil Antrian", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", mIdUser);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }

    private void insertUmum() {
        pd.setMessage("Mohon Tunggu...");
        pd.setCancelable(false);
        pd.show();

        String url = ServerAPI.INSERT_UMUM + mIdUser;

        StringRequest sendData = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.cancel();
                        try {
                            JSONObject res = new JSONObject(response);
                            Toast.makeText(AntrianActivity.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        startActivity( new Intent(AntrianActivity.this,vAntrianUmumActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Toast.makeText(AntrianActivity.this, "pesan : Gagal Ambil Antrian", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", mIdUser);
                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(sendData);
    }
}
