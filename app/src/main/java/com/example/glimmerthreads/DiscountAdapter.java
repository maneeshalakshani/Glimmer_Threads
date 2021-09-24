package com.example.glimmerthreads;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/*public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.DiscountViewHolder> {*/
public class DiscountAdapter {
    /*Context context;
    ArrayList<Discount> list;


    public DiscountAdapter(Context context, ArrayList<Discount> list) {
        this.context = context;
        this.list = list;
    }




    @NonNull
    @Override
    public DiscountViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new DiscountViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull DiscountViewHolder holder, int position) {
        Discount discount = list.get(position);
        holder.ItemCode.setText(discount.getiCode());
        holder.DiscountTitle.setText(discount.getDiscountTitle());
        holder.DiscountAmount.setText(discount.getAmount());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class DiscountViewHolder extends RecyclerView.ViewHolder{
        TextView DiscountTitle, ItemCode, DiscountAmount;
        public DiscountViewHolder(@NonNull View itemView) {
            super(itemView);
            DiscountAmount = itemView.findViewById(R.id.TVDiscountAmount);
            DiscountTitle = itemView.findViewById(R.id.TVDiscountTitle);
            ItemCode = itemView.findViewById(R.id.TVItemCode);
        }
    }
*/


}
