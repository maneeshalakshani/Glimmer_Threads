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

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVViewHolderClass> {

    ArrayList<ImageModel> objectImageModelClassList;
    Context context;


    //Constructor
   /* public RVAdapter(ArrayList<ImageModel> objectImageModelClassList) {
        this.objectImageModelClassList = objectImageModelClassList;
    }*/

    public RVAdapter(ArrayList<ImageModel> objectImageModelClassList, Context context) {
        this.objectImageModelClassList = objectImageModelClassList;
        this.context = context;
    }

    @NonNull
    @Override
    public RVViewHolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RVViewHolderClass(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolderClass holder, int position) {
        final ImageModel objectModelClass = objectImageModelClassList.get(position);

        holder.imageNameTV.setText(objectModelClass.getImageName());
        holder.objectImageView.setImageBitmap(objectModelClass.getImage());

        holder.voucher_delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, dis.getDiscountTitle() + " will be Deleted", Toast.LENGTH_SHORT).show();

                //Ask Confirmation in an alert Box
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure to delete " + objectModelClass.getImageName() + " ?");
                builder.setIcon(android.R.drawable.ic_menu_delete); //Delete Icon

                builder.setCancelable(false);   //To stop go back from alert;if not either yes or no clicked

                //If 'Yes' clicked, Do delete
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHandler DB = new DBHandler(context);
                        int result = DB.deleteVoucher(objectModelClass.getImageName());
                        if(result > 0){
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            objectImageModelClassList.remove(objectModelClass);
                            notifyDataSetChanged();
                        }else{
                            Toast.makeText(context, "Not Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("No",null); //Nothing Happens if 'No clicked'
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
