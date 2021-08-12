package com.mkc.school.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mkc.school.R
import com.mkc.school.ui.home.HomeFragment
import java.util.ArrayList

class CallenderEventAdapter(
    var activity: FragmentActivity?,
    var dateWiseCalEventList: ArrayList<String>,
    var itemClickListener: OnCalEventItemClick
) : RecyclerView.Adapter<CallenderEventAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_callender_event, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvDate.setText("Sat 13 : ")
        holder.tvEventName.setText(dateWiseCalEventList.get(position))
    }

    override fun getItemCount(): Int {
        return dateWiseCalEventList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvDate: TextView = view.findViewById(R.id.tvDate)
        var tvEventName: TextView = view.findViewById(R.id.tvEventName)
        var tvView: TextView = view.findViewById(R.id.tvView)

    }


    interface OnCalEventItemClick {
        fun onCalEventItemClick(position: Int, action: String?)
    }
}