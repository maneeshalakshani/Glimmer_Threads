package com.example.glimmerthreads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Bus extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    EditText discount,code,name;  //----
    Button insert,update,delete,view,button_view_a_discount;
    DHhelper DB;
    //DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);

        //-------------Hooks---------------------
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);
        name = findViewById(R.id.get_item_name);
        discount = findViewById(R.id.get_discount_1);
        code = findViewById(R.id.get_item_code);    //-----
        /*icode = findViewById(R.id.get_item_code);      //----*/

        insert = findViewById(R.id.button_get_discount);
        update = findViewById(R.id.button_update_discount);
        delete = findViewById(R.id.button_delete_discount);
        view = findViewById(R.id.button_view_discount);
        button_view_a_discount = findViewById(R.id.button_view_a_discount);

        DB = new DHhelper(this);



        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String discountName = name.getText().toString();
                int discountTXT = Integer.parseInt(discount.getText().toString());
                int codeTXT = Integer.parseInt(code.getText().toString());

                Boolean checkInsertData = DB.insertData(discountName,codeTXT,discountTXT);
                if(checkInsertData == true){
                    Toast.makeText(Bus.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Bus.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });




        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String discountName = name.getText().toString();
                int discountTXT = Integer.parseInt(discount.getText().toString());
                int codeTXT = Integer.parseInt(code.getText().toString());
                /*int icodeTXT = Integer.parseInt(icode.getText().toString());*/


                Boolean checkUpdateData = DB.updateData(discountName, codeTXT, discountTXT);
                if (checkUpdateData == true) {
                    Toast.makeText(Bus.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Bus.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
                }

            }
        });



        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int codeTXT = Integer.parseInt(code.getText().toString());

                Boolean checkDeleteData = DB.deleteDiscount(codeTXT);
                if(checkDeleteData == true){
                    Toast.makeText(Bus.this, "Entry Deleted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Bus.this, "Entry Not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });




        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor result = DB.getDiscount();
                if(result.getCount() == 0){
                    Toast.makeText(Bus.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()){
                    buffer.append("Item Code : "+result.getString(0)+"\n");
                    /*buffer.append("Item Code : "+result.getString(1)+"\n");*/
                    buffer.append("Discount Amount : "+result.getString(1)+"\n");
                    buffer.append("Title Name : "+result.getString(2)+"\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(Bus.this);
                builder.setCancelable(true);
                builder.setTitle("Glimmer Threads Discounts");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });



        button_view_a_discount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemCode = Integer.parseInt(code.getText().toString());;
                Cursor result = DB.getADiscout(itemCode);
                if(result.getCount() == 0){
                    Toast.makeText(Bus.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                //while (result.moveToNext()){
                buffer.append("Item Code : "+result.getString(0)+"\n");
                /*buffer.append("Item Code : "+result.getString(1)+"\n");*/
                buffer.append("Discount Amount : "+result.getString(1)+"\n");
                buffer.append("Title Name : "+result.getString(2)+"\n\n");
                //}

                AlertDialog.Builder builder = new AlertDialog.Builder(Bus.this);
                builder.setCancelable(true);
                builder.setTitle("Glimmer Threads");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });





        /*//Insert Images
        public void InsertImage(View view){
            try{
                if(!imageDetails.getText.toString().isEmpty() && objectImageView.getDrawable() != null && imageToStore != null){
                    dbHandler.storeImage(new ImageModel(imageDetails.getText().toString(),imageToStore));
                }else{
                    Toast.makeText(this, "Please select an Image name and Image", Toast.LENGTH_SHORT).show();
                }
            }catch(Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }*/




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
            case R.id.nav_add_discounts:
                break; //Come back to home when home selected
            case R.id.nav_Home:
                Intent intent = new Intent(Bus.this,MainActivity.class);
                startActivity(intent);
                break; //Redirects to Bus.xml when Login clicked
            case R.id.nav_Sales:
                startActivity(new Intent(Bus.this,Sales.class));
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