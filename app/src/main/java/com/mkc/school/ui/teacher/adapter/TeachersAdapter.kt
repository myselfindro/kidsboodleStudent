package com.mkc.school.ui.teacher.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.teachers.TeachersListResponse
import com.mkc.school.data.pojomodel.model.TeachersModel
import java.util.*

class TeachersAdapter(
    var activity: FragmentActivity?,
    var teachersList: ArrayList<TeachersListResponse>,
    var itemClickListener: OnTeachersItemClick
) : RecyclerView.Adapter<TeachersAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_teachers, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var subjectString : String = ""

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(activity!!)
            .load(teachersList?.get(position).image)
            .apply(options)
            .into(holder?.ivProfImg!!)

        holder.tvTeacherName.setText(teachersList.get(position).first_name+" "+teachersList.get(position).last_name)
        holder.tvEmailId.setText(teachersList.get(position).email)
        holder.tvContactNo.setText(teachersList.get(position).phone)


        for (i in 0 until teachersList.get(position).teacher_subject?.size!!) {

            if (i==0){
                subjectString = teachersList.get(position).teacher_subject?.get(i)?.subject!!
            }
            else{
                subjectString = ", "+teachersList.get(position).teacher_subject?.get(i)?.subject!!
            }

        }

        holder.tvSubject.setText(subjectString)

        holder.ivCollapseExpand.setOnClickListener {
            if (holder.llTeachersDetailsLayout.visibility == View.GONE){
                holder.llTeachersDetailsLayout.visibility= View.VISIBLE
                holder?.ivCollapseExpand?.setImageResource(R.drawable.ic_arrow_up)
            }
            else{
                holder.llTeachersDetailsLayout.visibility= View.GONE
                holder?.ivCollapseExpand?.setImageResource(R.drawable.ic_arrow_down)
            }
        }
        if(position%2 == 0){
            holder.cvMainLayout.setCardBackgroundColor(activity!!.resources.getColor(R.color.colorBlueLite))
        } else {
            holder.cvMainLayout.setCardBackgroundColor(activity!!.resources.getColor(R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return teachersList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivProfImg : ImageView = view.findViewById(R.id.ivProfImg)
        var ivCollapseExpand : ImageView = view.findViewById(R.id.ivCollapseExpand)
        var tvTeacherName: TextView = view.findViewById(R.id.tvTeacherName)
        var tvSubject: TextView = view.findViewById(R.id.tvSubject)
        var tvEmailId: TextView = view.findViewById(R.id.tvEmailId)
        var tvContactNo: TextView = view.findViewById(R.id.tvContactNo)
        var cvMainLayout: CardView = view.findViewById(R.id.cvMainLayout)
        var llTeachersDetailsLayout: LinearLayout = view.findViewById(R.id.llTeachersDetailsLayout)

    }


    interface OnTeachersItemClick {
        fun onTeachersItemClick(position: Int, action: String?)
    }
}