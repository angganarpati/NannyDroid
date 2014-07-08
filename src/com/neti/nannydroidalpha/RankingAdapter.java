package com.neti.nannydroidalpha;


import java.util.List;

import com.neti.model.Ranking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class RankingAdapter extends ArrayAdapter<Ranking>{
    private List<Ranking> items;
    
    public RankingAdapter(Context context, List<Ranking> items) {
        super(context, R.layout.rank_custom_list, items);
        this.items = items;
    }
    
    @Override
    public int getCount() {
        return items.size();
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        
        if(v == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.rank_custom_list, null);            
        }
        
        Ranking rank = items.get(position);
        
        if(rank != null) {
            TextView nama = (TextView)v.findViewById(R.id.txtNama);
            TextView ktp = (TextView)v.findViewById(R.id.txtKtp);
            TextView status = (TextView)v.findViewById(R.id.txtStatus);
            TextView total = (TextView)v.findViewById(R.id.txtTotal);
           
            
            
            
            if(nama != null) nama.setText((position+1)+". "+rank.getNama());
            if(ktp != null) ktp.setText(rank.getKtp());
            if(status != null) status.setText(rank.getStatus());
            if(total != null) total.setText("Nilai Total :"+(rank.getTotal()));            
            
        }
        
        return v;
    }
}
