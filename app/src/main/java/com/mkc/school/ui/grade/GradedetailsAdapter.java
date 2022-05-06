package com.mkc.school.ui.grade;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mkc.school.R;

import java.util.ArrayList;
import java.util.List;

public class GradedetailsAdapter extends RecyclerView.Adapter<GradedetailsAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private List<SubjectModel> subjectModelList;
    Context ctx;

    public GradedetailsAdapter(Context ctx, List<SubjectModel> subjectModelList){
        this.subjectModelList = subjectModelList;
        this.ctx = ctx;

    }

    @NonNull
    @Override
    public GradedetailsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(ctx).inflate(R.layout.rv_gradedetails, parent, false);
        GradedetailsAdapter.MyViewHolder holder = new GradedetailsAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GradedetailsAdapter.MyViewHolder holder, int position) {

        holder.tvSub.setText(subjectModelList.get(position).getSubname());
        holder.tvMarks.setText(subjectModelList.get(position).getTotalmarks());
        holder.tvGrade.setText(subjectModelList.get(position).getGrade());
        holder.btnShowpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx, Examreview.class);
                intent.putExtra("subjectid", subjectModelList.get(position).getSubid());
                intent.putExtra("examid", subjectModelList.get(position).getExamid());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);



            }
        });

    }

    @Override
    public int getItemCount() {
        return subjectModelList.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvSub, tvMarks,  tvGrade;
        LinearLayout btnShowpaper;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSub = itemView.findViewById(R.id.tvSub);
            tvMarks = itemView.findViewById(R.id.tvMarks);
            tvGrade = itemView.findViewById(R.id.tvGrade);
            btnShowpaper = itemView.findViewById(R.id.btnShowpaper);

        }
    }
}
