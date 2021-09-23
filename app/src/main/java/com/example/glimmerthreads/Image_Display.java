package com.example.glimmerthreads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class Image_Display extends AppCompatActivity {

    private DBHandler DB;
    private RecyclerView recyclerViewObject;

    private RVAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image__display);

        try {
            recyclerViewObject = findViewById(R.id.imagesRV);
            DB = new DBHandler(this);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void getData(View view){
        try {
            rvAdapter = new RVAdapter(DB.getAllImagesData());
            recyclerViewObject.setHasFixedSize(true);

            recyclerViewObject.setLayoutManager(new LinearLayoutManager(this));
            recyclerViewObject.setAdapter(rvAdapter);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}










