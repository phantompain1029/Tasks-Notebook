package com.example.testproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_page);


        String Title = getIntent().getExtras().getString("TITLE");
        String Details = getIntent().getExtras().getString("DETAILS");
        TextView TITLE, BODY;

        TITLE = findViewById(R.id.DetailTitle);
        BODY = findViewById(R.id.DetailBody);

        TITLE.setText(Title);
        BODY.setText(Details);


    }
}