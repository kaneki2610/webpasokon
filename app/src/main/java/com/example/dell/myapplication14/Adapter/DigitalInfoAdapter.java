package com.example.dell.myapplication14.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dell.myapplication14.Model.Object.DigitalInfo;
import com.example.dell.myapplication14.R;


import java.util.List;

public class DigitalInfoAdapter extends ArrayAdapter<DigitalInfo> {
    Context context;
    int layout;
    List<DigitalInfo> list;

    public DigitalInfoAdapter(@NonNull Context context, int resource, @NonNull List<DigitalInfo> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layout=resource;
        this.list=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=inflater.inflate(this.layout,parent,false);
        DigitalInfo digitalInfo=list.get(position);
        TextView txtName=convertView.findViewById(R.id.txtName);
        TextView txtCT=convertView.findViewById(R.id.txtNameCT);
        txtName.setText(digitalInfo.getName());
        txtCT.setText(digitalInfo.getValue());
        return convertView;
    }
}
