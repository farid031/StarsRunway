package com.farid.starsrunway.crud.visit;

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
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.farid.starsrunway.helper.AsmHandler;
import com.farid.starsrunway.helper.Konfigurasi;
import com.farid.starsrunway.R;
import com.farid.starsrunway.helper.RequestHandler;
import com.farid.starsrunway.login.SessionManager;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.maps.model.Marker;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

public class CreateOutActivity extends AppCompatActivity{

    GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Marker mCurrLocationMarker;
    private Location mLastLocation;
    ProgressDialog  pDialog;
    JSONArray JsonArrayAsm = null;
    List<String> valueAsm = new ArrayList<String>();
    List<String> valueNamaAsm = new ArrayList<String>();
    TextView TextJam, TextStatus, TextTanggal, TextLatitude, TextLongitude, TextAsm;
    EditText EditKodeToko, EditKet;
    Spinner Asm;
    AutoCompleteTextView AutoAsm;
    Button addBtn;
    String tanggal, jam, latitude, longitude, kodeToko, status = "2", kodeAsm, ket;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_outvisit);

        TextTanggal  = findViewById(R.id.textTanggal);
        TextJam      = findViewById(R.id.editTextJam);
        TextLatitude = findViewById(R.id.textLatitude);
        TextLongitude = findViewById(R.id.textLongitude);
        EditKodeToko = findViewById(R.id.editKodeToko);
        TextStatus   = findViewById(R.id.textStatus);
        Asm      = findViewById(R.id.spinnerAsm);
        TextAsm      = findViewById(R.id.textAsm);
        AutoAsm      = findViewById(R.id.autoCompleteAsm);
        EditKet      = findViewById(R.id.editKet);
        addBtn       = findViewById(R.id.buttonAdd_OutVisit);

        session = new SessionManager(getApplicationContext());

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (session.isLoggedIn()){
                    addData();
                }else {
                    Toast.makeText(CreateOutActivity.this, "Harus Login dulu", Toast.LENGTH_LONG).show();
                }
            }
        });

        TextStatus.setText(status);
        Tanggal tgl = new Tanggal();
        TextTanggal.setText(tgl.getTanggal());
        TextJam.setText(tgl.getWaktu());

        //Mengolah data lokasi menggunakan GPS
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //Mengambil data dari GPS
        LocationListener locListener = new CreateOutActivity.MyLocationListener();
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

        getDataAsm a = new getDataAsm();
        a.execute();
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
        kodeAsm = TextAsm.getText().toString().trim();
        ket = EditKet.getText().toString().trim();

        @SuppressLint("StaticFieldLeak")
        class CreateTask extends AsyncTask<String, String, String> {
            private ProgressDialog loading;

            @Override
            protected String doInBackground(String... strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_TANGGAL, tanggal);
                params.put(Konfigurasi.KEY_JAM, jam);
                params.put(Konfigurasi.KEY_LONGITUDE, longitude);
                params.put(Konfigurasi.KEY_LATITUDE, latitude);
                params.put(Konfigurasi.KEY_KD_TOKO, kodeToko);
                params.put(Konfigurasi.KEY_STATUS, status);
                loading = ProgressDialog.show(CreateOutActivity.this, "Menambahkan...", "Tunggu...", false, false);
                params.put(Konfigurasi.KEY_KET, ket);
                params.put(Konfigurasi.KEY_KD_ASM, kodeAsm);

                RequestHandler rh = new RequestHandler();
                return rh.sendPostRequest(Konfigurasi.URL_INSERT_OUTVISIT, params);
            }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(CreateOutActivity.this, "Menambahkan...", "Tunggu...", false, false);
        }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(CreateOutActivity.this, s, Toast.LENGTH_LONG).show();
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
            TextLatitude.setError("Tunggu latitude sampai muncul");
        }
        if (TextUtils.isEmpty(longitude)){
            isEmptyFields = true;
            TextLongitude.setError("Tunggu longitude sampai muncul");
        }
        if (TextUtils.isEmpty(kodeToko)){
            isEmptyFields = true;
            EditKodeToko.setError("Kode Toko tidak boleh kosong");
        }
        if (TextUtils.isEmpty(status)){
            isEmptyFields = true;
            TextStatus.setError("Jam tidak boleh kosong");
        }
        if (TextUtils.isEmpty(kodeAsm)){
            isEmptyFields = true;
            TextAsm.setError("Kode ASM tidak boleh kosong");
        }
        if (!isEmptyFields) {
            CreateTask ct = new CreateTask();
            ct.execute();
            startActivity(new Intent(CreateOutActivity.this, ReadOutActivity.class));
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

    //Menampilkan data ASM ke dalam Spinner
    @SuppressLint("StaticFieldLeak")
    private class getDataAsm extends AsyncTask<Void, Void, Void> implements TextWatcher {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CreateOutActivity.this);
            pDialog.setMessage("Mengambil data ASM...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            //Membuat Service "ServiceHandler"
            AsmHandler ah = new AsmHandler();

            // Memanggil URL untuk mendapatkan respon data
            String jsonStr = ah.makeServiceCall(Konfigurasi.URL_ASM, AsmHandler.GET); //siswaManager.php?mode=getAllDataSiswa

            Log.d("Response: ", "> " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Mendapatkan data Array JSON
                    JsonArrayAsm = jsonObj.getJSONArray("values");

                    ArrayList<ASM> listDataSiswa = new ArrayList<ASM>();
                    listDataSiswa.clear();

                    //Melakukan perulangan untuk memecah data
                    for (int i = 0; i < JsonArrayAsm.length(); i++) {
                        JSONObject obj = JsonArrayAsm.getJSONObject(i);

                        ASM asm = new ASM();
                        asm.setAsm(obj.getString("asm"));
                        asm.setNamaAsm(obj.getString("nmasm"));
                        listDataSiswa.add(asm);
                    }

                    valueAsm = new ArrayList<String>();
                    valueNamaAsm = new ArrayList<String>();

                    for (int i = 0; i < listDataSiswa.size(); i++) {
                        valueAsm.add(listDataSiswa.get(i).getAsm());
                        valueNamaAsm.add(listDataSiswa.get(i).getNamaAsm());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Couldn't get any data from the url");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

            // Membuat adapter untuk spinner
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(CreateOutActivity.this,
                    android.R.layout.simple_spinner_item, valueAsm);

            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            //Mengaitkan adapter spinner dengan spinner yang ada di layout
            Asm.setAdapter(spinnerAdapter);
            Asm.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    String ASM = valueAsm.get(position);
                    String NamaAsm = valueNamaAsm.get(position);
                    TextAsm.setText(ASM);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });

            AutoAsm.addTextChangedListener(this);
            AutoAsm.setAdapter(new ArrayAdapter<String>(CreateOutActivity.this, android.R.layout.simple_expandable_list_item_1, valueAsm));
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            TextAsm.setText(AutoAsm.getText());
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
