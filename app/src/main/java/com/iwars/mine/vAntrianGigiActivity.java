package com.iwars.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.iwars.mine.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class vAntrianGigiActivity extends AppCompatActivity {

    ProgressDialog pd;
    SessionManager sessionManager;

    private String mIdUser;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vantrian_gigi);

        mQueue = Volley.newRequestQueue(this);

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        mIdUser = user.get(sessionManager.ID_USER);

        loadAntrianku();
    }

    private void loadAntrianku() {
        String url = ServerAPI.CETAK_GIGI + mIdUser;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject data = response.getJSONObject(i);

                        String gigiku = "B - " + data.getString("gigi");
                        String tgl = data.getString("tanggal");

                        TextView tvTotalGigi = findViewById(R.id.antriangku);
                        TextView tvTgl = findViewById(R.id.tanggal);

                        tvTotalGigi.setText(gigiku);
                        tvTgl.setText(tgl);
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
}
