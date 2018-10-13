package com.farid.starsrunway.report;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.farid.starsrunway.R;
import com.farid.starsrunway.report.best_seller.ReadBestSellerActivity;
import com.farid.starsrunway.report.hitlist.ReadHitlistReportActivity;

public class ReportActivity extends AppCompatActivity implements View.OnClickListener {

    Button BtnHitList, BtnBestSeller, BtnRangkingToko;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        BtnHitList = findViewById(R.id.btnReport_Hit);
        BtnBestSeller = findViewById(R.id.btnReport_Best);
        BtnRangkingToko = findViewById(R.id.btnReport_Rangking);

        BtnHitList.setOnClickListener(this);
        BtnBestSeller.setOnClickListener(this);
        BtnRangkingToko.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == BtnHitList){
            startActivity(new Intent(this, ReadHitlistReportActivity.class));
        }
        if (v == BtnBestSeller){
            startActivity(new Intent(this, ReadBestSellerActivity.class));
        }
    }
}
