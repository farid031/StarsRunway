package com.farid.starsrunway.submenuactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farid.starsrunway.R;
import com.farid.starsrunway.crud.sampling.CreateComeSamplingActivity;
import com.farid.starsrunway.crud.sampling.CreateGoSamplingActivity;
import com.farid.starsrunway.crud.sampling.ReadComeSamplingActivity;
import com.farid.starsrunway.crud.sampling.ReadGoSamplingActivity;

public class SamplingActivity extends AppCompatActivity implements View.OnClickListener {
    Button BtnCome, BtnGo, BtnViewCome, BtnViewGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sampling);

        BtnCome = findViewById(R.id.btnCome);
        BtnGo   = findViewById(R.id.btnGo);
        BtnViewCome = findViewById(R.id.btnViewCome);
        BtnViewGo = findViewById(R.id.btnViewGo);

        BtnCome.setOnClickListener(this);
        BtnGo.setOnClickListener(this);
        BtnViewCome.setOnClickListener(this);
        BtnViewGo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == BtnCome){
            come();
        }
        if (v == BtnViewCome){
            viewCome();
        }
        if (v == BtnViewGo){
            viewGo();
        }
        if (v == BtnGo){
            go();
        }
    }

    void come(){
        startActivity(new Intent(this, CreateComeSamplingActivity.class));
    }

    void viewCome(){
        startActivity(new Intent(this, ReadComeSamplingActivity.class));
    }

    void go(){
        startActivity(new Intent(this, CreateGoSamplingActivity.class));
    }

    void viewGo(){
        startActivity(new Intent(this, ReadGoSamplingActivity.class));
    }
}
