package com.example.glimmerthreads;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Display_Discount_To_Get_Adapter extends RecyclerView.Adapter<Display_Discount_To_Get_Adapter.DiscountVH>{

    //Variables
    ArrayList<Discount> discountArrayList;
    Context context;

    //Constructors
    public Display_Discount_To_Get_Adapter(ArrayList<Discount> discountArrayList, Context context) {
        this.discountArrayList = discountArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DiscountVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_discount_customers,parent,false);
        DiscountVH discountVH = new DiscountVH(view);

        return discountVH;
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountVH holder, int position) {
        final Discount dis = discountArrayList.get(position);

        holder.tvTitle.setText(dis.getDiscountTitle());
        holder.tvItemCode.setText(String.valueOf(dis.getiCode()));
        holder.tvAmount.setText(String.valueOf(dis.getAmount()));



        holder.updateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Ask Confirmation in an alert Box
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Discount Code");
                builder.setMessage("Use this code : " + dis.getDiscountID());
                builder.setIcon(android.R.drawable.ic_input_get); //Delete Icon
                builder.setPositiveButton("OK",null);
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

        TextView tvTitle,tvAmount, tvItemCode;
        CardView updateCard;

        public DiscountVH(@NonNull View v) {
            super(v);

            tvTitle = v.findViewById(R.id.TVDiscountTitle);
            tvAmount = v.findViewById(R.id.discountAmountTV);
            tvItemCode = v.findViewById(R.id.itemCodeTV);

            updateCard = v.findViewById(R.id.updateCard);
        }
    }
}

