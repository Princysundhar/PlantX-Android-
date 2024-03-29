package com.example.krishibhavan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.krishibhavan.databinding.ActivityHomeBinding;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;
    TextView tv1,tv2;
    SharedPreferences sh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);
//        binding.appBarHome.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        View headerView = navigationView.getHeaderView(0);

        tv1 = headerView.findViewById(R.id.textview1);
        tv2 = headerView.findViewById(R.id.textView);
        SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        tv1.setText(sh.getString("name",""));
        tv2.setText(sh.getString("email",""));

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.view_profile){
            Intent i = new Intent(getApplicationContext(),view_profile.class);
            startActivity(i);
        }
        if(id==R.id.Agriculture_office){
            Intent i = new Intent(getApplicationContext(),view_agriculture_office.class);
            startActivity(i);
        }
        if(id==R.id.notification){
            Intent i = new Intent(getApplicationContext(),view_notification.class);
            startActivity(i);
        }
        if(id==R.id.complaint){
            Intent i = new Intent(getApplicationContext(),view_reply.class);
            startActivity(i);
        }
        if(id==R.id.feedback){
            Intent i = new Intent(getApplicationContext(),send_feedback.class);
            startActivity(i);
        }
        if(id==R.id.view_cart){
            Intent i = new Intent(getApplicationContext(),view_cart.class);
            startActivity(i);
        }
        if(id==R.id.orders){
            Intent i = new Intent(getApplicationContext(),view_orders.class);
            startActivity(i);
        }
        if(id==R.id.logout)
        {
            SharedPreferences sh = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor ed = sh.edit();
            ed.commit();
            ed.clear();
            Intent i = new Intent(getApplicationContext(),ip_page.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);
        }
        return false;
    }
}