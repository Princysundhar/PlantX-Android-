package com.example.krishibhavan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class Custom_view_stock extends BaseAdapter {
    String[]stk_id,name,type,price,quantity,image,officer_info;
    private Context context;


    public Custom_view_stock(Context applicationContext, String[] stk_id, String[] name, String[] type, String[] price, String[] quantity, String[] image, String[] officer_info) {
        this.context = applicationContext;
        this.stk_id = stk_id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
        this.officer_info = officer_info;
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
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            //gridView=inflator.inflate(R.layout.customview, null);
            gridView=inflator.inflate(R.layout.activity_custom_view_stock,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView47);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView49);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView51);
        TextView tv4=(TextView)gridView.findViewById(R.id.textView53);
        TextView tv5=(TextView)gridView.findViewById(R.id.textView55);
        ImageView im = (ImageView)gridView.findViewById(R.id.imageView2);


        Button b1 = (Button)gridView.findViewById(R.id.button9);
        if (quantity[i].equalsIgnoreCase(String.valueOf(0))){
            b1.setEnabled(false);
        }
        else {
            b1.setEnabled(true);
        }
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int pos = (int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("stk_id",stk_id[i]);
                ed.commit();
                Intent i = new Intent(context,add_quantity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });




        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);



        tv1.setText(name[i]);
        tv2.setText(type[i]);
        tv3.setText(price[i]);
        tv4.setText(quantity[i]);
        tv5.setText(officer_info[i]);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ipaddress","");

        String url="http://" + ip + ":8000"+image[i];


        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);





        return gridView;
    }
}