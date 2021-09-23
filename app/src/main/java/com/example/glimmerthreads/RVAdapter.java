package com.example.glimmerthreads;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolderClass> {

    ArrayList<ImageModel> objectImageModelClassList;


    //Constructor
    public RVAdapter(ArrayList<ImageModel> objectImageModelClassList) {
        this.objectImageModelClassList = objectImageModelClassList;
    }


    @NonNull
    @Override
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVViewHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolderClass holder, int position) {
        ImageModel objectModelClass = objectImageModelClassList.get(position);

        holder.imageNameTV.setText(objectModelClass.getImageName());
        holder.objectImageView.setImageBitmap(objectModelClass.getImage());
    }

    @Override
    public int getItemCount() {
        return objectImageModelClassList.size();
    }


    //View holder class
    public static class RVViewHolderClass extends RecyclerView.ViewHolder{
        TextView imageNameTV;
        ImageView objectImageView;

        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageNameTV = itemView.findViewById(R.id.imageDetailsTV);

            objectImageView = itemView.findViewById(R.id.sr_imageRow);
        }
    }

}
