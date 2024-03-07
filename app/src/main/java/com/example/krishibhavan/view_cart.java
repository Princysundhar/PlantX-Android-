package com.example.krishibhavan;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class view_cart extends AppCompatActivity {
    ListView li;
    SharedPreferences sh;
    String url;
    TextView tv1;
    Button b1;
    String[]cart_id,stock_name,type,price,image,userinfo,officer_info,quantity;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        li = findViewById(R.id.listview);
        tv1 = findViewById(R.id.textView57);
        b1 = findViewById(R.id.button11);

        sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sh.getString("ipaddress","");
        url = sh.getString("url","") + "and_view_cart";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();

                        // response
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            if (jsonObj.getString("status").equalsIgnoreCase("ok")) {
                                String amount = jsonObj.getString("t");        // Getting Total amount
                                SharedPreferences.Editor ed=sh.edit();
                                ed.putString("amount",amount);
                                ed.commit();
                                tv1.setText(amount);
                                tv1.setTextColor(Color.WHITE);

                                JSONArray js= jsonObj.getJSONArray("data");
                                cart_id=new String[js.length()];
                                stock_name=new String[js.length()];
                                type=new String[js.length()];
                                price=new String[js.length()];
                                image=new String[js.length()];
                                userinfo=new String[js.length()];
                                officer_info=new String[js.length()];
                                quantity=new String[js.length()];

                                for(int i=0;i<js.length();i++)
                                {
                                    JSONObject u=js.getJSONObject(i);
                                    cart_id[i]=u.getString("cart_id");
                                    stock_name[i]=u.getString("stock_name");
                                    type[i]=u.getString("type");
                                    price[i]=u.getString("price");
                                    image[i]=u.getString("image");
                                    userinfo[i]=u.getString("name")+"\n"+u.getString("contact");
                                    officer_info[i]=u.getString("officer_name")+"\n"+u.getString("officer_contact");
                                    quantity[i]=u.getString("quantity");
                                }


                                li.setAdapter(new custom_view_cart(getApplicationContext(),cart_id,stock_name,type,price,image,userinfo,officer_info,quantity));

                            }


                            // }
                            else {
                                Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                            }

                        }    catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "Error" + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(getApplicationContext(), "eeeee" + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Map<String, String> params = new HashMap<String, String>();

                params.put("lid",sh.getString("lid",""));

                return params;
            }
        };

        int MY_SOCKET_TIMEOUT_MS=100000;

        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(postRequest);

        b1.setOnClickListener(new View.OnClickListener() {                  // Place order
            @Override
            public void onClick(View v) {
//                String amount = tv1.getText().toString();
                Intent i = new Intent(getApplicationContext(),payment_mode.class);
                startActivity(i);


            }
        });



    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(),Home.class);
        startActivity(i);
    }
}