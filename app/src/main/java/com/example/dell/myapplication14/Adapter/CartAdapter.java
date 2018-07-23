package com.example.dell.myapplication14.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication14.Model.Cart.CartModel;
import com.example.dell.myapplication14.Model.Object.Product;
import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.Cart.CartActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    Context context;
    List<Product> list;
    CartModel cartModel;

    public CartAdapter(Context context, List<Product> list) {
        this.context = context;
        this.list = list;
        cartModel = new CartModel();
        cartModel.opnenConnectSQL(context);
    }

    @Override
    public CartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_line_cart_list_layout, parent, false);
        CartHolder cartHolder = new CartHolder(view);
        return cartHolder;
    }

    @Override
    public void onBindViewHolder(final CartHolder holder, final int position) {
        final Product product = list.get(position);
        holder.txtName.setText(product.getProduct_name());
        NumberFormat format = new DecimalFormat("###,###");
        String price = format.format(product.getPrice()).toString();
        holder.txtPrice.setText(price + " VNĐ");
        Bitmap bitmap = BitmapFactory.decodeByteArray(product.getImage(), 0, product.getImage().length);
        holder.imageView.setImageBitmap(bitmap);

        holder.txtDelete.setTag(product.getProduct_id());
        holder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CartModel cartModel = new CartModel();
                cartModel.opnenConnectSQL(context);
                cartModel.deleteCart((Integer) view.getTag());
                list.remove(position);
                notifyDataSetChanged();
                CartActivity.Test();
            }
        });
        holder.txtCount.setText(String.valueOf(product.getQuantity()));
        holder.txtIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(holder.txtCount.getText().toString());
                if (count > 1) {
                    count--;
                }
                holder.txtCount.setText(String.valueOf(count));
                cartModel.updateCart(product.getProduct_id(), count);

                CartActivity.Test();


            }
        });
        holder.txtDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = Integer.parseInt(holder.txtCount.getText().toString());
                int INVENTORYNUMBER = product.getINVENTORYNUMBER();
                if (count < INVENTORYNUMBER) {
                    count++;
                } else {
                    Toast.makeText(context, "Số lượng sản phẩm bạn mua quá số lượng có trong giỏ hảng!", Toast.LENGTH_SHORT).show();
                }


                holder.txtCount.setText(String.valueOf(count));
                cartModel.updateCart(product.getProduct_id(), count);
               // notifyDataSetChanged();
                CartActivity.Test();

            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class CartHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txtName, txtPrice, txtDelete, txtIncrease, txtDecrease, txtCount;


        public CartHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgTopPhoneAndLaptop);
            txtName = itemView.findViewById(R.id.txtNameProduct);
            txtPrice = itemView.findViewById(R.id.txtPriceProduct);
            txtDelete = itemView.findViewById(R.id.txtDelete);
            txtIncrease = itemView.findViewById(R.id.txtGiam);
            txtDecrease = itemView.findViewById(R.id.txtTang);
            txtCount = itemView.findViewById(R.id.txtSLSP);
        }
    }
}
