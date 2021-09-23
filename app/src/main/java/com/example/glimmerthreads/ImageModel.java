package com.example.glimmerthreads;

import android.graphics.Bitmap;

public class ImageModel {

    private String imageName;
    private Bitmap image;

    public ImageModel(String imageName, Bitmap image) {
        this.imageName = imageName;
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
