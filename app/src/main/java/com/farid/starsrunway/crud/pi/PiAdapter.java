package com.farid.starsrunway.crud.pi;

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

public class PiAdapter extends BaseAdapter {
    private Activity activity;
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<Pi> data_pi = new ArrayList<Pi>();

    private static LayoutInflater inflater = null;

    public PiAdapter(Activity a, ArrayList<Pi> d) {
        activity = a;
        data_pi = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_pi.size();
    }
    public Object getItem(int position) {
        return data_pi.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.listview_pi, null);
        TextView id = vi.findViewById(R.id.pi_id);
        TextView tanggal = vi.findViewById(R.id.pi_tanggal);
        TextView jam = vi.findViewById(R.id.pi_jam);
        TextView latitude = vi.findViewById(R.id.pi_latitude);
        TextView longitude = vi.findViewById(R.id.pi_longitude);
        TextView kd_toko = vi.findViewById(R.id.pi_kd_toko);
        TextView status = vi.findViewById(R.id.pi_status);

        Pi daftar_pi = data_pi.get(position);
        id.setText(daftar_pi.getP_Id());
        tanggal.setText(daftar_pi.getTanggal());
        jam.setText(daftar_pi.getJam());
        latitude.setText(daftar_pi.getLatitude());
        longitude.setText(daftar_pi.getLongitude());
        kd_toko.setText(daftar_pi.getKd_toko());
        status.setText(daftar_pi.getStatus());
        return vi;
    }
}
