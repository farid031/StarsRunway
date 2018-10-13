package com.farid.starsrunway.crud.visit;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.farid.starsrunway.R;

import java.util.ArrayList;

public class OutVisitAdapter {
    private Activity activity;
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<Visit> data_visit = new ArrayList<Visit>();

    private static LayoutInflater inflater = null;

    public OutVisitAdapter(Activity a, ArrayList<Visit> d) {
        activity = a;
        data_visit = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_visit.size();
    }
    public Object getItem(int position) {
        return data_visit.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.listview_outvisit, null);
        TextView id_mhs = vi.findViewById(R.id.id);
        TextView tanggal = vi.findViewById(R.id.tanggal);
        TextView jam = vi.findViewById(R.id.jam);
        TextView latitude = vi.findViewById(R.id.latitude);
        TextView longitude = vi.findViewById(R.id.longitude);
        TextView kd_toko = vi.findViewById(R.id.kd_toko);
        TextView status = vi.findViewById(R.id.status);
        TextView ket = vi.findViewById(R.id.ket);
        TextView kd_asm = vi.findViewById(R.id.kd_asm);

        Visit daftar_vst = data_visit.get(position);
        id_mhs.setText(daftar_vst.getV_Id());
        tanggal.setText(daftar_vst.getTanggal());
        jam.setText(daftar_vst.getJam());
        latitude.setText(daftar_vst.getLatitude());
        longitude.setText(daftar_vst.getLongitude());
        kd_toko.setText(daftar_vst.getKd_toko());
        status.setText(daftar_vst.getStatus());
        ket.setText(daftar_vst.getKet());
        kd_asm.setText(daftar_vst.getKd_Asm());

        return vi;
    }
}
