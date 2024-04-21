package com.example.eshop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eshop.model.Goods;

import java.util.ArrayList;

public class GoodsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Goods> goodsList;

    public GoodsAdapter(Context context, ArrayList<Goods> goodsList) {
        this.context = context;
        this.goodsList = goodsList;
    }

    @Override
    public int getCount() {
        return goodsList.size();
    }

    @Override
    public Object getItem(int position) {
        return goodsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.iv);
            holder.nameTextView = convertView.findViewById(R.id.tv1);
            holder.priceTextView = convertView.findViewById(R.id.tv2);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Goods currentGoods = goodsList.get(position);

        holder.imageView.setImageResource(currentGoods.getImage());
        holder.nameTextView.setText(currentGoods.getName());
        holder.priceTextView.setText(String.valueOf(currentGoods.getPrice()));

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView priceTextView;
    }
}

