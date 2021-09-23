package com.example.glimmerthreads;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Get_Discount_Code extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get__discount__code);

        //-------------Hooks---------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //------------Tool Bar-----------------------
        setSupportActionBar(toolbar);

        //-----------Navigation Drawer Menu------------
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_Home);
    }



    //Initially when pressesd back button, the application is closing, to only close the navigation
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }




    //To selectNavigation Items
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        //To redirects to pages, when navigation items clicked
        switch (item.getItemId()){
            case R.id.nav_Home:
                break; //Come back to home when home selected
            case R.id.nav_add_discounts:
                Intent intent = new Intent(Get_Discount_Code.this,Bus.class);
                startActivity(intent);
                break; //Redirects to Bus.xml when Login clicked
            case R.id.nav_Sales:
                startActivity(new Intent(Get_Discount_Code.this,Sales.class));
                break; //Redirects to Bus.xml when Login clicked
            case R.id.nav_Share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show(); //Gives a toast message when Share Clicked
                break;
        }

        //close navigation bar when an item selected
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}