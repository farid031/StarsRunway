package com.farid.starsrunway.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.farid.starsrunway.helper.Konfigurasi;
import com.farid.starsrunway.R;
import com.farid.starsrunway.helper.RequestHandler;

import java.util.HashMap;

public class RegisterActivity extends Activity implements View.OnClickListener {

    ProgressDialog pDialog;

    EditText EditType, EditUser, EditPassword, EditRePassword;
    Button ButtonDaftar;
    String type, user, pass, repass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditType        = findViewById(R.id.editType);
        EditUser        = findViewById(R.id.editUsername);
        EditPassword    = findViewById(R.id.editPassword);
        EditRePassword  = findViewById(R.id.editRePassword);
        ButtonDaftar    = findViewById(R.id.buttonReg);

        ButtonDaftar.setOnClickListener(this);
    }

    public void Register(){
        type = EditType.getText().toString().trim();
        user = EditUser.getText().toString().trim();
        pass = EditPassword.getText().toString().trim();
        repass = EditRePassword.getText().toString().trim();

        @SuppressLint("StaticFieldLeak")
        class Create extends AsyncTask<String, String, String> {
            private ProgressDialog loading;

            @Override
            protected String doInBackground(String... strings) {
                HashMap<String, String> params = new HashMap<>();
                params.put(Konfigurasi.KEY_TYPE, type);
                params.put(Konfigurasi.KEY_USER, user);
                params.put(Konfigurasi.KEY_PASS, pass);

                RequestHandler rh = new RequestHandler();
                return rh.sendPostRequest(Konfigurasi.URL_REGISTER, params);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RegisterActivity.this, "Menambahkan...", "Tunggu...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(RegisterActivity.this, s, Toast.LENGTH_LONG).show();
            }
        }

        boolean isEmptyFields = false;
        if (TextUtils.isEmpty(type)) {
            isEmptyFields = true;
            EditType.setError("Nama tidak boleh kosong");
        }
        if (TextUtils.isEmpty(user)) {
            isEmptyFields = true;
            EditUser.setError("Username tidak boleh kosong");
        }
        if (TextUtils.isEmpty(pass)) {
            isEmptyFields = true;
            //textLatitude.setError("Tunggu Latitude sampai muncul");
            EditPassword.setError("Password tidak boleh kosong");
        }
        if (TextUtils.isEmpty(repass)) {
            isEmptyFields = true;
            //textLatitude.setError("Tunggu Latitude sampai muncul");
            EditRePassword.setError("Re-Enter Password tidak boleh kosong");
        }
        if (!isEmptyFields) {
            if (pass.equals(repass)){
                new Create().execute();
            }else{
                Toast.makeText(RegisterActivity.this, "Password tidak cocok", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == ButtonDaftar){
            Register();
        }
    }
}
