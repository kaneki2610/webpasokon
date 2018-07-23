package com.example.dell.myapplication14.Custom;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

import com.example.dell.myapplication14.R;


@SuppressLint("AppCompatCustomView")
public class CleanEditText extends EditText {
    Drawable displayX, nodisplayX, drawable;
    Boolean display = false;      //no display X

    public CleanEditText(Context context) {
        super(context);
        Init();
    }

    public CleanEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init();
    }

    public CleanEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CleanEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        Init();
    }

    private void Init() {
        displayX = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();
        nodisplayX = ContextCompat.getDrawable(getContext(), android.R.drawable.screen_background_light_transparent).mutate();

        setInputType(InputType.TYPE_CLASS_TEXT);
        Drawable[] drawables = getCompoundDrawables();
        drawable = display ? displayX : nodisplayX;
        //3 :on,left,right,..
        setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1], drawable, drawables[3]);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN && event.getX() >= (getRight() -drawable.getBounds().width()))
        {
            setText("");
        }
        return super.onTouchEvent(event);
    }

    //event status input edittext
    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (start == 0 && lengthAfter == 0) {
            display = false;
            Init();

        } else {
            display = true;
            Init();
        }
    }
}