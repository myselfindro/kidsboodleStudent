package com.mkc.school.ui.liveclass.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.liveclass.LiveclassResponse
import com.mkc.school.data.pojomodel.api.response.liveclass.Result
import com.mkc.school.data.pojomodel.model.LiveclassModel
import com.mkc.school.ui.announcement.adapter.AnnouncementAdapter

import com.mkc.school.utils.customview.RoundRectCornerImageView
import java.util.ArrayList
//import kotlin.collections.ArrayList

class LiveclassAdapter(
    var activity: FragmentActivity?,
    var liveClassList: ArrayList<Result>,
    var itemClickListener: OnLiveclassItemClick
) : RecyclerView.Adapter<LiveclassAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.layout_liveclass, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        /*val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
*/

        /*Glide.with(activity!!)
            .load(liveClassList.get(position).liveclassImage)
            .apply(options)
            .into(holder.ivClassImage)*/
//        Log.d("LiveclassAdapter", ": " + liveClassList.get(position).routine.subject)
        holder.tvSubject.setText(liveClassList.get(position).routine.subject_name)
        holder.tvDate.setText(liveClassList.get(position).date)
        holder.tvstatus.setText("Completed")

        /*if (liveClassList.get(position).is_live==true){
            holder.tvJoinLiveClass.visibility = View.VISIBLE
        }
        else{
            holder.tvJoinLiveClass.visibility = View.INVISIBLE
        }*/



    }

    override fun getItemCount(): Int {
        Log.d("LiveclassAdapter", ": " + liveClassList.size)
        return liveClassList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var tvSubject: TextView = view.findViewById(R.id.lvsub)
        var tvDate: TextView = view.findViewById(R.id.lvdate)
        var tvstatus: TextView = view.findViewById(R.id.lvstatus)
        //var ivClassImage: RoundRectCornerImageView = view.findViewById(R.id.ivClassImage)

    }


    interface OnLiveclassItemClick {
        fun onLiveclassItemClick(position: Int, action: String?)
    }
}