package com.example.krishibhavan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom_view_fertilizer extends BaseAdapter {
    String[] fid, name, price, details;
    private Context context;


    public Custom_view_fertilizer(Context applicationContext, String[] fid, String[] name, String[] price, String[] details) {
        this.context = applicationContext;
        this.fid = fid;
        this.name = name;
        this.price = price;
        this.details = details;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if (view == null) {
            gridView = new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView = inflator.inflate(R.layout.activity_custom_view_fertilizer, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView28);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView30);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView32);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv1.setText(name[i]);
        tv2.setText(price[i]);
        tv3.setText(details[i]);


        return gridView;
    }
}