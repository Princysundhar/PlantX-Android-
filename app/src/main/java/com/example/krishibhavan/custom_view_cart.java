package com.example.krishibhavan;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class custom_view_cart extends BaseAdapter {
    String[] cart_id, stock_name, type, price, image, userinfo, officer_info, quantity;
    private Context context;
    String url;


    public custom_view_cart(Context applicationContext, String[] cart_id, String[] stock_name, String[] type, String[] price, String[] image, String[] userinfo, String[] officer_info, String[] quantity) {
        this.context = applicationContext;
        this.cart_id = cart_id;
        this.stock_name = stock_name;
        this.type = type;
        this.price = price;
        this.image = image;
        this.userinfo = userinfo;
        this.officer_info = officer_info;
        this.quantity = quantity;
    }

    @Override
    public int getCount() {
        return stock_name.length;
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
            gridView = inflator.inflate(R.layout.activity_custom_view_cart, null);

        } else {
            gridView = (View) view;

        }
        TextView tv1 = (TextView) gridView.findViewById(R.id.textView60);
        TextView tv2 = (TextView) gridView.findViewById(R.id.textView61);
        TextView tv3 = (TextView) gridView.findViewById(R.id.textView63);
        TextView tv4 = (TextView) gridView.findViewById(R.id.textView65);
        TextView tv5 = (TextView) gridView.findViewById(R.id.textView67);
        TextView tv6 = (TextView) gridView.findViewById(R.id.textView69);
        ImageView im = (ImageView)gridView.findViewById(R.id.imageView3);

        Button b1 = (Button)gridView.findViewById(R.id.button13);
        b1.setTag(i);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                int pos = (int)view.getTag();

                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                sh.getString("ipaddress","");
                url = sh.getString("url","") + "and_cart_cancel";

                RequestQueue requestQueue = Volley.newRequestQueue(context);
                StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                                // response
                                try {
                                    JSONObject jsonObj = new JSONObject(response);
                                    if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                        Toast.makeText(context, "Cart cancelled", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(context,view_cart.class);
                                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        context.startActivity(i);

                                    }


                                    // }
                                    else {
                                        Toast.makeText(context, "Invalid Authentication", Toast.LENGTH_LONG).show();
                                    }

                                }    catch (Exception e) {
                                    Toast.makeText(context, "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // error
                                Toast.makeText(context, "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }
                ) {
                    @Override
                    protected Map<String, String> getParams() {
                        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(context);
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("cart_id",cart_id[i]);
//                        params.put("password",password);
                        return params;
                    }
                };

                int MY_SOCKET_TIMEOUT_MS=100000;

                postRequest.setRetryPolicy(new DefaultRetryPolicy(
                        MY_SOCKET_TIMEOUT_MS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                requestQueue.add(postRequest);

            }
        });


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
        tv4.setTextColor(Color.BLACK);
        tv5.setTextColor(Color.BLACK);
        tv6.setTextColor(Color.BLACK);


        tv1.setText(stock_name[i]);
        tv2.setText(price[i]);
        tv3.setText(type[i]);
        tv4.setText(userinfo[i]);
        tv5.setText(officer_info[i]);
        tv6.setText(quantity[i]);

        SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(context);
        String ip=sh.getString("ipaddress","");

        String url="http://" + ip + ":8000"+image[i];


        Picasso.with(context).load(url).transform(new CircleTransform()). into(im);


        return gridView;
    }
}