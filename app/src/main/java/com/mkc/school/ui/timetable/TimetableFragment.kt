package com.mkc.school.ui.timetable

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkc.school.ui.base.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.model.HorizontalOptionsModel
import com.mkc.school.data.pojomodel.model.TimetableModel
import com.mkc.school.databinding.FragmentTimetableBinding
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.timetable.adapter.TimetableAdapter
import java.util.ArrayList


class TimetableFragment : BaseFragment<FragmentTimetableBinding, TimetableViewModel>(), TimetableNavigator,
    TimetableAdapter.OnTimetableItemClick {


    companion object {
        val TAG = TimetableFragment::class.java.simpleName
        fun newInstance(): TimetableFragment {
            val args = Bundle()
            val fragment = TimetableFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var timetableViewModel: TimetableViewModel? = null
    private var binding: FragmentTimetableBinding? = null
    private var layoutManager: LinearLayoutManager? = null
    private var timetableAdapter: TimetableAdapter? = null
    private var dayWiseClassList: ArrayList<TimetableModel> = ArrayList<TimetableModel>()


        override val bindingVariable: Int
        get() =  BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_timetable
    override val viewModel: TimetableViewModel
        get() {
            timetableViewModel = ViewModelProvider(
                this,
                ViewModelFactory(requireActivity().application)
            ).get(
                TimetableViewModel::class.java
            )
            return timetableViewModel as TimetableViewModel
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        timetableViewModel!!.navigator = this
        binding = viewDataBinding

        loaddummyData()
        initview()

    }

    private fun loaddummyData() {

        dayWiseClassList.add(TimetableModel("Math","Mr. Sujoy Roy","10:00 am","10:45 am",1))
        dayWiseClassList.add(TimetableModel("Eng","Mr. Atanu Mondal","10:00 am","10:45 am",1))
        dayWiseClassList.add(TimetableModel("Beng","Mrs. Sutapa Roy","10:00 am","10:45 am",0))
        dayWiseClassList.add(TimetableModel("Math","Mr. Sujoy Roy","10:00 am","10:45 am",0))
        dayWiseClassList.add(TimetableModel("Math","Mr. Sujoy Roy","10:00 am","10:45 am",1))

    }

    private fun initview() {

        binding?.rvTimetable?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        layoutManager!!.setOrientation(LinearLayoutManager.VERTICAL)
        binding?.rvTimetable?.setLayoutManager(layoutManager)
        timetableAdapter = TimetableAdapter(activity, dayWiseClassList, this)
        binding?.rvTimetable?.setAdapter(timetableAdapter)

        binding?.tabLayoutDays?.addTab(binding?.tabLayoutDays?.newTab()?.setText("Mon")!!)
        binding?.tabLayoutDays?.addTab(binding?.tabLayoutDays?.newTab()?.setText("Tue")!!)
        binding?.tabLayoutDays?.addTab(binding?.tabLayoutDays?.newTab()?.setText("Wed")!!)
        binding?.tabLayoutDays?.addTab(binding?.tabLayoutDays?.newTab()?.setText("Thu")!!)
        binding?.tabLayoutDays?.addTab(binding?.tabLayoutDays?.newTab()?.setText("Fri")!!)
        binding?.tabLayoutDays?.addTab(binding?.tabLayoutDays?.newTab()?.setText("Sat")!!)
        binding?.tabLayoutDays?.setTabGravity(TabLayout.GRAVITY_FILL)

        binding?.tabLayoutDays?.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                Toast.makeText(activity,tab.text,Toast.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    override fun onClick() {}

    override fun onTimetableItemClick(position: Int, action: String?) {}

}