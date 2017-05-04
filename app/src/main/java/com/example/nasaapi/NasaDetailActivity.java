package com.example.nasaapi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NasaDetailActivity extends AppCompatActivity {
    private static Context detailContext;
    private ImageView picImageView2;
    private TextView titleTextView2;
    private TextView dateTextView;
    private TextView explanationTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        NasaData nasaData = AppSingleton.getInstance().getGson().fromJson(getIntent().getStringExtra("nasaData"), NasaData.class);

        picImageView2 = (ImageView) findViewById(R.id.picImageView2);
        titleTextView2 = (TextView) findViewById(R.id.titleTextView2);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        explanationTextView = (TextView) findViewById(R.id.explanationTextView);

        Picasso.with(detailContext).load(nasaData.getUrl()).into(picImageView2);
        titleTextView2.setText(nasaData.getTitle());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        dateTextView.setText(sdf.format(nasaData.getDate()));
        explanationTextView.setText(nasaData.getExplanation());

    }
}
