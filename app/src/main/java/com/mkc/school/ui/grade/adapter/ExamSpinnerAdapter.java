package com.mkc.school.ui.grade.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mkc.school.R;
import com.mkc.school.data.pojomodel.api.response.exam.ExamListResponse;
import com.mkc.school.data.pojomodel.model.ExamModel;

import java.util.ArrayList;

/**
 * Created by avik on 30/4/19.
 */

public class ExamSpinnerAdapter extends BaseAdapter {


    Context context;
    ArrayList<ExamListResponse> itemList;
    LayoutInflater inflter;

    OnSpinnerItemSelectListener mCallback;
    private String selectedItemName, selectedItemId;

    public ExamSpinnerAdapter(Context context, ArrayList<ExamListResponse> itemList, OnSpinnerItemSelectListener mCallback) {
        this.context = context;
        this.itemList = itemList;
        this.mCallback = mCallback;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_layout, null);
        TextView tvSpItemName = (TextView) view.findViewById(R.id.tvSpItemName);

        tvSpItemName.setText(itemList.get(i).getExam_type());

        tvSpItemName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItemName = itemList.get(i).getExam_type();
                selectedItemId = String.valueOf(itemList.get(i).getId());
                mCallback.spinnerSelectedItem(selectedItemName, selectedItemId);
            }
        });
        return view;
    }

    public interface OnSpinnerItemSelectListener {
        public void spinnerSelectedItem(String selectedItemName, String selectedItemId);
    }
}
