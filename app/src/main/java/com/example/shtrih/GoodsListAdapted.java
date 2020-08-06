package com.example.shtrih;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shtrih.Good;
import com.example.shtrih.Kassa;
import com.example.shtrih.R;

import java.util.List;

final public class GoodsListAdapted extends BaseAdapter {
    private List<Good> goodData;
    private LayoutInflater layoutInflater;
    private Context context;
    private int layout_type;


    public GoodsListAdapted(Context aContext, List<Good> goodData, int layout_type) {
        this.context = aContext;
        this.goodData = goodData;
        this.layout_type = layout_type;
        layoutInflater = LayoutInflater.from(aContext);
    }


    @Override
    public int getCount() {
        return goodData.size();
    }

    @Override
    public Object getItem(int position) {
        return goodData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(layout_type,parent,false);

            holder = new ViewHolder();
            holder.name_place = (TextView) convertView.findViewById(R.id.good_name_place);
            holder.price_place = (TextView) convertView.findViewById(R.id.good_price_place);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Good good = this.goodData.get(position);
        holder.name_place.setText(good.name);
        holder.price_place.setText(String.valueOf(good.price/100)+" Р");
        return convertView;
    }


    static class ViewHolder {
        TextView price_place;
        TextView name_place;
    }
}
