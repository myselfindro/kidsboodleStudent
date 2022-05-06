package com.mkc.school.ui.grade;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mkc.school.R;

import java.util.ArrayList;
import java.util.List;

public class GradeAdapter2 extends RecyclerView.Adapter<GradeAdapter2.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<GradeModel> gradeModelArrayList;
    Context ctx;


    public GradeAdapter2(Context ctx, ArrayList<GradeModel> gradeModelArrayList) {
        inflater = LayoutInflater.from(ctx);
        this.gradeModelArrayList = gradeModelArrayList;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public GradeAdapter2.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.rv_grade, parent, false);
        GradeAdapter2.MyViewHolder holder = new GradeAdapter2.MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GradeAdapter2.MyViewHolder holder, int position) {

        holder.tvTesttitle.setText(gradeModelArrayList.get(position).getExam_type_name());
        holder.rv_subject.setLayoutManager(new LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false));
        SubjectAdapter subjectAdapter = new SubjectAdapter(ctx, gradeModelArrayList.get(position).getSubjectModelList());
        holder.rv_subject.setAdapter(subjectAdapter);
        holder.btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ctx, Gradedetails.class);
                intent.putExtra("examid", gradeModelArrayList.get(position).getExamid());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                ctx.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return gradeModelArrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTesttitle, tvSubRemarks;
        Button btnDetails;
        RecyclerView rv_subject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTesttitle = itemView.findViewById(R.id.tvTesttitle);
            rv_subject = itemView.findViewById(R.id.rv_subject);
            btnDetails = itemView.findViewById(R.id.btnDetails);

        }
    }
}
