package com.farid.starsrunway.crud.sampling;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.farid.starsrunway.R;

import java.util.ArrayList;

public class ComeSamplingAdapter extends BaseAdapter{
    private Activity activity;
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<Sampling> data_sampling = new ArrayList<Sampling>();

    private static LayoutInflater inflater = null;

    public ComeSamplingAdapter(Activity a, ArrayList<Sampling> d) {
        activity = a;
        data_sampling = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_sampling.size();
    }
    public Object getItem(int position) {
        return data_sampling.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.listview_comesampling, null);
        TextView id = vi.findViewById(R.id.come_id);
        TextView tanggal = vi.findViewById(R.id.come_tanggal);
        TextView jam = vi.findViewById(R.id.come_jam);
        TextView latitude = vi.findViewById(R.id.come_latitude);
        TextView longitude = vi.findViewById(R.id.come_longitude);
        TextView kd_toko = vi.findViewById(R.id.come_kd_toko);
        TextView status = vi.findViewById(R.id.come_status);
        TextView kd_asm = vi.findViewById(R.id.come_kd_asm);

        Sampling daftar_sampling = data_sampling.get(position);
        id.setText(daftar_sampling.getS_Id());
        tanggal.setText(daftar_sampling.getTanggal());
        jam.setText(daftar_sampling.getJam());
        latitude.setText(daftar_sampling.getLatitude());
        longitude.setText(daftar_sampling.getLongitude());
        kd_toko.setText(daftar_sampling.getKd_toko());
        status.setText(daftar_sampling.getStatus());
        kd_asm.setText(daftar_sampling.getKd_Asm());

        return vi;
    }
}
