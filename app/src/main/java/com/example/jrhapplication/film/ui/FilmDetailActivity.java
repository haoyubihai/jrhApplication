package com.example.jrhapplication.film.ui;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.jrhapplication.R;

public class FilmDetailActivity extends Activity {

    TextView tvTitle,tvCompany,tvDurationTime,tvInTime,btnIdCard,btnCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_detail);
        initViews();
    }

    private void initViews() {
        tvTitle  = findViewById(R.id.tvTitle);
        tvCompany = findViewById(R.id.tvCompany);
        tvDurationTime = findViewById(R.id.tvDurationTime);
        tvInTime = findViewById(R.id.tvInTime);
        btnIdCard = findViewById(R.id.btnIdCard);
        btnCode = findViewById(R.id.btnCode);

        btnIdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    public void show(String title,String company,String durationTime,String inTime){

        tvTitle.setText(title);
        tvCompany.setText(company);
        tvDurationTime.setText(durationTime);
        tvInTime.setText(inTime);
    }
}