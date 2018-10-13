package com.farid.starsrunway.crud.toko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farid.starsrunway.R;

public class TokoActivity extends AppCompatActivity implements View.OnClickListener {
    Button ViewToko, ViewMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toko);

        ViewToko = findViewById(R.id.btnViewToko);
        ViewMaps = findViewById(R.id.btnViewMaps);

        ViewToko.setOnClickListener(this);
        ViewMaps.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == ViewToko){
            startActivity(new Intent(this, ReadTokoActivity.class));
        }
        if (v == ViewMaps){

        }
    }
}
