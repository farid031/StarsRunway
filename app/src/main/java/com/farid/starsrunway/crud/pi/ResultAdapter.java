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

public class ResultAdapter extends BaseAdapter{
    private Activity activity;
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<Pi> data_result = new ArrayList<Pi>();

    private static LayoutInflater inflater = null;

    public ResultAdapter(Activity a, ArrayList<Pi> d) {
        activity = a;
        data_result = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_result.size();
    }
    public Object getItem(int position) {
        return data_result.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.listview_result, null);
        TextView id = vi.findViewById(R.id.result_id);
        TextView tanggal = vi.findViewById(R.id.result_tanggal);
        TextView jam = vi.findViewById(R.id.result_jam);
        TextView latitude = vi.findViewById(R.id.result_latitude);
        TextView longitude = vi.findViewById(R.id.result_longitude);
        TextView kd_toko = vi.findViewById(R.id.result_kd_toko);
        TextView status = vi.findViewById(R.id.result_status);
        TextView ket = vi.findViewById(R.id.result_ket);

        Pi daftar_result = data_result.get(position);
        id.setText(daftar_result.getP_Id());
        tanggal.setText(daftar_result.getTanggal());
        jam.setText(daftar_result.getJam());
        latitude.setText(daftar_result.getLatitude());
        longitude.setText(daftar_result.getLongitude());
        kd_toko.setText(daftar_result.getKd_toko());
        status.setText(daftar_result.getStatus());
        ket.setText(daftar_result.getKet());
        return vi;
    }
}
