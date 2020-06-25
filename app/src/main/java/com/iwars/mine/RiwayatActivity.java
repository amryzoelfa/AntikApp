package com.iwars.mine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.iwars.mine.Adapter.AdapterRiwayat;
import com.iwars.mine.Model.ModelRiwayat;
import com.iwars.mine.Util.AppController;
import com.iwars.mine.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RiwayatActivity extends AppCompatActivity {

    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelRiwayat> mItems;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerviewRiwayat);
        pd = new ProgressDialog(RiwayatActivity.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(RiwayatActivity.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterRiwayat(RiwayatActivity.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

    }

    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_RIWAYAT,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelRiwayat md = new ModelRiwayat();
                                md.setTanggal(data.getString("tanggal"));
                                md.setPoli(data.getString("ket_poli"));
                                md.setDiagnosa(data.getString("diagnosa"));
                                md.setTindakan(data.getString("tindakan"));
                                md.setObat(data.getString("resep_obat"));
                                mItems.add(md);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.cancel();
                        Log.d("volley", "error : " + error.getMessage());
                    }
                });

        AppController.getInstance().addToRequestQueue(reqData);
    }
}
