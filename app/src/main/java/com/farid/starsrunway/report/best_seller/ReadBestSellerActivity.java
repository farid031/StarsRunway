package com.farid.starsrunway.report.best_seller;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
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

public class ReadBestSellerActivity extends AppCompatActivity {

    ListView list;

    JSONParser jParser = new JSONParser();
    ArrayList<BestSeller> daftar_bestseller = new ArrayList<BestSeller>();
    JSONArray daftarBestseller = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_report_best_seller);

        list = (ListView) findViewById(R.id.listview_bestseller);

        //jalankan ReadDataTask
        ReadDataTask m =  new ReadDataTask();
        m.execute();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int urutan, long id) {
                //dapatkan data dari API dan simpan dalam variabel string
                String no= ((TextView) view.findViewById(R.id.bestseller_no)).getText().toString();
                String artikel = ((TextView) view.findViewById(R.id.bestseller_artikel)).getText().toString();
                String prj = ((TextView) view.findViewById(R.id.bestseller_prj)).getText().toString();
                String retprc= ((TextView) view.findViewById(R.id.bestseller_retprc)).getText().toString();
                String sls = ((TextView) view.findViewById(R.id.bestseller_sls)).getText().toString();
                String stdt= ((TextView) view.findViewById(R.id.bestseller_stdt)).getText().toString();
                String stok = ((TextView) view.findViewById(R.id.bestseller_stok)).getText().toString();
                String gr= ((TextView) view.findViewById(R.id.bestseller_gr)).getText().toString();
                String mrg = ((TextView) view.findViewById(R.id.bestseller_mrg)).getText().toString();
                String dis= ((TextView) view.findViewById(R.id.bestseller_dis)).getText().toString();
                String tg1 = ((TextView) view.findViewById(R.id.bestseller_tg1)).getText().toString();
                String tg2= ((TextView) view.findViewById(R.id.bestseller_tg2)).getText().toString();

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
            pDialog = new ProgressDialog(ReadBestSellerActivity.this);
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
                Toast.makeText(ReadBestSellerActivity.this, "Unable to connect to server,please check your internet connection!", Toast.LENGTH_LONG).show();
            }
            if(result.equalsIgnoreCase("no results")){
                Toast.makeText(ReadBestSellerActivity.this, "Data empty", Toast.LENGTH_LONG).show();
            }else {
                list.setAdapter(new BestSellerAdapter(ReadBestSellerActivity.this,daftar_bestseller)); //Adapter menampilkan data ke dalam listView
            }
        }

        //method untuk memperoleh daftar mahasiswa dari JSON
        String getDataList(){
            BestSeller tempBest = new BestSeller();
            List<NameValuePair> parameter = new ArrayList<NameValuePair>();
            try {
                JSONObject json = jParser.makeHttpRequest(Konfigurasi.URL_READ_BESTSELLER,"POST", parameter);

                int success = json.getInt(Konfigurasi.TAG_SUCCESS);
                if (success == 1) { //Ada record Data (SUCCESS = 1)
                    //Getting Array of come sampling
                    daftarBestseller = json.getJSONArray(Konfigurasi.TAG_BESTSELLER);
                    // looping through All daftar_mhs
                    for (int i = 0; i < daftarBestseller.length() ; i++){
                        JSONObject c = daftarBestseller.getJSONObject(i);
                        tempBest = new BestSeller();
                        tempBest.setNo(c.getString(Konfigurasi.TAG_NO));
                        tempBest.setArtikel(c.getString(Konfigurasi.TAG_ARTIKEL));
                        tempBest.setPrj(c.getString(Konfigurasi.TAG_PRJ));
                        tempBest.setRetprc(c.getString(Konfigurasi.TAG_RETPRC));
                        tempBest.setSls(c.getString(Konfigurasi.TAG_SLS));
                        tempBest.setStdt(c.getString(Konfigurasi.TAG_STDT));
                        tempBest.setStok(c.getString(Konfigurasi.TAG_STOK));
                        tempBest.setGr(c.getString(Konfigurasi.TAG_GR));
                        tempBest.setMrg(c.getString(Konfigurasi.TAG_MRG));
                        tempBest.setDis(c.getString(Konfigurasi.TAG_DIS));
                        tempBest.setTg1(c.getString(Konfigurasi.TAG_TG1));
                        tempBest.setTg2(c.getString(Konfigurasi.TAG_TG2));
                        daftar_bestseller.add(tempBest);
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

