package com.example.glimmerthreads;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ModifierDiscountAdapter extends RecyclerView.Adapter<ModifierDiscountAdapter.DiscountVH>{

    //Variables
    ArrayList<Discount> discountArrayList;
    Context context;

    //Constructors
    public ModifierDiscountAdapter(ArrayList<Discount> discountArrayList, Context context) {
        this.discountArrayList = discountArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DiscountVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_discount,parent,false);
        DiscountVH discountVH = new DiscountVH(view);

        return discountVH;
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountVH holder, int position) {
        final Discount dis = discountArrayList.get(position);

        holder.tvTitle.setText(dis.getDiscountTitle());
        holder.tvItemCode.setText(String.valueOf(dis.getiCode()));
        holder.tvDiscountCode.setText(String.valueOf(dis.getDiscountID()));
        holder.tvAmount.setText(String.valueOf(dis.getAmount()));



        holder.updateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, dis.getDiscountTitle() + " will be Updated", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(context,Update_Discount.class);
                intent.putExtra("DISCOUNT", dis);
                context.startActivity(intent);
            }
        });


        holder.deleteCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(context, dis.getDiscountTitle() + " will be Deleted", Toast.LENGTH_SHORT).show();

                //Ask Confirmation in an alert Box
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Confirmation");
                builder.setMessage("Are you sure to delete " + dis.getDiscountTitle() + " ?");
                builder.setIcon(android.R.drawable.ic_menu_delete); //Delete Icon

                builder.setCancelable(false);   //To stop go back from alert;if not either yes or no clicked

                //If 'Yes' clicked, Do delete
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DHhelper DB = new DHhelper(context);
                        int result = DB.deleteDiscount(dis.getDiscountID());
                        if(result > 0){
                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                            discountArrayList.remove(dis);
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


    //To gets the size of discountArrayList
    @Override
    public int getItemCount() {
        return discountArrayList.size();
    }



    class DiscountVH extends RecyclerView.ViewHolder{

        TextView tvTitle, tvDiscountCode,tvAmount, tvItemCode;
        CardView updateCard,deleteCard;

        public DiscountVH(@NonNull View v) {
            super(v);

            tvTitle = v.findViewById(R.id.TVDiscountTitle);
            tvAmount = v.findViewById(R.id.discountAmountTV);
            tvDiscountCode = v.findViewById(R.id.discountCodeTV);
            tvItemCode = v.findViewById(R.id.itemCodeTV);

            updateCard = v.findViewById(R.id.updateCard);
            deleteCard = v.findViewById(R.id.deleteCard);
        }
    }
}
