package com.iwars.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;
import com.iwars.mine.Util.ServerAPI;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditProfilActivity extends AppCompatActivity {

    //    id user yang login, sementara manual
    String idUser = "1";

    TextInputLayout txtNama, txtAlamat, txtJk, txtTtl, txtTempat;
    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    Button btnUpdateProfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        initViews();

        btnUpdateProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput(txtNama, "harap isi nama") & validateInput(txtAlamat, "harap isi alamat") & validateInput(txtJk, "harap isi jenis kelamin") & validateInput(txtTtl, "harap isi TTL") & validateInput(txtTempat, "harap isi tempat lahir")) {
                    processUpdate();
                    //                    Toast.makeText(getApplicationContext(), "Mantul", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initViews() {
        txtNama = findViewById(R.id.txtnama);
        txtAlamat = findViewById(R.id.txtalamat);
        txtJk = findViewById(R.id.txtjeniskelamin);
        txtTtl = findViewById(R.id.ttl);
        txtTempat = findViewById(R.id.txttempat);
        btnUpdateProfil = findViewById(R.id.buttonEditProfil);

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
        progressDialog.setMessage("merubah password....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = ServerAPI.UPDATE_PROFIL;

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
                params.put("id_user", idUser);
                params.put("nama", txtNama.getEditText().getText().toString().trim());
                params.put("alamat", txtAlamat.getEditText().getText().toString().trim());
                params.put("jenis_kelamin", txtJk.getEditText().getText().toString().trim());
                params.put("tempat_lahir", txtTempat.getEditText().getText().toString().trim());
                params.put("tanggal_lahir", txtTtl.getEditText().getText().toString().trim());
                return params;
            }
        };

        requestQueue.add(updateRequest);
    }
}
