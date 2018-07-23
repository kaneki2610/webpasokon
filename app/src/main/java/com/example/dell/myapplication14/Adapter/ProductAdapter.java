package com.example.dell.myapplication14.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication14.Model.Object.Product;

import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.ProductDetail.ProductDetailActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolderPro> {
    Context context;
    List<Product> productList;
    int layout;

    public ProductAdapter(Context context, List<Product> productList, int layout) {
        this.context = context;
        this.productList = productList;
        this.layout = layout;
    }

    @Override
    public ViewHolderPro onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(layout, parent, false);
        ViewHolderPro viewHolderPro = new ViewHolderPro(view);
        return viewHolderPro;
    }

    @Override
    public void onBindViewHolder(ViewHolderPro holder, int position) {
        final Product product = productList.get(position);
        holder.txtName.setText(product.getProduct_name());

        NumberFormat numberFormat = new DecimalFormat("###,###");
        String price = numberFormat.format(product.getPrice()).toString();
        holder.txtPrice.setText(price + " VNĐ");
        holder.txtDiscount.setText("2222");
        holder.cardView.setTag(product.getProduct_id());
        Picasso.with(context).load(product.getBig_image())
                .resize(300, 250)
                .centerInside()
                .placeholder(R.drawable.imgplaceholder)
                .into(holder.imageView, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iProductDetail=new Intent(context, ProductDetailActivity.class);
                //Toast.makeText(context, ""+product.getProduct_id(), Toast.LENGTH_SHORT).show();
                iProductDetail.putExtra("product_id", (Integer) view.getTag());
                iProductDetail.putExtra("product_name",product.getProduct_name());
                context.startActivity(iProductDetail);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolderPro extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtName, txtPrice, txtDiscount;
        CardView cardView;
        public ViewHolderPro(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgTopPhoneAndLaptop);
            txtName = itemView.findViewById(R.id.txtNameProduct);
            txtPrice = itemView.findViewById(R.id.txtPriceProduct);
            txtDiscount = itemView.findViewById(R.id.txtPriceDiscount);
            cardView=itemView.findViewById(R.id.cardViewProduct);
        }
    }
}
