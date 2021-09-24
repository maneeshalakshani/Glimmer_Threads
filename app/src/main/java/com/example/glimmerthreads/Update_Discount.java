package com.example.glimmerthreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Update_Discount extends AppCompatActivity {

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


}