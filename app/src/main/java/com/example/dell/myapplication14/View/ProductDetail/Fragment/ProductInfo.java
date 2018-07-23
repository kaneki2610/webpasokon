package com.example.dell.myapplication14.View.ProductDetail.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.myapplication14.R;
import com.example.dell.myapplication14.View.ProductDetail.ProductDetailActivity;


public class ProductInfo extends Fragment {

    LinearLayout linearLayout;
    TextView txt;
    ImageView img;
    boolean checkExpand = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_comment_fragment_layout, container, false);
        AddControl(view);

        final ProductDetailActivity p = (ProductDetailActivity) getActivity();
        Log.d("info", p.getDescrption());
        txt.setText(p.getDescrption().substring(0, 80));
        if (p.getDescrption().length() < 80) {
            linearLayout.setVisibility(View.GONE);
        } else {
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    checkExpand = !checkExpand;
                    if (checkExpand) {
                        txt.setText(p.getDescrption());
                        img.setVisibility(View.VISIBLE);
                        linearLayout.setVisibility(View.GONE);
                        img.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(p.getDescrption().length()>80)
                                {
                                    img.setVisibility(View.GONE);
                                    linearLayout.setVisibility(View.VISIBLE);
                                    txt.setText(p.getDescrption().substring(0, 80));
                                }else{
                                    linearLayout.setVisibility(View.GONE);
                                }
                            }
                        });

                    } else {
                        txt.setText(p.getDescrption().substring(0, 80));
                        img.setVisibility(View.GONE);
                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }
            });
        }


        return view;
    }

    private void AddControl(View view) {
        linearLayout = view.findViewById(R.id.linearReadMore);
        txt = view.findViewById(R.id.txtDescrptionSPCT);
        img=view.findViewById(R.id.imgLessMore);
    }
}
