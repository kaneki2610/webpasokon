package com.example.dell.myapplication14.Adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dell.myapplication14.Model.Object.Discount;
import com.example.dell.myapplication14.R;

import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.ViewHolderDiscount> {
    Context context;
    List<Discount>discountList;
    public DiscountAdapter(Context context,List<Discount>list)
    {
        this.context=context;
        this.discountList=list;
    }
    @Override
    public ViewHolderDiscount onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_line_discount, parent, false);
        ViewHolderDiscount viewHolderDiscount=new ViewHolderDiscount(view);
        return viewHolderDiscount;
    }

    @Override
    public void onBindViewHolder(ViewHolderDiscount holder, int position) {
        Discount discount=discountList.get(position);
        holder.textView.setText(discount.getCategory_name());
        TopPhoneAdapter topPhoneAdapter=new TopPhoneAdapter(context,discount.getProductList());
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(topPhoneAdapter);



    }

    @Override
    public int getItemCount() {
        return discountList.size();
    }

    public class ViewHolderDiscount extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        TextView textView;
        public ViewHolderDiscount(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.txtTitleKM);
            recyclerView=itemView.findViewById(R.id.recycleView_KM);
        }
    }
}
