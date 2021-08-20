package com.mkc.school.ui.grade.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.grade.GradeListResponse
import com.mkc.school.data.pojomodel.model.GradeModel
import com.mkc.school.ui.attendance.adapter.AttendanceAdapter
import com.mkc.school.ui.grade.GradeFragment
import java.util.ArrayList

class GradeAdapter(
    var activity: FragmentActivity?,
    var gradeList: ArrayList<GradeListResponse>,
    var itemClickListener: OnGradeItemClick
) : RecyclerView.Adapter<GradeAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_my_grade, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvSubject.setText(gradeList.get(position).exam_details?.get(0)?.subject__subject__subject)
        holder.tvGrade.setText(gradeList.get(position).grade_details?.get(0)?.grade)
        holder.tvScore.setText(gradeList.get(position).total_marks_obtained.toString())

        if(position%2 == 0){
            holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.colorBlueLite))
        } else {
            holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return gradeList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvSubject: TextView = view.findViewById(R.id.tvSubject)
        var tvScore: TextView = view.findViewById(R.id.tvScore)
        var tvGrade: TextView = view.findViewById(R.id.tvGrade)
        var ivRemark: ImageView = view.findViewById(R.id.ivRemark)
        var llMainLayout: LinearLayout = view.findViewById(R.id.llMainLayout)

    }


    interface OnGradeItemClick {
        fun onGradeItemClick(position: Int, action: String?)
    }

}