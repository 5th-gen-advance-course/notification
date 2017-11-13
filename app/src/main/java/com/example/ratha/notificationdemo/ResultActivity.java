package com.example.ratha.notificationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.tvResult)
    TextView tvResult;
    @BindView(R.id.image)
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        Intent intent=getIntent();
        if(null!=intent){
            String s=intent.getStringExtra("DATA");
            int imageId= intent.getIntExtra("IMAGE" ,R.drawable.ic_bear_460dp);
            tvResult.setText(s);
            imageView.setImageResource(imageId);
        }
    }
}
