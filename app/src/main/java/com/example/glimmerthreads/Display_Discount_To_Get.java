package com.example.glimmerthreads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Display_Discount_To_Get extends AppCompatActivity {

    TextView TVTotal;
    RecyclerView recyclerView;
    Display_Discount_To_Get_Adapter modifierDiscountAdapter;
    ArrayList<Discount> discountList;
    DHhelper dHhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display__discount__to__get);

        recyclerView = findViewById(R.id.DiscountListRV);
        TVTotal = findViewById(R.id.TVTotal);

        dHhelper = new DHhelper(this);
    }

    @Override
    protected void onStart() {
        super.onStart();

        discountList = dHhelper.getAllDiscount();
        TVTotal.setText("Total Discounts : " + discountList.size());

        modifierDiscountAdapter = new Display_Discount_To_Get_Adapter(discountList,this);
        recyclerView.setAdapter(modifierDiscountAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}