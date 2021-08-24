package com.mkc.school.ui.account.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.exam.StudentExamsListResponse
import com.mkc.school.ui.account.AccountFragment
import com.mkc.school.ui.grade.adapter.GradeAdapter
import java.util.ArrayList

class StudentExamsAdapter(
    var activity: FragmentActivity?,
    var examsList: ArrayList<StudentExamsListResponse>,
    var itemClickListener: OnExamsItemClick
) : RecyclerView.Adapter<StudentExamsAdapter.MyViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_student_exams, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvExamName.setText((position+1).toString()+". "+examsList?.get(position)?.title)
      holder.tvExamMarks.setText("Marks- "+examsList.get(position).student_subject_marks?.total_marks_obtained.toString())

//        if(position%2 == 0){
//            holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.colorBlueLite))
//        } else {
//            holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.white))
//        }
    }

    override fun getItemCount(): Int {
        return examsList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvExamName: TextView = view.findViewById(R.id.tvExamName)
        var tvExamMarks: TextView = view.findViewById(R.id.tvExamMarks)
        var tvViewResult: TextView = view.findViewById(R.id.tvViewResult)
        var llMainLayout: LinearLayout = view.findViewById(R.id.llMainLayout)

    }


    interface OnExamsItemClick {
        fun onExamsItemClick(position: Int, action: String?)
    }
}