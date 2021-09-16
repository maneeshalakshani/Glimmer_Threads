package com.example.glimmerthreads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        println("Test commit from Maneesha branch");
        println("To check pull request");
        println("To check pull request");

        println("Test commit from Maneesha branch");
        println("To check pull request");
        println("To check pull request");
    }
}