package com.example.lohan.itslife;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lohan.itslife.DB.Task;
import com.example.lohan.itslife.R;
import com.tokenautocomplete.TokenCompleteTextView;

/**
 * Created by lohan on 28-01-2017.
 */

public class ChipView extends TokenCompleteTextView<String> {
    public ChipView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected View getViewForObject(String task) {
        LayoutInflater l = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        LinearLayout view = (LinearLayout) l.inflate(R.layout.chip_layout, (ViewGroup) getParent(), false);
        TextView textView=(TextView)view.findViewById(R.id.name);
        textView.setText(task);
        return view;
    }


    @Override
    protected String defaultObject(String completionText) {
        return null;
    }

}
