package com.example.dell.myapplication14.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.dell.myapplication14.Model.Object.Evaluate;
import com.example.dell.myapplication14.R;

import java.util.ArrayList;
import java.util.List;

public class EvaluateAdapter extends RecyclerView.Adapter<EvaluateAdapter.ViewHolder> {
    Context context;
    List<Evaluate> list = new ArrayList<>();
    int limit;

    public EvaluateAdapter(Context context, List<Evaluate> list1, int limit) {
        this.context = context;
        this.list = list1;
        this.limit = limit;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_recycle_evaluate_product, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            Evaluate evaluate=list.get(position);
            holder.txtTitle.setText(evaluate.getTitle());
            holder.txtPost.setText("Được đăng bởi: "+evaluate.getDevice_name()+" ngày:  "+evaluate.getEvaluate_date());
            holder.txtContent.setText(evaluate.getContent());
            holder.ratingBar.setRating(evaluate.getNumber_star());
    }

    @Override
    public int getItemCount() {
        int row=0;
        if(list.size()<limit)
        {
            row= list.size();

        }else{
            if(limit!=0)
            {
                row= limit;
            }else{
                row=list.size();
            }
        }
        return row;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtContent, txtPost;
        RatingBar ratingBar;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtContent = itemView.findViewById(R.id.txtContent);
            txtPost = itemView.findViewById(R.id.txtPost);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }
}
