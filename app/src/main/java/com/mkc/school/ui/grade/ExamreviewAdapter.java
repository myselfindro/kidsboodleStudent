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

import java.util.List;

public class ExamreviewAdapter extends RecyclerView.Adapter<ExamreviewAdapter.MyViewHolder>{

    private LayoutInflater inflater;
    private List<ExamreviewModel> examreviewModelList;
    Context ctx;

    public ExamreviewAdapter(Context ctx, List<ExamreviewModel> examreviewModelList){
        this.examreviewModelList = examreviewModelList;
        this.ctx = ctx;

    }

    @NonNull
    @Override
    public ExamreviewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.from(ctx).inflate(R.layout.rv_examreviewlist, parent, false);
        ExamreviewAdapter.MyViewHolder holder = new ExamreviewAdapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExamreviewAdapter.MyViewHolder holder, int position) {

        holder.tvAnswer.setText("A : "+examreviewModelList.get(position).getAnswer());
        holder.tvQuestion.setText("Q : "+examreviewModelList.get(position).getQuestion());
        holder.btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((Examreview) ctx).viewcomment(examreviewModelList.get(position));



            }
        });

    }

    @Override
    public int getItemCount() {
        return examreviewModelList.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvQuestion, tvAnswer;
        LinearLayout btnComment;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvAnswer = itemView.findViewById(R.id.tvAnswer);
            btnComment = itemView.findViewById(R.id.btnComment);

        }
    }
}
