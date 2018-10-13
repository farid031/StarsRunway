package com.farid.starsrunway.crud.toko;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.farid.starsrunway.R;
import com.farid.starsrunway.helper.JSONParser;
import com.farid.starsrunway.helper.Konfigurasi;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReadTokoActivity extends AppCompatActivity {

    ListView list;


    JSONParser jParser = new JSONParser();
    ArrayList<Toko> daftar_toko = new ArrayList<Toko>();
    JSONArray daftarToko = null;
    SearchView search;
    TokoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_toko);

        list = (ListView) findViewById(R.id.listview_toko);
        search = findViewById(R.id.search_view);



        //jalankan ReadDataTask
        ReadDataTask m= (ReadDataTask) new ReadDataTask().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                //dapatkan data id, nama, nim mahasiswa dan simpan dalam variable string
                //String str_id = ((TextView) view.findViewById(R.id.str_id)).getText().toString();
                String nama = ((TextView) view.findViewById(R.id.nama)).getText().toString();
                String alamat = ((TextView) view.findViewById(R.id.alamat)).getText().toString();
                String kabupaten = ((TextView) view.findViewById(R.id.kabupaten)).getText().toString();
                String provinsi = ((TextView) view.findViewById(R.id.provinsi)).getText().toString();
                String latitude = ((TextView) view.findViewById(R.id.latitude)).getText().toString();
                String longitude = ((TextView) view.findViewById(R.id.longitude)).getText().toString();
                String sms= ((TextView) view.findViewById(R.id.sms)).getText().toString();

                //varible string tadi kita simpan dalam suatu Bundle, dan kita parse bundle tersebut bersama intent menuju kelas ViewDataActivity
                //Intent i = null;
//                i = new Intent(ReadTokoActivity.this, ViewdataInActivity.class);
//                Bundle b = new Bundle();
//                //b.putString("id", id_m);
//                b.putString("tanggal", tanggal);
//                b.putString("jam", jam);
//                b.putString("latitude", latitude);
//                b.putString("longitude", longitude);
//                b.putString("kodetoko", kodetoko);
//                b.putString("kodestatus", kodestatus);
//                b.putString("kodeasm", kodeasm);
//                i.putExtras(b);
//                startActivity(i);
            }
        });
    }



    @SuppressLint("StaticFieldLeak")
    class ReadDataTask extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ReadTokoActivity.this);
            pDialog.setMessage("Mohon Tunggu..");
            pDialog.setIndeterminate(true);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... sText) {
            return getDataList();

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            if(result.equalsIgnoreCase("Exception Caught")){
                Toast.makeText(ReadTokoActivity.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }
            if(result.equalsIgnoreCase("no results")){
                Toast.makeText(ReadTokoActivity.this, "Data empty", Toast.LENGTH_LONG).show();
            }else {
                adapter = new TokoAdapter(ReadTokoActivity.this,daftar_toko);
                list.setAdapter(adapter);
                //Adapter menampilkan data mahasiswa ke dalam listView


            }
        }

        //method untuk memperoleh daftar mahasiswa dari JSON
        String getDataList(){
            Toko tempMarker = new Toko();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(Konfigurasi.URL_READ_TOKO,"POST", parameter);

                int success = json.getInt(Konfigurasi.TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of daftar_mhs
                    daftarToko = json.getJSONArray(Konfigurasi.TAG_TOKO);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarToko.length(); i++){

                        JSONObject c = daftarToko.getJSONObject(i);
                        tempMarker = new Toko();
                        tempMarker.setNo(c.getString(Konfigurasi.TAG_NO));
                        tempMarker.setStr_id(c.getString(Konfigurasi.TAG_STR_ID));
                        tempMarker.setNama(c.getString(Konfigurasi.TAG_NAMA));
                        tempMarker.setAlamat(c.getString(Konfigurasi.TAG_ALAMAT));
                        tempMarker.setKabupaten(c.getString(Konfigurasi.TAG_KABUPATEN));
                        tempMarker.setProvinsi(c.getString(Konfigurasi.TAG_PROVINSI));
                        tempMarker.setLatitude(c.getString(Konfigurasi.TAG_LATITUDE));
                        tempMarker.setLongitude(c.getString(Konfigurasi.TAG_LONGITUDE));
                        tempMarker.setSms(c.getString(Konfigurasi.TAG_SMS));
                        daftar_toko.add(tempMarker);
                    }
                    return "OK";
                }else{
                    //Tidak Ada Record Data (SUCCESS = 0)
                    return "no results";
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception Caught";
            }
        }
    }

}
