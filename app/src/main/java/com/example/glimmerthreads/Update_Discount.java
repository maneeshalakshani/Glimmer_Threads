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
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Update_Discount extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    EditText discount,code,name;
    int DiscountId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update__discount);

        Discount discountVar = (Discount) getIntent().getExtras().getSerializable("DISCOUNT");

        DiscountId = discountVar.getDiscountID();
        name = findViewById(R.id.get_item_name);
        discount = findViewById(R.id.update_Amount);
        code = findViewById(R.id.update_item_code);

        name.setText(discountVar.getDiscountTitle());
        discount.setText(String.valueOf(discountVar.getAmount()));
        code.setText(String.valueOf(discountVar.getiCode()));




        //-------------Hooks for Navigation ---------------------
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


    public void updateDiscount(View view){
        String title = name.getText().toString().trim();
        String amount = discount.getText().toString().trim();
        String item_code = code.getText().toString().trim();

        Discount d = new Discount(DiscountId,title,Integer.parseInt(amount),Integer.parseInt(item_code));
        DHhelper database = new DHhelper(this);
        int result = database.updateDiscount(d);
        if(result > 0){
            Toast.makeText(this, "Updated "+result, Toast.LENGTH_SHORT).show();
            finish();   //Automatically redirects to previous screen
        }else{
            Toast.makeText(this, "Not Updated "+result, Toast.LENGTH_SHORT).show();
        }
    }





    //================= Navigation =================================================================================================================================================


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
            case R.id.nav_add_discounts:
                Intent intent = new Intent(Update_Discount.this,Bus.class);
                startActivity(intent);
                break;
            case R.id.nav_Home:
                startActivity(new Intent(Update_Discount.this,MainActivity.class));
                break; //Come back to home when home selected
            case R.id.nav_Sales:
                startActivity(new Intent(Update_Discount.this,Sales.class));
                break; //Redirects to Bus.xml when Login clicked
            case R.id.nav_Rate:
                startActivity(new Intent(Update_Discount.this,Image_View.class));
                break; //Redirects to Bus.xml when Login clicked
            case R.id.nav_modify_discounts:
                startActivity(new Intent(Update_Discount.this,Display_All_Data.class));
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