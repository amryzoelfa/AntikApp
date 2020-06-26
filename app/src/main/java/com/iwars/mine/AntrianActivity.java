package com.iwars.mine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class AntrianActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonvumum, buttonvgigi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antrian);

        initControl();
    }

    private void initControl() {
        buttonvumum = (Button) findViewById(R.id.buttonvumum);
        buttonvumum.setOnClickListener(this);
        buttonvgigi = (Button) findViewById(R.id.buttonvgigi);
        buttonvgigi.setOnClickListener(this);

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
//            case R.id.btnRiwayat:
//                Intent riwayat = new Intent(BerandaActivity.this, RiwayatActivity.class);
//                startActivity(riwayat);
//                break;
//            case R.id.btnProfil:
//                Intent profil = new Intent(BerandaActivity.this, ProfilActivity.class);
//                startActivity(profil);
//                break;
        }
    }
}
