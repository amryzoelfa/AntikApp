package com.iwars.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.iwars.mine.Util.ServerAPI;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class GantiPasswordActivity extends AppCompatActivity {

    //String idUser = "1";
    private String mIdUser;

    EditText textPassLama, textPassNew, textPassConfirm;
    Button btnUpdate, btnBatal;

    ProgressDialog progressDialog;
    RequestQueue requestQueue;

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password);

        initView();

        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        mIdUser = user.get(sessionManager.ID_USER);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput(textPassLama, "harap isi password lama") & validateInput(textPassNew, "harap isi password baru") & validateInput(textPassConfirm, "harap isi konfirmasi password") & validateNewPass(textPassNew, textPassConfirm)) {
                    updatePassword();
//                    Toast.makeText(getApplicationContext(), "Mantul", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnBatal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GantiPasswordActivity.this, ProfilActivity.class));
                finish();
            }
        });
    }

    private void initView() {
        textPassLama = findViewById(R.id.paslam);
        textPassNew = findViewById(R.id.pasnew);
        textPassConfirm = findViewById(R.id.conpas);
        btnUpdate = findViewById(R.id.buttonUpdatePassword);
        btnBatal = findViewById(R.id.buttonBatal);

        progressDialog = new ProgressDialog(this);
        requestQueue = Volley.newRequestQueue(this);
    }

    private boolean validateInput(EditText editText, String error){
        String input = editText.getText().toString().trim();

        if (input.isEmpty()){
            editText.setError(error);
            return false;
        } else {
            editText.setError(null);
            return true;
        }
    }

    private boolean validateNewPass(EditText password, EditText confirm) {
        String inputPass = password.getText().toString().trim();
        String confirmPass = confirm.getText().toString().trim();

        if (inputPass.equals(confirmPass)) {
            confirm.setError(null);
            return true;
        } else {
            confirm.setError("Konfirmasi password tidak sama");
            return false;
        }
    }

    private void updatePassword(){
        progressDialog.setMessage("merubah password....");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String url = ServerAPI.RUBAH_PASS + mIdUser;

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
                params.put("password_lama", textPassLama.getText().toString().trim());
                params.put("password_baru", textPassConfirm.getText().toString().trim());
                return params;
            }
        };

        requestQueue.add(updateRequest);
    }
}
