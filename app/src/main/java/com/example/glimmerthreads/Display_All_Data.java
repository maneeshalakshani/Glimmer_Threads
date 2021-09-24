package com.example.glimmerthreads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.fonts.SystemFonts;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Display_All_Data extends AppCompatActivity {

    TextView TVTotal;
    RecyclerView recyclerView;
    ModifierDiscountAdapter modifierDiscountAdapter;
    ArrayList<Discount> discountList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__all__data);

        recyclerView = findViewById(R.id.DiscountListRV);
        TVTotal = findViewById(R.id.TVTotal);

        DHhelper dHhelper = new DHhelper(this);

        discountList = dHhelper.getAllDiscount();
        TVTotal.setText("Total Students : " + discountList.size());

        modifierDiscountAdapter = new ModifierDiscountAdapter(discountList,this);
        recyclerView.setAdapter(modifierDiscountAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}