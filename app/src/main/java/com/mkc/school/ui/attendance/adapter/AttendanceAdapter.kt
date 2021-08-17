package com.mkc.school.ui.attendance.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.attendance.AttendanceDetails
import com.mkc.school.data.pojomodel.api.response.attendance.AttendanceListResponse
import com.mkc.school.data.pojomodel.model.AttendanceModel
import com.mkc.school.ui.attendance.AttendanceFragment
import com.mkc.school.ui.home.adapter.NoticeAdapter
import java.util.ArrayList

class AttendanceAdapter(
    var activity: FragmentActivity?,
    var attendanceList: ArrayList<AttendanceDetails>,
    var itemClickListener: OnAttendanceItemClick
) : RecyclerView.Adapter<AttendanceAdapter.MyViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_my_attendance, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvDate.setText(attendanceList.get(position).date)
        holder.tvDay.setText(attendanceList.get(position).day_name)
        if (attendanceList.get(position).present_status.equals("present")){
             holder.tvAttendance.setText("Present")
            holder.tvAttendance.setTextColor(activity?.resources?.getColor(R.color.colorGreen)!!)
        }
        else{
            holder.tvAttendance.setText("Absent")
            holder.tvAttendance.setTextColor(activity?.resources?.getColor(R.color.colorTextRed)!!)
        }

        holder.ivRemark.setOnClickListener {

            itemClickListener.onAttendanceItemClick(position,holder.ivRemark, attendanceList.get(position).teacher_remarks!!,"REMARK")
        }

        if(position%2 == 0){
           holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.colorBlueLite))
        } else {
            holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return attendanceList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvDate: TextView = view.findViewById(R.id.tvDate)
        var tvDay: TextView = view.findViewById(R.id.tvDay)
        var tvAttendance: TextView = view.findViewById(R.id.tvAttendance)
        var ivRemark: ImageView = view.findViewById(R.id.ivRemark)
        var llMainLayout: LinearLayout = view.findViewById(R.id.llMainLayout)

    }


    interface OnAttendanceItemClick {
        fun onAttendanceItemClick(position: Int, view: View, remarks: String, action: String?)
    }
}