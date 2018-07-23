package com.example.dell.myapplication14.View.ProductDetail.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.example.dell.myapplication14.R;
import com.squareup.picasso.Picasso;

public class FragmentProductSlider extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.product_deatail_slider_fragment,container,false);
        Bundle bundle=getArguments();
        String img_link=bundle.getString("image_link");
       // Log.d("image",img_link);
        ImageView imageView=view.findViewById(R.id.imghinhSanPham);
        Picasso.with(getContext()).load(img_link).placeholder(R.drawable.imgplaceholder).into(imageView);
        return view;
    }
}
