package com.example.dell.myapplication14.View.HomePage.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dell.myapplication14.R;

public class FragementElectronic extends Fragment  {
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.product_fragment_layout, container, false);
       // AddControl(view);
      /*  ProductPresenterLogic productPresenterLogic = new ProductPresenterLogic(this);
        productPresenterLogic.getListPhoneAndLapTop();*/
        return view;
    }

   /* private void AddControl(View view) {
        recyclerView = view.findViewById(R.id.recycle_product);
    }
*/
    //@Override
   // public void DisplayListPhoneAndLap(List<Product> list) {
      /*  TopPhoneAdapter topPhoneAdapter = new TopPhoneAdapter(getContext(),list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(topPhoneAdapter);
        topPhoneAdapter.notifyDataSetChanged();*/


}
