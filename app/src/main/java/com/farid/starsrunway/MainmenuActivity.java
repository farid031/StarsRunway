package com.farid.starsrunway;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.farid.starsrunway.crud.toko.TokoActivity;
import com.farid.starsrunway.login.LoginActivity;
import com.farid.starsrunway.login.SessionManager;
import com.farid.starsrunway.report.ReportActivity;

public class MainmenuActivity extends AppCompatActivity implements View.OnClickListener {

    Button BtnActivity, BtnReport, BtnToko, BtnLogout;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        BtnActivity = findViewById(R.id.btnActivity);
        BtnReport   = findViewById(R.id.btnReport);
        BtnToko     = findViewById(R.id.btnToko);
        BtnLogout   = findViewById(R.id.btnLogout);

        BtnActivity.setOnClickListener(this);
        BtnReport.setOnClickListener(this);
        BtnToko.setOnClickListener(this);
        BtnLogout.setOnClickListener(this);

        session = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View v) {
        if (v == BtnActivity){
            Activity();
        }
        if (v == BtnLogout){
            logout();
        }
        if (v == BtnToko){
            startActivity(new Intent(this, TokoActivity.class));
        }
        if (v == BtnReport){
            startActivity(new Intent(this, ReportActivity.class));
        }
    }

    void Activity(){
        startActivity(new Intent(MainmenuActivity.this, SubmenuactivityActivity.class));
    }

    void logout(){
        session.logoutUser();
        finish();
        startActivity(new Intent(MainmenuActivity.this, LoginActivity.class));
    }
    void toko(){

    }
}
