package com.example.krishibhavan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Custom_view_orders extends BaseAdapter {
    String[]order_id,stock_name,stock_image,amount,date,officer_info,quantity;
    private Context context;


    public Custom_view_orders(Context applicationContext, String[] order_id, String[] stock_name, String[] stock_image, String[] amount, String[] date, String[] officer_info, String[] quantity) {
        this.context = applicationContext;
        this.order_id = order_id;
        this.stock_name = stock_name;
        this.stock_image = stock_image;
        this.amount = amount;
        this.date = date;
        this.officer_info = officer_info;
        this.quantity = quantity;

    }

    @Override
    public int getCount() {
        return quantity.length;
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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_view_orders,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView72);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView74);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView76);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView78);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView80);
        ImageView im=(ImageView) gridView.findViewById(R.id.imageView4);

        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);


        tv1.setText(stock_name[i]);
        tv2.setText(amount[i]);
        tv3.setText(date[i]);
        tv4.setText(officer_info[i]);
        tv5.setText(quantity[i]);


        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ipaddress","");

        String url="http://" + ip + ":8000"+stock_image[i];


        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);

        return gridView;
    }
}