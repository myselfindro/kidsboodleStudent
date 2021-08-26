package com.mkc.school.ui.announcement

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.announcement.AnnouncementDataResponse
import com.mkc.school.data.pojomodel.api.response.announcement.AnnouncementListResponse
import com.mkc.school.databinding.FragmentAnnouncementBinding
import com.mkc.school.ui.announcement.adapter.AnnouncementAdapter
import com.mkc.school.ui.announcementdetails.AnnouncementDetailsFragment
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.ui.dashboard.DashboardActivity
import com.mkc.school.utils.CommonUtils
import java.util.*


class AnnouncementFragment : BaseFragment<FragmentAnnouncementBinding, AnnouncementViewModel>(),
    AnnouncementNavigator, AnnouncementAdapter.OnAnnouncementItemClick {

    companion object {
        val TAG = AnnouncementFragment::class.java.simpleName
        fun newInstance(): AnnouncementFragment {
            val args = Bundle()
            val fragment = AnnouncementFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var announcementViewModel: AnnouncementViewModel? = null
    private var binding: FragmentAnnouncementBinding? = null
    private var layoutManager: LinearLayoutManager? = null
    private var announcementAdapter: AnnouncementAdapter? = null
    private var announcementList: ArrayList<AnnouncementDataResponse> = ArrayList<AnnouncementDataResponse>()
    private var pageSize: String? = "0"

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_announcement
    override val viewModel: AnnouncementViewModel
        get() {
            announcementViewModel = ViewModelProvider(
                this,
                ViewModelFactory(requireActivity().application)
            ).get(
                AnnouncementViewModel::class.java
            )
            return announcementViewModel as AnnouncementViewModel
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        announcementViewModel!!.navigator = this
        binding = viewDataBinding

        initview()

        showLoading()
        viewModel.getAnnouncementList(pageSize!!)

    }



    private fun initview() {

        binding?.rvAnnouncement?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        layoutManager!!.setOrientation(LinearLayoutManager.VERTICAL)
        binding?.rvAnnouncement?.setLayoutManager(layoutManager)
        announcementAdapter = AnnouncementAdapter(activity, announcementList, this)
        binding?.rvAnnouncement?.setAdapter(announcementAdapter)
    }

    override fun onClick() {}
    override fun successAnnouncementResponse(announcementListResponse: AnnouncementListResponse?) {
        if (announcementListResponse?.request_status == 1) {
               hideLoading()
               if (announcementListResponse.result!!.size>0){
                   announcementList.clear()
                   announcementList.addAll(announcementListResponse.result!!)
                   announcementAdapter?.notifyDataSetChanged()

                   binding?.ivNoDataFound?.visibility = View.GONE
                   binding?.rvAnnouncement?.visibility = View.VISIBLE
               }
            else{
                binding?.ivNoDataFound?.visibility = View.VISIBLE
                binding?.rvAnnouncement?.visibility = View.GONE
               }

        } else {
            hideLoading()
            CommonUtils.showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                announcementListResponse?.msg!!
            )
        }
    }

    override fun errorAnnouncementResponse(throwable: Throwable?) {
        hideLoading()
        if (throwable?.message != null) {
            CommonUtils.showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                "Something went wrong"
            )
        }
    }

    override fun onAnnouncementItemClick(position: Int, action: String?) {

        val bundle = Bundle()
        bundle.putString("DATE", announcementList.get(position).date)
        bundle.putString("TITLE", announcementList.get(position).title)
        bundle.putString("DESCRIPTION", announcementList.get(position).description)
        val announcementDetailsFragment = AnnouncementDetailsFragment()
        announcementDetailsFragment.setArguments(bundle)

        (activity as DashboardActivity?)?.ft = activity?.supportFragmentManager?.beginTransaction()
        (activity as DashboardActivity?)?.ft!!.replace(R.id.fragment_container, announcementDetailsFragment)
        (activity as DashboardActivity?)?.ft!!.commit()
    }

}