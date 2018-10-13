package com.farid.starsrunway.submenuactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farid.starsrunway.R;
import com.farid.starsrunway.crud.pi.CreatePiActivity;
import com.farid.starsrunway.crud.pi.CreateResultActivity;
import com.farid.starsrunway.crud.pi.ReadPiActivity;
import com.farid.starsrunway.crud.pi.ReadResultActivity;

public class PiActivity extends AppCompatActivity implements View.OnClickListener {

    Button BtnPi, BtnResult, BtnViewPi, BtnViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pi);

        BtnPi = findViewById(R.id.btnPi);
        BtnResult = findViewById(R.id.btnResult);
        BtnViewPi = findViewById(R.id.btnViewPi);
        BtnViewResult = findViewById(R.id.btnViewResult);

        BtnPi.setOnClickListener(this);
        BtnResult.setOnClickListener(this);
        BtnViewResult.setOnClickListener(this);
        BtnViewPi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == BtnViewPi){
            startActivity(new Intent(this, ReadPiActivity.class));
        }
        if (v == BtnViewResult){
            startActivity(new Intent(this, ReadResultActivity.class));
        }
        if (v == BtnPi){
            startActivity(new Intent(this, CreatePiActivity.class));
        }
        if (v == BtnResult){
            startActivity(new Intent(this, CreateResultActivity.class));
        }
    }
}
