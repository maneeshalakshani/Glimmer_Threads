package com.example.glimmerthreads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Voucher_for_CustomerAdapter extends RecyclerView.Adapter<Voucher_for_CustomerAdapter.RVViewHolderClass>{

    ArrayList<ImageModel> objectImageModelClassList;
    Context context;

    public Voucher_for_CustomerAdapter(ArrayList<ImageModel> objectImageModelClassList, Context context) {
        this.objectImageModelClassList = objectImageModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public Voucher_for_CustomerAdapter.RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Voucher_for_CustomerAdapter.RVViewHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_voucher,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Voucher_for_CustomerAdapter.RVViewHolderClass holder, int position) {
        final ImageModel objectModelClass = objectImageModelClassList.get(position);

        holder.imageNameTV.setText(objectModelClass.getImageName());
        holder.objectImageView.setImageBitmap(objectModelClass.getImage());

        holder.voucher_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, dis.getDiscountTitle() + " will be Deleted", Toast.LENGTH_SHORT).show();

                //Ask Confirmation in an alert Box
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Use This code as Voucher...");
                builder.setMessage( objectModelClass.getImageName());
                builder.setIcon(android.R.drawable.ic_menu_delete); //Delete Icon

                builder.setCancelable(false);   //To stop go back from alert;if not either yes or no clicked

                builder.setPositiveButton("OK",null); //Nothing Happens if 'No clicked'
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return objectImageModelClassList.size();
    }


    //View holder class
    public static class RVViewHolderClass extends RecyclerView.ViewHolder{
        TextView imageNameTV;
        ImageView objectImageView;
        CardView voucher_delete_btn;

        public RVViewHolderClass(@NonNull View itemView) {
            super(itemView);
            imageNameTV = itemView.findViewById(R.id.imageDetailsTV);

            objectImageView = itemView.findViewById(R.id.sr_imageRow);

            voucher_delete_btn = itemView.findViewById(R.id.voucher_delete_btn);
        }
    }

}
