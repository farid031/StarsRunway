package com.farid.starsrunway.report.rangking_toko;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.farid.starsrunway.R;

import java.util.ArrayList;

public class RangkingTokoAdapter {
    private Activity activity;
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<RangkingToko> data_rangkingtoko = new ArrayList<RangkingToko>();

    private static LayoutInflater inflater = null;

    public RangkingTokoAdapter (Activity a, ArrayList<RangkingToko> d) {
        activity = a;
        data_rangkingtoko = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_hitlist.size();
    }
    public Object getItem(int position) {
        return data_hitlist.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.listview_hitlist, null);
        TextView kd_asm = vi.findViewById(R.id.hitlist_kd_asm);
        TextView nama_asm = vi.findViewById(R.id.hitlist_nama_asm);
        TextView tgl = vi.findViewById(R.id.hitlist_tgl);
        TextView kd_toko = vi.findViewById(R.id.hitlist_kd_toko);
        TextView nama_toko = vi.findViewById(R.id.hitlist_nama_toko);
        TextView kd_aksi = vi.findViewById(R.id.hitlist_kd_aksi);
        TextView verifikasi = vi.findViewById(R.id.hitlist_verifikasi);
        TextView hasil_aksi = vi.findViewById(R.id.hitlist_hasil_aksi);

        HitList daftar_hit = data_hitlist.get(position);
        kd_asm.setText(daftar_hit.getKd_Asm());
        nama_asm.setText(daftar_hit.getNama_Asm());
        tgl.setText(daftar_hit.getTgl());
        kd_toko.setText(daftar_hit.getKd_Toko());
        nama_toko.setText(daftar_hit.getNamaToko());
        kd_aksi.setText(daftar_hit.getKd_Aksi());
        verifikasi.setText(daftar_hit.getVerifikasi());
        hasil_aksi.setText(daftar_hit.getHasilAksi());

        return vi;
    }
}
