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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public LinearLayoutManager linearLayoutManager;
    public NasaAdapter nasaAdapter;
    public String date;
    public DateTime thenDate;
    public int counter = 0;
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

    public void getData(int count) {
        counter = 0;
        final List<NasaData> nasaDataList = new ArrayList<>();

        for (int i = count; i < count + 10; i++) {
            thenDate = new DateTime().minusDays(i);
            DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
            date = fmt.print(thenDate);

            Call<NasaData> call = AppSingleton.getInstance().getNasaApi().getApod(date);

            call.enqueue(new Callback<NasaData>() {
                @Override
                public void onResponse(Call<NasaData> dataObjects, Response<NasaData> response) {
                    counter++;
                    nasaDataList.add(response.body());
                    if (counter == 10) {
                        nasaAdapter.setDataList(nasaDataList);
                        endlessScrollListener.setLoading(false);
                    }
                }
                @Override
                public void onFailure(Call<NasaData> data, Throwable t) {
                    counter++;
                    if (counter == 10) {
                        nasaAdapter.setDataList(nasaDataList);
                        endlessScrollListener.setLoading(false);
                    }
                }
            });
        }
    }

} 