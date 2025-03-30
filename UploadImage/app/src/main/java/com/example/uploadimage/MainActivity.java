package com.example.uploadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.uploadimage.activity.ConnectServer;
import com.example.uploadimage.activity.UploadActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

//    public void GetAll(View v) {
//        startActivity(new Intent(MainActivity.this, GetAll.class));                     // Start the activity to get all images
//    }

    public void Upload(View v) {
        startActivity(new Intent(MainActivity.this, UploadActivity.class));                     // Start the activity to upload an image
    }

    public void ConnectServer(View v) {
        startActivity(new Intent(MainActivity.this, ConnectServer.class));                     // Start the activity to upload an image
    }
//
//    public void GetByName(View v) {
//        startActivity(new Intent(MainActivity.this, GetByName.class));                  // Start the activity to get an image by its name
//    }
}