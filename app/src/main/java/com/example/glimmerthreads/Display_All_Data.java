package com.example.glimmerthreads;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.fonts.SystemFonts;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Display_All_Data extends AppCompatActivity{

    TextView TVTotal;
    RecyclerView recyclerView;
    ModifierDiscountAdapter modifierDiscountAdapter;
    ArrayList<Discount> discountList;
    DHhelper dHhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__all__data);

        recyclerView = findViewById(R.id.DiscountListRV);
        TVTotal = findViewById(R.id.TVTotal);
        dHhelper = new DHhelper(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        discountList = dHhelper.getAllDiscount();
        TVTotal.setText("Total Discounts : " + discountList.size());

        modifierDiscountAdapter = new ModifierDiscountAdapter(discountList,this);
        recyclerView.setAdapter(modifierDiscountAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}