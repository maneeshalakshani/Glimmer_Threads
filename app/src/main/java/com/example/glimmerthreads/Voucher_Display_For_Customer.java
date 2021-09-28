package com.example.glimmerthreads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Voucher_Display_For_Customer extends AppCompatActivity {

    private DBHandler DB;
    private RecyclerView recyclerViewObject;
    ArrayList<ImageModel> imageList;

    private Voucher_for_CustomerAdapter Voucher_for_CustomerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voucher__display__for__customer);

        try {
            recyclerViewObject = findViewById(R.id.imagesRV);
            DB = new DBHandler(this);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }



    public void getData(View view){
        try {
            /*rvAdapter = new RVAdapter(DB.getAllImagesData());*/
            imageList = DB.getAllImagesData();

            Voucher_for_CustomerAdapter = new Voucher_for_CustomerAdapter(imageList,this);
            recyclerViewObject.setAdapter(Voucher_for_CustomerAdapter);
            recyclerViewObject.setHasFixedSize(true);

            recyclerViewObject.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewObject.setAdapter(Voucher_for_CustomerAdapter);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}