package com.example.nasaapi;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NasaAdapter extends RecyclerView.Adapter<NasaViewHolder>{
    private Context context;
    private LayoutInflater layoutInflater;
    private List<NasaData> dataList = new ArrayList<>();

    public NasaAdapter(Context context2) {
        this.context = context2;
        this.layoutInflater = LayoutInflater.from(context2);
    }
    @Override
    public NasaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.row_picture, parent, false);
        final NasaViewHolder viewHolder = new NasaViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(NasaViewHolder holder, int position) {
        final NasaData nasaData = dataList.get(position);
        Picasso.with(context).load(nasaData.getUrl()).into(holder.imageView);
        holder.textView.setText(nasaData.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,NasaDetailActivity.class);
                intent.putExtra("nasaData", AppSingleton.getInstance().getGson().toJson(nasaData));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return  dataList.size();
    }
    public void setDataList(List<NasaData> nasaDataList){
        this.dataList.addAll(nasaDataList);
        Collections.sort(this.dataList, new Comparator<NasaData>() {
            @Override
            public int compare(NasaData o1, NasaData o2) {
                return o1.getDate().getTime() > o2.getDate().getTime()?-1:1;
            }
        });
        notifyDataSetChanged();
    }
}
