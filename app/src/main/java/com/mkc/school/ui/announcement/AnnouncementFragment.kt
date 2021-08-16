package com.mkc.school.ui.announcement

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.announcement.AnnouncementDataResponse
import com.mkc.school.data.pojomodel.api.response.announcement.AnnouncementListResponse
import com.mkc.school.data.pojomodel.model.AnnouncementModel
import com.mkc.school.databinding.FragmentAnnouncementBinding
import com.mkc.school.ui.announcement.adapter.AnnouncementAdapter
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.base.ViewModelFactory
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

        loadDummyData()
        initview()

        viewModel.getAnnouncementList(pageSize!!)

    }

    private fun loadDummyData() {

//        announcementList.add(
//            AnnouncementModel(
//                "25.06.2021",
//                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout."
//            )
//        )
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
            CommonUtils.showSuccessSnackbar(requireActivity(), binding?.mainLayout!!, announcementListResponse.msg!!)

            announcementList.addAll(announcementListResponse.result!!)
            announcementAdapter?.notifyDataSetChanged()

        } else {
            CommonUtils.showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                announcementListResponse?.msg!!
            )
        }
    }

    override fun errorAnnouncementResponse(throwable: Throwable?) {
        if (throwable?.message != null) {
            CommonUtils.showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                "Something went wrong"
            )
        }
    }

    override fun onAnnouncementItemClick(position: Int, action: String?) {
    }

}