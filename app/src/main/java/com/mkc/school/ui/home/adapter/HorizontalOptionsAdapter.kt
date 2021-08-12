package com.mkc.school.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.mkc.school.R
import com.mkc.school.data.pojomodel.model.HorizontalOptionsModel

import java.util.ArrayList

class HorizontalOptionsAdapter(
    var activity: FragmentActivity?,
    var horizontalOptionsList: ArrayList<HorizontalOptionsModel>,
    var itemClickListener: OnHorizontalItemClick
) : RecyclerView.Adapter<HorizontalOptionsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_horizontal_options, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.tvIconName.setText(horizontalOptionsList.get(position).optionsName)
        holder.ivIcon.setImageResource(horizontalOptionsList.get(position).optionsIcon!!)

        holder.itemView.setOnClickListener {
            itemClickListener.onHorizontalItemClick(position,horizontalOptionsList.get(position).optionsName)
        }
    }

    override fun getItemCount(): Int {
        return horizontalOptionsList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ivIcon: ImageView = view.findViewById(R.id.ivIcon)
        var tvIconName: TextView = view.findViewById(R.id.tvIconName)

    }

    interface OnHorizontalItemClick {
        fun onHorizontalItemClick(position: Int, action: String?)
    }
}