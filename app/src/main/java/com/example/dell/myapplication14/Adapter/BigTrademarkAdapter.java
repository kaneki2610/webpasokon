package com.example.dell.myapplication14.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication14.Model.Object.Trademark;
import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.Product.ProductActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BigTrademarkAdapter extends RecyclerView.Adapter<BigTrademarkAdapter.ViewHolder> {
    Context context;
    List<Trademark> list;
    public BigTrademarkAdapter(Context context,List<Trademark>list)
    {
        this.context=context;
        this.list=list;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycleview_bigtrademark, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Trademark trademark=list.get(position);
        holder.textView.setText(trademark.getTRADEMARK_NAME());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iProduct=new Intent(context, ProductActivity.class);
                iProduct.putExtra("trademark_id",trademark.getTRADEMARK_ID());
                iProduct.putExtra("trademark_name",trademark.getTRADEMARK_NAME());
                context.startActivity(iProduct);
               // Toast.makeText(context, trademark.getTRADEMARK_ID()+"--"+trademark.getTRADEMARK_NAME(), Toast.LENGTH_SHORT).show();
            }
        });
        Picasso.with(context).load(trademark.getImage())
                .resize(180,90).centerInside()
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
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView imageView;
        ProgressBar progressBar;
        CardView linearLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.txtTrademarkName);
            imageView=itemView.findViewById(R.id.imgTrademark);
            progressBar=itemView.findViewById(R.id.progress_bar_hinh);
            linearLayout=itemView.findViewById(R.id.linearLayoutBigTrademark);
        }
    }

}
