package com.example.dell.myapplication14.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.myapplication14.Model.MenuJSON.ParseJsonMenu;
import com.example.dell.myapplication14.Model.Object.Category;
import com.example.dell.myapplication14.R;

import java.util.List;


public class ExpandAdapter extends BaseExpandableListAdapter {
    Context context;
    List<Category> categoryList;

    public ExpandAdapter(Context context, List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;

        ParseJsonMenu parseJsonMenu = new ParseJsonMenu();
        int count = categoryList.size();
        for (int i = 0; i < count; i++) {
            int category_id = categoryList.get(i).getCategory_id();
            categoryList.get(i).setListChild(parseJsonMenu.GetMenuChild(category_id));
        }
    }

    @Override
    public int getGroupCount() {
        return categoryList.size();
    }

    @Override
    public int getChildrenCount(int position_parent) {
        if (categoryList.get(position_parent).getListChild().size() != 0) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public Object getGroup(int position_parent) {
        return categoryList.get(position_parent);
    }

    @Override
    public Object getChild(int position_parent, int position_child) {
        return categoryList.get(position_parent).getListChild().get(position_child);
    }

    @Override
    public long getGroupId(int position_parent) {
        return categoryList.get(position_parent).getCategory_id();
    }

    @Override
    public long getChildId(int position_parent, int position_child) {
        return categoryList.get(position_parent).getListChild().get(position_child).getCategory_id();
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getGroupView(final int position_parent, boolean isExpanded, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=inflater.inflate(R.layout.custom_group_parent_layout,viewGroup,false);
        TextView txtCategoryName = view.findViewById(R.id.txtTenLoai);
        ImageView imageView = view.findViewById(R.id.imgDownUp);
        txtCategoryName.setText(categoryList.get(position_parent).getCategory_name());

        //display icon down and up base on count of listchild
        int count = categoryList.get(position_parent).getListChild().size();
        if (count != 0) {
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }

        //display icon down and up base on expaned event
        if (isExpanded) {
            imageView.setImageResource(R.mipmap.up);
            view.setBackgroundResource(R.color.colorToolBar);
            txtCategoryName.setTextColor(Color.WHITE);
        } else {
            imageView.setImageResource(R.mipmap.down);
        }
        //event click of expandable
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Toast.makeText(context, categoryList.get(position_parent).getCategory_name(), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        return view;
    }

    @Override
    public View getChildView(int position_parent, int position_child, boolean isExpended, View view, ViewGroup viewGroup) {
        //use second expand adapter
      /*  LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ExpandAbleListView expandAbleListView=new ExpandAbleListView(context);
        SecondExpendAdapter secondExpendAdapter=new SecondExpendAdapter(categoryList.get(position_parent).getListChild());
        expandAbleListView.setAdapter(secondExpendAdapter);
        secondExpendAdapter.notifyDataSetChanged();
        return expandAbleListView;*/

        //reuse expand adpater
        ExpandAbleListView expandAbleListView = new ExpandAbleListView(context);
        ExpandAdapter expandAdapter = new ExpandAdapter(context, categoryList.get(position_parent).getListChild());
        expandAbleListView.setAdapter(expandAdapter);
        expandAdapter.notifyDataSetChanged();

        //to lose icon down and up
        expandAbleListView.setGroupIndicator(null);
        return expandAbleListView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    //custom expandable listview
    private class ExpandAbleListView extends ExpandableListView {

        public ExpandAbleListView(Context context) {
            super(context);
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            Display display = windowManager.getDefaultDisplay();
            Point point = new Point();
            display.getSize(point);
            int width = point.x;
            int height = point.y;

            // widthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.AT_MOST);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}

