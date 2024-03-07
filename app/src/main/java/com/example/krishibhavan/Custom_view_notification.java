package com.example.krishibhavan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Custom_view_notification extends BaseAdapter {
    String[]nid,notification,date;
    private Context context;

    public Custom_view_notification(Context applicationContext, String[] nid, String[] notification, String[] date) {
        this.context = applicationContext;
        this.nid = nid;
        this.notification = notification;
        this.date = date;
    }

    @Override
    public int getCount() {
        return notification.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_notification,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView24);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView26);




        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);



        tv1.setText(notification[i]);
        tv2.setText(date[i]);





        return gridView;
    }
}