package com.example.krishibhavan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    Button b1;
    SharedPreferences sh;
    String url;
    String MobilePattern = "[6-9][0-9]{9}";
    String PinPattern = "[6-9][0-9]{5}";
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String password_pattern="[A-Za-z0-9]{3,8}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        e1 = findViewById(R.id.editTextTextPersonName3);
        e2 = findViewById(R.id.editTextTextPersonName4);
        e3 = findViewById(R.id.editTextTextPersonName5);
        e4 = findViewById(R.id.editTextNumber);
        e5 = findViewById(R.id.editTextTextEmailAddress);
        e6 = findViewById(R.id.editTextNumber2);
        e7 = findViewById(R.id.editTextTextPassword2);
        e8 = findViewById(R.id.editTextTextPassword3);
        b1 = findViewById(R.id.button3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = e1.getText().toString();
                String place = e2.getText().toString();
                String post = e3.getText().toString();
                String pin = e4.getText().toString();
                String email = e5.getText().toString();
                String contact = e6.getText().toString();
                String password = e7.getText().toString();
                String confirm_password = e8.getText().toString();
                int flag = 0;
                if (name.equalsIgnoreCase("")) {
                    e1.setError("Null");
                    flag++;
                }
                if (place.equalsIgnoreCase("")) {
                    e2.setError("Null");
                    flag++;
                }
                if (post.equalsIgnoreCase("")) {
                    e3.setError("Null");
                    flag++;
                }
                if (!pin.matches(PinPattern)) {
                    e4.setError("Null");
                    flag++;
                }
                if (!email.matches(emailPattern)) {
                    e5.setError("Null");
                    flag++;
                }
                if (!contact.matches(MobilePattern)) {
                    e6.setError("Null");
                    flag++;
                }
                if (!password.matches(password_pattern)) {
                    e7.setError("Null");
                    flag++;
                }
                if(!password.matches(confirm_password)){
                    Toast.makeText(Register.this, "Try Again", Toast.LENGTH_SHORT).show();
                    flag++;
                }
                if(flag==0) {

                    sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    sh.getString("ipaddress", "");
                    url = sh.getString("url", "") + "and_user_registration";

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
                                            Toast.makeText(Register.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                            Intent i = new Intent(getApplicationContext(), Login.class);
                                            startActivity(i);

                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "Not found", Toast.LENGTH_LONG).show();
                                        }

                                    } catch (Exception e) {
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

                            params.put("name", name);
                            params.put("place", place);
                            params.put("post", post);
                            params.put("pin", pin);
                            params.put("email", email);
                            params.put("contact", contact);
                            params.put("password", password);
                            return params;
                        }
                    };

                    int MY_SOCKET_TIMEOUT_MS = 100000;

                    postRequest.setRetryPolicy(new DefaultRetryPolicy(
                            MY_SOCKET_TIMEOUT_MS,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    requestQueue.add(postRequest);
                }

            }
        });
    }
}