package com.example.admin.heartmonitor;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    public ImageView fingerPrintImageView;
    public ImageView mailImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        fingerPrintImageView=(ImageView) findViewById(R.id.imageView);
        mailImageView=(ImageView) findViewById(R.id.imageView2);

        mailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SplashScreen.this,MainActivity.class);
                startActivity(i);
            }
        });

        fingerPrintImageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(SplashScreen.this,"Finger Print feature is not added at the moment..",Toast.LENGTH_LONG);
            }
        });

    }
}
