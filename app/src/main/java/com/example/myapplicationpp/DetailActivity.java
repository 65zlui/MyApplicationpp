package com.example.myapplicationpp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;

import android.widget.TextView;


public class DetailActivity extends AppCompatActivity {
    long stopTime1=0;
    static boolean isIn=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();

        TextView title=(findViewById(R.id.title));
        title.setText(intent.getStringExtra("title"));

        TextView source=(findViewById(R.id.source));
        source.setText(intent.getStringExtra("source"));

        TextView time=(findViewById(R.id.time));
        time.setText(intent.getStringExtra("time"));

        TextView content=(findViewById(R.id.content));
        content.setText(intent.getStringExtra("news_content"));
    }

    public void onStart(){
        super.onStart();
        stopTime1=System.currentTimeMillis();
        ListActivity.ttime1=stopTime1;

    }
}