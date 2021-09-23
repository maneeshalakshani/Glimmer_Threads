package com.example.glimmerthreads;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Image_Display extends AppCompatActivity {

    /*private EditText imageDetailsET;
    private ImageView objectImageView;

    private static final  int PICK_IMAGE_REQUEST = 100; //Can any number other than '0'
    private Uri imageFilePath;

    private Bitmap imageToStore;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image__display);

        /*try {
            imageDetailsET = findViewById(R.id.image_name);
            objectImageView = findViewById(R.id.image);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }*/
    }

    /*//To select an image from emulator
    public void choseImage(View objectView){
        try {
            Intent objectIntent = new Intent();
            objectIntent.setType("image/*");    //To select all types of images

            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,PICK_IMAGE_REQUEST);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null){
                imageFilePath = data.getData();

                //Store Image into Bitmap
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(),imageFilePath);

                //Set Image to image view
                objectImageView.setImageBitmap(imageToStore);
            }
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }*/
}










