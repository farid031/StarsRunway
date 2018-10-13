package com.farid.starsrunway.crud.pi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.farid.starsrunway.R;

public class ViewDataResultActivity extends AppCompatActivity implements View.OnClickListener {

    private String s_id, tanggal, jam, latitude, longitude, status;

    private Button btnTampil;

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_result);

        //TextView txtId       = findViewById(R.id.TextId);
        TextView txtTanggal = findViewById(R.id.textTanggal);
        TextView txtJam = findViewById(R.id.textJam);
        TextView txtLatitude = findViewById(R.id.textLatitude);
        TextView txtLongitude = findViewById(R.id.textLongitude);
        TextView txtKd_toko = findViewById(R.id.textKd_toko);
        TextView txtKet = findViewById(R.id.textKeterangan);
        TextView txtStatus = findViewById(R.id.textStatus);

        btnTampil = findViewById(R.id.buttonTampil);
        btnTampil.setOnClickListener(this);

        //menangkap bundle yang telah di-parsed dari MainActivity
        Bundle b = getIntent().getExtras();
        assert b != null;
        //String isi_id        = b.getString("id");
        String isi_tanggal = b.getString("tanggal");
        String isi_jam = b.getString("jam");
        String isi_latitude = b.getString("latitude");
        String isi_longitude = b.getString("longitude");
        String isi_kd_toko = b.getString("kodetoko");
        String isi_ket = b.getString("ket");
        String isi_status = b.getString("kodestatus");

        //meng-set bundle tersebut
        //txtId.setText(isi_id);
        txtTanggal.setText(isi_tanggal);
        txtJam.setText(isi_jam);
        txtLatitude.setText(isi_latitude);
        txtLongitude.setText(isi_longitude);
        txtKd_toko.setText(isi_kd_toko);
        txtKet.setText(isi_ket);
        txtStatus.setText(isi_status);
    }

    @Override
    public void onClick(View v) {
        if (v == btnTampil) {
            startActivity(new Intent(this, ReadResultActivity.class));
        }

    }
}
