package com.example.dell.myapplication14.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.example.dell.myapplication14.Model.Object.Discount_detail;
import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class TopPhoneAdapter extends RecyclerView.Adapter<TopPhoneAdapter.ViewHolderTop> {
    Context context;
    List<Product> productList;

    public TopPhoneAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ViewHolderTop onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycleview_topphone_laptop, parent, false);
        ViewHolderTop viewHolderTop = new ViewHolderTop(view);
        return viewHolderTop;
    }

    @Override
    public void onBindViewHolder(final ViewHolderTop holder, int position) {
        Product product = productList.get(position);
        holder.txtName.setText(product.getProduct_name());

        Discount_detail discount_detail = product.getDiscount_detail();

        int priceSP = product.getPrice();

        if (discount_detail != null) {
            int percent_deiscount = discount_detail.getPercent();

            NumberFormat numberFormat = new DecimalFormat("###,###");
            priceSP=priceSP*percent_deiscount/100;
            String price1 = numberFormat.format(priceSP);
            holder.txtDiscount.setVisibility(View.VISIBLE);
            holder.txtDiscount.setPaintFlags(holder.txtDiscount.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.txtDiscount.setText(price1 + " VNĐ");


        }

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String price = numberFormat.format(product.getPrice()).toString();
        holder.txtPrice.setText(price + " VNĐ");

        Picasso.with(context).load(product.getBig_image()).resize(300, 250)
                .centerInside()
                .placeholder(R.drawable.imgplaceholder)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolderTop extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtName, txtPrice, txtDiscount;
        ProgressBar progressBar;

        public ViewHolderTop(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgTopPhoneAndLaptop);
            txtName = itemView.findViewById(R.id.txtNameProduct);
            txtPrice = itemView.findViewById(R.id.txtPriceProduct);
            txtDiscount = itemView.findViewById(R.id.txtPriceDiscount);
            progressBar = itemView.findViewById(R.id.progress_barTop);
        }
    }
}
