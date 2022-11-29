package com.example.simmiotp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Confirmation extends AppCompatActivity {
ImageView congratsimg;
TextView confirmtxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        congratsimg=findViewById(R.id.congratsimg);
        confirmtxt=findViewById(R.id.confirmtxt);
    }
}