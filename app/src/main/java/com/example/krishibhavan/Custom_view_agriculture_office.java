package com.example.krishibhavan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Custom_view_agriculture_office extends BaseAdapter {
    String[]aid,name,email,contact,latitude,longitude;
    private Context context;
    SharedPreferences sh;


    public Custom_view_agriculture_office(Context applicationContext, String[] aid, String[] name, String[] email, String[] contact, String[] latitude, String[] longitude) {
        this.context = applicationContext;
        this.aid = aid;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.latitude = latitude;
        this.longitude = longitude;

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
            gridView=inflator.inflate(R.layout.activity_custom_view_agriculture_office,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView18);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView20);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView22);
        Button b1 = (Button)gridView.findViewById(R.id.button4);
        Button b2 = (Button)gridView.findViewById(R.id.button5);
        Button b3 = (Button)gridView.findViewById(R.id.button7);
        Button b4 = (Button)gridView.findViewById(R.id.button8);
        Button b5 = (Button)gridView.findViewById(R.id.button12);
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int ik=(int)view.getTag();
                String url = "http://maps.google.com/?q=" + latitude[ik] + "," + longitude[ik];
                Intent i = new Intent(android.content.Intent.ACTION_VIEW,  Uri.parse(url));
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        b2.setTag(i);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int pos = (int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("aid",aid[i]);
                ed.commit();
                Intent i = new Intent(context,view_fertilizer.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });
        b3.setTag(i);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int pos = (int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("aid",aid[i]);
                ed.commit();
                Intent i = new Intent(context,view_success_story.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });
        b4.setTag(i);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int pos = (int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("aid",aid[i]);
                ed.commit();
                Intent i = new Intent(context,Chat.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });
        b5.setTag(i);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int pos = (int)view.getTag();
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
                SharedPreferences.Editor ed = sh.edit();
                ed.putString("aid",aid[i]);
                ed.commit();
                Intent i = new Intent(context,view_stock.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);


        tv1.setText(name[i]);
        tv2.setText(email[i]);
        tv3.setText(contact[i]);




        return gridView;
    }
}