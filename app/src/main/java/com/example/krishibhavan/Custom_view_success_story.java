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

public class Custom_view_success_story extends BaseAdapter {
    String[]sid,story,date,agriculture_info;
    private Context context;


    public Custom_view_success_story(Context applicationContext, String[] sid, String[] story, String[] date,String[] agriculture_info) {
        this.context = applicationContext;
        this.sid = sid;
        this.story = story;
        this.date = date;
        this.agriculture_info = agriculture_info;
    }

    @Override
    public int getCount() {
        return story.length;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_success_story,null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.textView24);
        TextView tv2=(TextView)gridView.findViewById(R.id.textView26);
        TextView tv3=(TextView)gridView.findViewById(R.id.textView45);




        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);



        tv1.setText(story[i]);
        tv2.setText(date[i]);
        tv3.setText(agriculture_info[i]);





        return gridView;
    }
}