package com.mkc.school.ui.announcement.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.announcement.AnnouncementDataResponse
import com.mkc.school.data.pojomodel.model.AnnouncementModel
import com.mkc.school.ui.announcement.AnnouncementFragment
import com.mkc.school.ui.home.adapter.NoticeAdapter
import com.mkc.school.utils.CommonUtils.getFormatedDate
import java.util.ArrayList

class AnnouncementAdapter(
    var activity: FragmentActivity?,
    var announcementList: ArrayList<AnnouncementDataResponse>,
    var itemClickListener: OnAnnouncementItemClick
) : RecyclerView.Adapter<AnnouncementAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnnouncementAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_my_announcement, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AnnouncementAdapter.MyViewHolder, position: Int) {

//        holder.tvDate.setText(getFormatedDate(announcementList.get(position).date!!))
        holder.tvDate.setText(announcementList.get(position).date)
        holder.tvMessage.setText(announcementList.get(position).title)

        if(position%2 == 0){
            holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.colorBlueLite))
        } else {
            holder.llMainLayout.setBackgroundColor(activity!!.resources.getColor(R.color.white))
        }
    }

    override fun getItemCount(): Int {
        return announcementList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvDate: TextView = view.findViewById(R.id.tvDate)
        var tvMessage: TextView = view.findViewById(R.id.tvMessage)
        var llMainLayout: LinearLayout = view.findViewById(R.id.llMainLayout)

    }


    interface OnAnnouncementItemClick {
        fun onAnnouncementItemClick(position: Int, action: String?)
    }

}