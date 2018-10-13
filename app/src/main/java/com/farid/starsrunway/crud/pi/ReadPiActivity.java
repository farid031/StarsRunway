package com.farid.starsrunway.crud.pi;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
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

public class ReadPiActivity extends AppCompatActivity {

    ListView list;

    JSONParser jParser = new JSONParser();
    ArrayList<Pi> daftar_pi = new ArrayList<Pi>();
    JSONArray daftarPi = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_pi);

        list = (ListView) findViewById(R.id.listview_pi);

        //jalankan ReadDataTask
        ReadDataTask m = (ReadDataTask) new ReadDataTask().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                //dapatkan data dari API dan simpan dalam variabel string
                String id_s = ((TextView) view.findViewById(R.id.pi_id)).getText().toString();
                String tanggal = ((TextView) view.findViewById(R.id.pi_tanggal)).getText().toString();
                String jam = ((TextView) view.findViewById(R.id.pi_jam)).getText().toString();
                String latitude = ((TextView) view.findViewById(R.id.pi_latitude)).getText().toString();
                String longitude = ((TextView) view.findViewById(R.id.pi_longitude)).getText().toString();
                String kodetoko= ((TextView) view.findViewById(R.id.pi_kd_toko)).getText().toString();
                String kodestatus = ((TextView) view.findViewById(R.id.pi_status)).getText().toString();

                //varible string tadi kita simpan dalam suatu Bundle, dan kita parse bundle tersebut bersama intent menuju kelas ViewDataActivity
                Intent i = null;
                i = new Intent(ReadPiActivity.this, ViewDataPiActivity.class);
                Bundle b = new Bundle();
                //b.putString("id", id_m);
                b.putString("tanggal", tanggal);
                b.putString("jam", jam);
                b.putString("latitude", latitude);
                b.putString("longitude", longitude);
                b.putString("kodetoko", kodetoko);
                b.putString("kodestatus", kodestatus);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    class ReadDataTask extends AsyncTask<String, String, String> {
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ReadPiActivity.this);
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
                Toast.makeText(ReadPiActivity.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }
            if(result.equalsIgnoreCase("no results")){
                Toast.makeText(ReadPiActivity.this, "Data empty", Toast.LENGTH_LONG).show();
            }else {
                list.setAdapter(new PiAdapter(ReadPiActivity.this, daftar_pi)); //Adapter menampilkan data ke dalam listView
            }
        }

        //method untuk memperoleh daftar mahasiswa dari JSON
        String getDataList(){
            Pi tempPi = new Pi();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(Konfigurasi.URL_READ_PI,"POST", parameter);

                int success = json.getInt(Konfigurasi.TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of come sampling
                    daftarPi = json.getJSONArray(Konfigurasi.TAG_PI);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarPi.length() ; i++){
                        JSONObject c = daftarPi.getJSONObject(i);
                        tempPi = new Pi();
                        tempPi.setP_Id(c.getString(Konfigurasi.TAG_ID));
                        tempPi.setTanggal(c.getString(Konfigurasi.TAG_TANGGAL));
                        tempPi.setJam(c.getString(Konfigurasi.TAG_JAM));
                        tempPi.setLatitude(c.getString(Konfigurasi.TAG_LATITUDE));
                        tempPi.setLongitude(c.getString(Konfigurasi.TAG_LONGITUDE));
                        tempPi.setKd_Toko(c.getString(Konfigurasi.TAG_KD_TOKO));
                        tempPi.setStatus(c.getString(Konfigurasi.TAG_STATUS));
                        daftar_pi.add(tempPi);
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
