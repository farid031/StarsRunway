package com.farid.starsrunway.report.best_seller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.farid.starsrunway.R;

import java.util.ArrayList;

public class BestSellerAdapter extends BaseAdapter {
    private Activity activity;
    //private ArrayList<HashMap<String, String>> data;
    private ArrayList<BestSeller> data_bestseller = new ArrayList<BestSeller>();

    private static LayoutInflater inflater = null;

    public BestSellerAdapter(Activity a, ArrayList<BestSeller> d) {
        activity = a;
        data_bestseller = d;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return data_bestseller.size();
    }
    public Object getItem(int position) {
        return data_bestseller.get(position);
    }
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.listview_bestseller, null);
        TextView no = vi.findViewById(R.id.bestseller_no);
        TextView artikel = vi.findViewById(R.id.bestseller_artikel);
        TextView prj = vi.findViewById(R.id.bestseller_prj);
        TextView retprc = vi.findViewById(R.id.bestseller_retprc);
        TextView sls = vi.findViewById(R.id.bestseller_sls);
        TextView stdt = vi.findViewById(R.id.bestseller_stdt);
        TextView stok = vi.findViewById(R.id.bestseller_stok);
        TextView gr = vi.findViewById(R.id.bestseller_gr);
        TextView mrg = vi.findViewById(R.id.bestseller_mrg);
        TextView dis = vi.findViewById(R.id.bestseller_dis);
        TextView tg1 = vi.findViewById(R.id.bestseller_tg1);
        TextView tg2 = vi.findViewById(R.id.bestseller_tg2);

        BestSeller daftar_best = data_bestseller.get(position);
        no.setText(daftar_best.getNo());
        artikel.setText(daftar_best.getArtikel());
        prj.setText(daftar_best.getPrj());
        retprc.setText(daftar_best.getRetprc());
        sls.setText(daftar_best.getSls());
        stdt.setText(daftar_best.getStdt());
        stok.setText(daftar_best.getStok());
        gr.setText(daftar_best.getGr());
        mrg.setText(daftar_best.getMrg());
        dis.setText(daftar_best.getDis());
        tg1.setText(daftar_best.getTg1());
        tg2.setText(daftar_best.getTg2());

        return vi;
    }
}