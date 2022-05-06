package com.mkc.school.ui.grade;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkc.school.R;

import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private List<SubjectModel> subjectModelList;
    Context ctx;

    public SubjectAdapter(Context ctx, List<SubjectModel> subjectModelList){
        this.subjectModelList = subjectModelList;
        this.ctx = ctx;

    }

    @NonNull
    @Override
    public SubjectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(ctx).inflate(R.layout.rv_subjectlist, parent, false);
        SubjectAdapter.MyViewHolder holder = new SubjectAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectAdapter.MyViewHolder holder, int position) {

        holder.tvSubRemark.setText(subjectModelList.get(position).getSubname()+ " - "
                +subjectModelList.get(position).getGrade());

    }

    @Override
    public int getItemCount() {
        return subjectModelList.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvSubRemark;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSubRemark = itemView.findViewById(R.id.tvSubRemark);
        }
    }
}
