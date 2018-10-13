package com.farid.starsrunway.submenuactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farid.starsrunway.maps.MapsViewInActivity;
import com.farid.starsrunway.R;
import com.farid.starsrunway.crud.visit.CreateInActivity;
import com.farid.starsrunway.crud.visit.CreateOutActivity;
import com.farid.starsrunway.crud.visit.ReadInActivity;
import com.farid.starsrunway.crud.visit.ReadOutActivity;

public class VisitActivity extends AppCompatActivity implements View.OnClickListener {
    Button BtnIn, BtnOut, BtnViewIn, BtnViewOut, BtnViewMaps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit);

        BtnIn   = findViewById(R.id.btnIn);
        BtnOut  = findViewById(R.id.btnOut);
        BtnViewIn = findViewById(R.id.btnViewIn);
        BtnViewOut = findViewById(R.id.btnViewOut);
        BtnViewMaps = findViewById(R.id.btnViewMapsIn);

        BtnIn.setOnClickListener(this);
        BtnOut.setOnClickListener(this);
        BtnViewIn.setOnClickListener(this);
        BtnViewOut.setOnClickListener(this);
        BtnViewMaps.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == BtnViewIn){
            viewIn();
        }
        if (v == BtnViewOut){
            viewOut();
        }
        if (v == BtnIn){
            in();
        }
        if (v == BtnOut){
            out();
        }
        if (v == BtnViewMaps){
            startActivity(new Intent(this, MapsViewInActivity.class));
        }
    }

    void viewIn(){
        startActivity(new Intent(VisitActivity.this, ReadInActivity.class));
    }

    void in(){
        startActivity(new Intent(VisitActivity.this, CreateInActivity.class));
    }

    void out(){
        startActivity(new Intent(VisitActivity.this, CreateOutActivity.class));
    }

    void viewOut(){
        startActivity(new Intent(this, ReadOutActivity.class));
    }
}
