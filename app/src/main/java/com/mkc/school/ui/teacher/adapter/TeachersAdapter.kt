package com.mkc.school.ui.teacher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mkc.school.R
import com.mkc.school.data.pojomodel.model.TeachersModel
import java.util.*

class TeachersAdapter(
    var activity: FragmentActivity?,
    var teachersList: ArrayList<TeachersModel>,
    var itemClickListener: OnTeachersItemClick
) : RecyclerView.Adapter<TeachersAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_teachers, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {


//        if(position%2 == 0){
//            holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.colorBlueLite))
//        } else {
//            holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.white))
//        }
    }

    override fun getItemCount(): Int {
        return teachersList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var tvSubject: TextView = view.findViewById(R.id.tvSubject)
//        var tvScore: TextView = view.findViewById(R.id.tvScore)
//        var tvGrade: TextView = view.findViewById(R.id.tvGrade)
//        var ivRemark: ImageView = view.findViewById(R.id.ivRemark)
//        var llMainLayout: LinearLayout = view.findViewById(R.id.llMainLayout)

    }


    interface OnTeachersItemClick {
        fun onTeachersItemClick(position: Int, action: String?)
    }
}