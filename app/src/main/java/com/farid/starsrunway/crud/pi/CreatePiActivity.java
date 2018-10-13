package com.farid.starsrunway.crud.pi;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.farid.starsrunway.R;
import com.farid.starsrunway.helper.Konfigurasi;
import com.farid.starsrunway.helper.RequestHandler;
import com.farid.starsrunway.login.SessionManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.Marker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;

public class CreatePiActivity extends AppCompatActivity {

    TextView TextJam, TextStatus, TextTanggal, TextLatitude, TextLongitude;
    EditText EditKodeToko;
    Button addBtn;
    String tanggal, jam, latitude, longitude, status, kodeToko;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pi);

        TextTanggal  = findViewById(R.id.pi_textTanggal);
        TextJam      = findViewById(R.id.pi_textJam);
        TextLatitude = findViewById(R.id.pi_textLatitude);
        TextLongitude = findViewById(R.id.pi_textLongitude);
        EditKodeToko = findViewById(R.id.pi_editKodeToko);
        TextStatus   = findViewById(R.id.pi_textStatus);
        addBtn       = findViewById(R.id.buttonAdd_Pi);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });


        TextStatus.setText("1");
        Tanggal tgl = new Tanggal();
        TextTanggal.setText(tgl.getTanggal());
        TextJam.setText(tgl.getWaktu());

        //Mengolah data lokasi menggunakan GPS
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Mengambil data dari GPS
        LocationListener locListener = new MyLocationListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        assert lm != null;
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 1, locListener);
    }

    private class MyLocationListener implements LocationListener{

        @Override
        public void onLocationChanged(Location loc) {
            if (loc !=null){
                TextLatitude.setText(String.valueOf(loc.getLatitude()));
                TextLongitude.setText(String.valueOf(loc.getLongitude()));
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String arg0) {

        }

        @Override
        public void onProviderDisabled(String arg0) {

        }
    }

    //fungsi create data
    private void addData() {
        tanggal = TextTanggal.getText().toString().trim();
        jam = TextJam.getText().toString().trim();
        latitude = TextLatitude.getText().toString().trim();
        longitude = TextLongitude.getText().toString().trim();
        kodeToko = EditKodeToko.getText().toString().trim();
        status = TextStatus.getText().toString().trim();

        @SuppressLint("StaticFieldLeak")
        class CreateTask extends AsyncTask<String, String, String> {
            private ProgressDialog loading;

            @Override
            protected String doInBackground(String... strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_TANGGAL, tanggal);
                params.put(Konfigurasi.KEY_JAM, jam);
                params.put(Konfigurasi.KEY_LATITUDE, latitude);
                params.put(Konfigurasi.KEY_LONGITUDE, longitude);
                params.put(Konfigurasi.KEY_KD_TOKO, kodeToko);
                params.put(Konfigurasi.KEY_STATUS, status);

                RequestHandler rh = new RequestHandler();
                return rh.sendPostRequest(Konfigurasi.URL_INSERT_PI, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(CreatePiActivity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(CreatePiActivity.this, s, Toast.LENGTH_LONG).show();
            }
        }

        boolean isEmptyFields = false;
        if (TextUtils.isEmpty(tanggal)){
            isEmptyFields = true;
            TextTanggal.setError("Tanggal tidak boleh kosong");
        }
        if (TextUtils.isEmpty(jam)){
            isEmptyFields = true;
            TextJam.setError("Jam tidak boleh kosong");
        }
        if (TextUtils.isEmpty(latitude)){
            isEmptyFields = true;
            TextLatitude.setError("Tunggu nilai latitude sampai keluar");
        }
        if (TextUtils.isEmpty(longitude)){
            isEmptyFields = true;
            TextLongitude.setError("Tunggu nilai longitude sampai keluar");
        }
        if (TextUtils.isEmpty(kodeToko)){
            isEmptyFields = true;
            EditKodeToko.setError("Kode Toko tidak boleh kosong");
        }
        if (TextUtils.isEmpty(status)){
            isEmptyFields = true;
            TextStatus.setError("Jam tidak boleh kosong");
        }
        if (!isEmptyFields) {
            CreateTask ct = new CreateTask();
            ct.execute();
            startActivity(new Intent(this, ReadPiActivity.class));
        }
    }

    private class Tanggal{
        private String getTanggal() {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
            java.util.Date date = new java.util.Date();
            return dateFormat.format(date);
        }

        private String getWaktu() {
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss", Locale.US);
            java.util.Date date = new java.util.Date();
            return dateFormat.format(date);
        }
    }
}
