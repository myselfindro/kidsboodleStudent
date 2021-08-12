package com.mkc.school.utils.customview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

/**
 * Created by avik on 4/12/17.
 */

public class CustomSpinner extends Spinner {

    Context context=null;

    public CustomSpinner(Context context) {
        super(context);
        this.context=context;
    }

    public CustomSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public CustomSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }
}
