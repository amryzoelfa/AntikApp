package com.iwars.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iwars.mine.Util.AppController;
import com.iwars.mine.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AntrianActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonvumum, buttonvgigi, buttonUmum, buttonGigi;
    ProgressDialog pd;
    SessionManager sessionManager;

    private String mIdUser;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrian);

        mQueue = Volley.newRequestQueue(this);

        //pd = new ProgressDialog(AntrianActivity.this);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        mIdUser = user.get(sessionManager.ID_USER);

        initControl();
        loadTotalUmum();
        loadTotalGigi();
    }

    private void loadTotalGigi() {
        String url = ServerAPI.JUMLAH_GIGI;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);

                        String totalGigi = data.getString("jumlah_ag");

                        TextView tvTotalGigi = findViewById(R.id.jGigi);

                        tvTotalGigi.setText(totalGigi);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(jsonArrayRequest);
    }

    private void loadTotalUmum() {

        String url = ServerAPI.JUMLAH_UMUM;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);

                        String totalUmum = data.getString("jumlah_au");

                        TextView tvTotalUmum = findViewById(R.id.jUmum);

                        tvTotalUmum.setText(totalUmum);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(jsonArrayRequest);
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
        pd = new ProgressDialog(this);
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
        pd = new ProgressDialog(this);
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
