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
import com.iwars.mine.Adapter.AdapterDokter;
import com.iwars.mine.Model.ModelDokter;
import com.iwars.mine.Util.AppController;
import com.iwars.mine.Util.ServerAPI;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DokterActivity extends AppCompatActivity {

    RecyclerView mRecyclerview;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mManager;
    List<ModelDokter> mItems;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dokter);

        mRecyclerview = (RecyclerView) findViewById(R.id.recyclerviewDokter);
        pd = new ProgressDialog(DokterActivity.this);
        mItems = new ArrayList<>();

        loadJson();

        mManager = new LinearLayoutManager(DokterActivity.this,LinearLayoutManager.VERTICAL,false);
        mRecyclerview.setLayoutManager(mManager);
        mAdapter = new AdapterDokter(DokterActivity.this,mItems);
        mRecyclerview.setAdapter(mAdapter);

    }

    private void loadJson()
    {
        pd.setMessage("Mengambil Data");
        pd.setCancelable(false);
        pd.show();

        JsonArrayRequest reqData = new JsonArrayRequest(Request.Method.POST, ServerAPI.URL_DOKTER,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.cancel();
                        Log.d("volley","response : " + response.toString());
                        for(int i = 0 ; i < response.length(); i++)
                        {
                            try {
                                JSONObject data = response.getJSONObject(i);
                                ModelDokter md = new ModelDokter();
                                md.setFoto(data.getString("foto"));
                                md.setNama(data.getString("nama"));
                                md.setJenis_kelamin(data.getString("jenis_kelamin"));
                                md.setNo_hp(data.getString("no_hp"));
                                md.setAlamat(data.getString("alamat"));
                                md.setKet_akses(data.getString("ket_akses"));
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
