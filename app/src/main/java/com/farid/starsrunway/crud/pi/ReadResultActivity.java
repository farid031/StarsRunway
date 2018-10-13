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

public class ReadResultActivity extends AppCompatActivity {

    ListView list;

    JSONParser jParser = new JSONParser();
    ArrayList<Pi> daftar_result = new ArrayList<Pi>();
    JSONArray daftarResult = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_result);

        list = (ListView) findViewById(R.id.listview_result);

        //jalankan ReadDataTask
        ReadDataTask m = (ReadDataTask) new ReadDataTask().execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                //dapatkan data dari API dan simpan dalam variabel string
                String id_s = ((TextView) view.findViewById(R.id.result_id)).getText().toString();
                String tanggal = ((TextView) view.findViewById(R.id.result_tanggal)).getText().toString();
                String jam = ((TextView) view.findViewById(R.id.result_jam)).getText().toString();
                String latitude = ((TextView) view.findViewById(R.id.result_latitude)).getText().toString();
                String longitude = ((TextView) view.findViewById(R.id.result_longitude)).getText().toString();
                String kodetoko= ((TextView) view.findViewById(R.id.result_kd_toko)).getText().toString();
                String kodestatus = ((TextView) view.findViewById(R.id.result_status)).getText().toString();
                String ket = ((TextView) view.findViewById(R.id.result_ket)).getText().toString();

                //varible string tadi kita simpan dalam suatu Bundle, dan kita parse bundle tersebut bersama intent menuju kelas ViewDataActivity
                Intent i = null;
                i = new Intent(ReadResultActivity.this, ViewDataResultActivity.class);
                Bundle b = new Bundle();
                //b.putString("id", id_m);
                b.putString("tanggal", tanggal);
                b.putString("jam", jam);
                b.putString("latitude", latitude);
                b.putString("longitude", longitude);
                b.putString("kodetoko", kodetoko);
                b.putString("kodestatus", kodestatus);
                b.putString("ket", ket);
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
            pDialog = new ProgressDialog(ReadResultActivity.this);
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
                Toast.makeText(ReadResultActivity.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }
            if(result.equalsIgnoreCase("no results")){
                Toast.makeText(ReadResultActivity.this, "Data empty", Toast.LENGTH_LONG).show();
            }else {
                list.setAdapter(new ResultAdapter(ReadResultActivity.this, daftar_result)); //Adapter menampilkan data ke dalam listView
            }
        }

        //method untuk memperoleh daftar mahasiswa dari JSON
        String getDataList(){
            Pi tempResult = new Pi();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(Konfigurasi.URL_READ_RESULT,"POST", parameter);

                int success = json.getInt(Konfigurasi.TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of come sampling
                    daftarResult = json.getJSONArray(Konfigurasi.TAG_PI);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarResult.length() ; i++){
                        JSONObject c = daftarResult.getJSONObject(i);
                        tempResult = new Pi();
                        tempResult.setP_Id(c.getString(Konfigurasi.TAG_ID));
                        tempResult.setTanggal(c.getString(Konfigurasi.TAG_TANGGAL));
                        tempResult.setJam(c.getString(Konfigurasi.TAG_JAM));
                        tempResult.setLatitude(c.getString(Konfigurasi.TAG_LATITUDE));
                        tempResult.setLongitude(c.getString(Konfigurasi.TAG_LONGITUDE));
                        tempResult.setKd_Toko(c.getString(Konfigurasi.TAG_KD_TOKO));
                        tempResult.setKet(c.getString(Konfigurasi.TAG_KET));
                        tempResult.setStatus(c.getString(Konfigurasi.TAG_STATUS));
                        daftar_result.add(tempResult);
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
