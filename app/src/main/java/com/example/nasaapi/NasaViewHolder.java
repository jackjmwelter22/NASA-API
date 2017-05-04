package com.example.nasaapi;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class NasaViewHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;
    public TextView textView;

    public NasaViewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.picImageView);
        textView = (TextView) itemView.findViewById(R.id.titleTextView);
    }
}
