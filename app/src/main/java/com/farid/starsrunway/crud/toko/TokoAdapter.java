package com.farid.starsrunway.crud.toko;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.farid.starsrunway.R;

import java.util.ArrayList;

public class TokoAdapter extends BaseAdapter {
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<Toko> data_toko = new ArrayList<Toko>();

    private static LayoutInflater inflater = null;

    public TokoAdapter(Activity a, ArrayList<Toko> d) {
        data_toko = d;
        inflater = (LayoutInflater) a.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_toko.size();
    }
    public Object getItem(int position) {
        return data_toko.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null) {
            vi = inflater.inflate(R.layout.listview_toko, null);
        }
        TextView no = vi.findViewById(R.id.no);
        TextView str_id = vi.findViewById(R.id.str_id);
        TextView nama = vi.findViewById(R.id.nama);
        TextView alamat = vi.findViewById(R.id.alamat);
        TextView kabupaten = vi.findViewById(R.id.kabupaten);
        TextView provinsi = vi.findViewById(R.id.provinsi);
        TextView latitude = vi.findViewById(R.id.latitude);
        TextView longitude = vi.findViewById(R.id.longitude);
        TextView sms = vi.findViewById(R.id.sms);


        Toko daftar_toko = data_toko.get(position);
        no.setText(daftar_toko.getNo());
        str_id.setText(daftar_toko.getStr_id());
        nama.setText(daftar_toko.getNama());
        alamat.setText(daftar_toko.getAlamat());
        kabupaten.setText(daftar_toko.getKabupaten());
        provinsi.setText(daftar_toko.getProvinsi());
        latitude.setText(daftar_toko.getLatitude());
        longitude.setText(daftar_toko.getLongitude());
        sms.setText(daftar_toko.getSms());
        return vi;
    }
}
