package com.farid.starsrunway.report.hitlist;

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

public class ReadHitlistReportActivity extends AppCompatActivity {

    ListView list;

    JSONParser jParser = new JSONParser();
    ArrayList<HitList> daftar_hitlist = new ArrayList<HitList>();
    JSONArray daftarHitlist = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_report_hitlist);

        list = (ListView) findViewById(R.id.listview_hitlist);

        //jalankan ReadDataTask
        ReadDataTask m =  new ReadDataTask();
        m.execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                //dapatkan data dari API dan simpan dalam variabel string
                String kodeasm= ((TextView) view.findViewById(R.id.hitlist_kd_asm)).getText().toString();
                String namaasm = ((TextView) view.findViewById(R.id.hitlist_nama_asm)).getText().toString();
                String tgl = ((TextView) view.findViewById(R.id.hitlist_tgl)).getText().toString();
                String kodetoko= ((TextView) view.findViewById(R.id.hitlist_kd_toko)).getText().toString();
                String namatoko = ((TextView) view.findViewById(R.id.hitlist_nama_toko)).getText().toString();
                String kodeaksi= ((TextView) view.findViewById(R.id.hitlist_kd_aksi)).getText().toString();
                String verifikasi = ((TextView) view.findViewById(R.id.hitlist_verifikasi)).getText().toString();
                String hasilaksi= ((TextView) view.findViewById(R.id.hitlist_hasil_aksi)).getText().toString();

                //varible string tadi kita simpan dalam suatu Bundle, dan kita parse bundle tersebut bersama intent menuju kelas ViewDataActivity
//                Intent i = null;
//                i = new Intent(ReadHitlistReportActivity.this, ViewDataComeSamplingActivity.class);
//                Bundle b = new Bundle();
//                //b.putString("id", id_m);
//                b.putString("kodeasm", kodeasm);
//                b.putString("namaasm", namaasm);
//                b.putString("tgl", tgl);
//                b.putString("kodetoko", kodetoko);
//                b.putString("namatoko", namatoko);
//                b.putString("kodeaksi", kodeaksi);
//                b.putString("verifikasi", verifikasi);
//                b.putString("hasilaksi", hasilaksi);
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
            pDialog = new ProgressDialog(ReadHitlistReportActivity.this);
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
                Toast.makeText(ReadHitlistReportActivity.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }
            if(result.equalsIgnoreCase("no results")){
                Toast.makeText(ReadHitlistReportActivity.this, "Data empty", Toast.LENGTH_LONG).show();
            }else {
                list.setAdapter(new HitlistAdapter(ReadHitlistReportActivity.this,daftar_hitlist)); //Adapter menampilkan data ke dalam listView
            }
        }

        //method untuk memperoleh daftar mahasiswa dari JSON
        String getDataList(){
            HitList tempSampling = new HitList();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(Konfigurasi.URL_READ_HITLIST,"POST", parameter);

                int success = json.getInt(Konfigurasi.TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of come sampling
                    daftarHitlist = json.getJSONArray(Konfigurasi.TAG_HITLIST);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarHitlist.length() ; i++){
                        JSONObject c = daftarHitlist.getJSONObject(i);
                        tempSampling = new HitList();
                        tempSampling.setKd_Asm(c.getString(Konfigurasi.TAG_KD_ASM));
                        tempSampling.setNama_Asm(c.getString(Konfigurasi.TAG_NAMA_ASM));
                        tempSampling.setTgl(c.getString(Konfigurasi.TAG_TANGGAL));
                        tempSampling.setKd_Toko(c.getString(Konfigurasi.TAG_KD_TOKO));
                        tempSampling.setNama_toko(c.getString(Konfigurasi.TAG_NAMA_TOKO));
                        tempSampling.setKd_Aksi(c.getString(Konfigurasi.TAG_KD_AKSI));
                        tempSampling.setVerifikasi(c.getString(Konfigurasi.TAG_VERIFIKASI));
                        tempSampling.setHasilAksi(c.getString(Konfigurasi.TAG_HASIL_AKSI));
                        daftar_hitlist.add(tempSampling);
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
