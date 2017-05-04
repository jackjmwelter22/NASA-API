package com.example.nasaapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.method.DateTimeKeyListener;
import android.util.Log;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public LinearLayoutManager linearLayoutManager;
    public NasaAdapter nasaAdapter;
    public String date;
    public DateTime thenDate;
    private EndlessScrollListener endlessScrollListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.aRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        nasaAdapter = new NasaAdapter(this);
        recyclerView.setAdapter(nasaAdapter);

        getData(0);

        endlessScrollListener = new EndlessScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore() {
                getData(nasaAdapter.getItemCount() + 1);
            }
        };
        recyclerView.addOnScrollListener(endlessScrollListener);
    }

    public void getData(final int count) {
        final List<NasaData> nasaDataList = new ArrayList<>();

        final android.os.Handler handler = new android.os.Handler();

        new Thread(new Runnable() {
            public int counter = 0;

            @Override
            public void run() {

                for (int i = count; i < count + 10; i++) {
                    thenDate = new DateTime().minusDays(i);
                    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
                    date = fmt.print(thenDate);

                    Call<NasaData> call = AppSingleton.getInstance().getNasaApi().getApod(date);

                    try {
                        nasaDataList.add(call.execute().body());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    counter++;
                    if (counter == 10) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                nasaAdapter.setDataList(nasaDataList);
                                endlessScrollListener.setLoading(false);
                            }
                        });

                    }

//
                }
            }
        }).start();


    }

}