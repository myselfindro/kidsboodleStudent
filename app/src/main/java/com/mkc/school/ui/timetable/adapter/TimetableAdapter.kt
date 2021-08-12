package com.mkc.school.ui.timetable.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mkc.school.R
import com.mkc.school.data.pojomodel.model.TimetableModel
import java.util.ArrayList

class TimetableAdapter(
    var activity: FragmentActivity?,
    var dayWiseClassList: ArrayList<TimetableModel>,
    var itemClickListener: OnTimetableItemClick
) : RecyclerView.Adapter<TimetableAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_timetable, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvSubject.setText(dayWiseClassList.get(position).subject)
        holder.tvTeacher.setText(dayWiseClassList.get(position).teacherName)
        holder.tvStartTime.setText("StartTime: "+dayWiseClassList.get(position).startTime)
        holder.tvEndTime.setText("EndTime: "+dayWiseClassList.get(position).endTime)

        if (dayWiseClassList.get(position).isLiveClass==1){
            holder.tvJoinLiveClass.visibility = View.VISIBLE
        }
        else{
            holder.tvJoinLiveClass.visibility = View.INVISIBLE
        }

        if(position%2 == 0){
            holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.colorBlueLite))
        } else {
            holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return dayWiseClassList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvSubject: TextView = view.findViewById(R.id.tvSubject)
        var tvTeacher: TextView = view.findViewById(R.id.tvTeacher)
        var tvStartTime: TextView = view.findViewById(R.id.tvStartTime)
        var tvEndTime: TextView = view.findViewById(R.id.tvEndTime)
        var tvJoinLiveClass: TextView = view.findViewById(R.id.tvJoinLiveClass)
        var llMainLayout: LinearLayout = view.findViewById(R.id.llMainLayout)

    }


    interface OnTimetableItemClick {
        fun onTimetableItemClick(position: Int, action: String?)
    }
}