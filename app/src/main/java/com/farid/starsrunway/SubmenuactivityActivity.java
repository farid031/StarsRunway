package com.farid.starsrunway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.farid.starsrunway.submenuactivity.PiActivity;
import com.farid.starsrunway.submenuactivity.SamplingActivity;
import com.farid.starsrunway.submenuactivity.VisitActivity;
import com.farid.starsrunway.login.SessionManager;

public class SubmenuactivityActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager session;
    Button BtnVisit, BtnSampling, BtnPi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenuactivity);

        BtnVisit   = findViewById(R.id.btnVisit);
        BtnSampling = findViewById(R.id.btnSampling);
        BtnPi       = findViewById(R.id.btnPi);

        BtnSampling.setOnClickListener(this);
        BtnVisit.setOnClickListener(this);
        BtnPi.setOnClickListener(this);

        session = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        session.checkLogin();
    }

    @Override
    public void onClick(View v) {
        if (v == BtnVisit){
            visit();
        }
        if (v == BtnSampling){
            sampling();
        }
        if (v == BtnPi){
            startActivity(new Intent(this, PiActivity.class));
        }
    }
    void visit(){
        startActivity(new Intent(SubmenuactivityActivity.this, VisitActivity.class));
    }
    void sampling(){
        startActivity(new Intent(SubmenuactivityActivity.this, SamplingActivity.class));
    }
}
