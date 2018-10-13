package com.farid.starsrunway.login;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.farid.starsrunway.MainmenuActivity;
import com.farid.starsrunway.R;
import com.farid.starsrunway.helper.JSONParser;
import com.farid.starsrunway.helper.Konfigurasi;

public class LoginActivity extends AppCompatActivity  {

    Button ButtonDaftar, ButtonLogin, ButtonCancel;
    EditText EditAsm, EditPass;
    String url, success;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new SessionManager(getApplicationContext());
        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();

        ButtonDaftar = findViewById(R.id.buttonDaftar);
        ButtonLogin = findViewById(R.id.buttonLogin);
        ButtonCancel = findViewById(R.id.buttonCancel);
        EditAsm = findViewById(R.id.editAsm);
        EditPass = findViewById(R.id.editPass);

        ButtonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        ButtonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String asm = EditAsm.getText().toString().trim();
                String password = EditPass.getText().toString().trim();
                url = Konfigurasi.URL_LOGIN + "asm=" + asm + "&password=" + password;

                if (asm.length() > 0 && password.length() > 0) {
                    new Masuk().execute();
                } else {
                    Toast.makeText(getApplicationContext(), "Username/password masih kosong gan.!!", Toast.LENGTH_LONG).show();
                }
            }
        });

        ButtonCancel.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    @SuppressLint("StaticFieldLeak")
    public class Masuk extends AsyncTask<String, String, String>
    {
        ArrayList<HashMap<String, String>> contactList = new ArrayList<HashMap<String, String>>();
        ProgressDialog pDialog;

        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

            pDialog = new ProgressDialog(LoginActivity.this);
            pDialog.setMessage("Tunggu Bentar ya...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }
        @Override
        protected String doInBackground(String... arg0) {
            JSONParser jParser = new JSONParser();
            JSONObject json = jParser.getJSONFromUrl(url);
            try {
                success = json.getString("success");
                Log.e("error", "nilai sukses=" + success);
                JSONArray hasil = json.getJSONArray("login");
                if (success.equals("y")) {
                    for (int i = 0; i < hasil.length(); i++) {
                        JSONObject c = hasil.getJSONObject(i);
                        String asm = c.getString("asm").trim();
                        String nama = c.getString("namaasm").trim();
                        session.createLoginSession(asm, nama);
                        Log.e("ok", " ambil data");
                    }
                } else {
                    Log.e("error", "tidak bisa ambil data 0");
                }
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("error", "tidak bisa ambil data 1");
            }
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            pDialog.dismiss();
            if (success.equals("y")) {
                startActivity(new Intent(LoginActivity.this, MainmenuActivity.class));
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Username/password salah gan.!!", Toast.LENGTH_LONG).show();
            }
        }
    }
}