package com.farid.starsrunway.crud.visit;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class ReadOutActivity extends AppCompatActivity {

    ListView list;

    JSONParser jParser = new JSONParser();
    ArrayList<Visit> daftar_visit = new ArrayList<Visit>();
    JSONArray daftarVsit = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_outvisit);

        list = (ListView) findViewById(R.id.listview_outvisit);

        //jalankan ReadDataTask
        ReadDataTask m= (ReadDataTask) new ReadDataTask().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                //dan simpan dalam variable string
                //String id_m = ((TextView) view.findViewById(R.id.id)).getText().toString();
                String tanggal = ((TextView) view.findViewById(R.id.tanggal)).getText().toString();
                String jam = ((TextView) view.findViewById(R.id.jam)).getText().toString();
                String latitude = ((TextView) view.findViewById(R.id.latitude)).getText().toString();
                String longitude = ((TextView) view.findViewById(R.id.longitude)).getText().toString();
                String kodetoko= ((TextView) view.findViewById(R.id.kd_toko)).getText().toString();
                String kodestatus = ((TextView) view.findViewById(R.id.status)).getText().toString();
                String keterangan = ((TextView) view.findViewById(R.id.ket)).getText().toString();
                String kodeasm= ((TextView) view.findViewById(R.id.kd_asm)).getText().toString();

                //varible string tadi kita simpan dalam suatu Bundle, dan kita parse bundle tersebut bersama intent menuju kelas ViewDataActivity
                Intent i = null;
                i = new Intent(ReadOutActivity.this, ViewDataOutActivity.class);
                Bundle b = new Bundle();
                //b.putString("id", id_m);
                b.putString("tanggal", tanggal);
                b.putString("jam", jam);
                b.putString("latitude", latitude);
                b.putString("longitude", longitude);
                b.putString("kodetoko", kodetoko);
                b.putString("kodestatus", kodestatus);
                b.putString("keterangan", keterangan);
                b.putString("kodeasm", kodeasm);
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
            pDialog = new ProgressDialog(ReadOutActivity.this);
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
                Toast.makeText(ReadOutActivity.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }
            if(result.equalsIgnoreCase("no results")){
                Toast.makeText(ReadOutActivity.this, "Data empty", Toast.LENGTH_LONG).show();
            }else {
                list.setAdapter(new VisitOutAdapter(ReadOutActivity.this,daftar_visit)); //Adapter menampilkan data mahasiswa ke dalam listView
            }
        }

        //method untuk memperoleh daftar mahasiswa dari JSON
        String getDataList(){
            Visit tempMarker = new Visit();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(Konfigurasi.URL_READ_OUTVISIT,"POST", parameter);

                int success = json.getInt(Konfigurasi.TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of daftar_mhs
                    daftarVsit = json.getJSONArray(Konfigurasi.TAG_VISIT);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarVsit.length() ; i++){
                        JSONObject c = daftarVsit.getJSONObject(i);
                        tempMarker = new Visit();
                        tempMarker.setV_Id(c.getString(Konfigurasi.TAG_ID));
                        tempMarker.setTanggal(c.getString(Konfigurasi.TAG_TANGGAL));
                        tempMarker.setJam(c.getString(Konfigurasi.TAG_JAM));
                        tempMarker.setLatitude(c.getString(Konfigurasi.TAG_LATITUDE));
                        tempMarker.setLongitude(c.getString(Konfigurasi.TAG_LONGITUDE));
                        tempMarker.setKd_Toko(c.getString(Konfigurasi.TAG_KD_TOKO));
                        tempMarker.setStatus(c.getString(Konfigurasi.TAG_STATUS));
                        tempMarker.setKet(c.getString(Konfigurasi.TAG_KET));
                        tempMarker.setKd_Asm(c.getString(Konfigurasi.TAG_KD_ASM));
                        daftar_visit.add(tempMarker);
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
