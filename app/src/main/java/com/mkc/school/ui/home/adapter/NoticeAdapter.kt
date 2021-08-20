package com.mkc.school.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.home.AnnouncementList
import com.mkc.school.ui.home.HomeFragment
import java.util.ArrayList

class NoticeAdapter(
    var activity: FragmentActivity?,
    var myNoticeList: ArrayList<AnnouncementList>,
    var itemClickListener: OnNoticeItemClick
) : RecyclerView.Adapter<NoticeAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_my_notice, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvNotice.setText(" • "+myNoticeList.get(position).title)
    }

    override fun getItemCount(): Int {
        return myNoticeList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvNotice: TextView = view.findViewById(R.id.tvNotice)

    }


    interface OnNoticeItemClick {
        fun onNoticeItemClick(position: Int, action: String?)
    }
}