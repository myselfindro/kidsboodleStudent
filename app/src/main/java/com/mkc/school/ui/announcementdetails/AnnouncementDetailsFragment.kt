package com.mkc.school.ui.announcementdetails

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.databinding.FragmentAnnouncementDetailsBinding
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.utils.CommonUtils


class AnnouncementDetailsFragment : BaseFragment<FragmentAnnouncementDetailsBinding, AnnouncementDetailsViewModel>(),
    AnnouncementDetailsNavigator {

    companion object {
        val TAG = AnnouncementDetailsFragment::class.java.simpleName
        fun newInstance(): AnnouncementDetailsFragment {
            val args = Bundle()
            val fragment = AnnouncementDetailsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var announcementDetailsViewModel: AnnouncementDetailsViewModel? = null
    private var binding: FragmentAnnouncementDetailsBinding? = null
    private var announcementDate : String = ""
    private var announcementTitle : String = ""
    private var announcementDescription : String = ""


    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_announcement_details
    override val viewModel: AnnouncementDetailsViewModel
        get() {
            announcementDetailsViewModel = ViewModelProvider(
                this,
                ViewModelFactory(requireActivity().application)
            ).get(
                AnnouncementDetailsViewModel::class.java
            )
            return announcementDetailsViewModel as AnnouncementDetailsViewModel
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        announcementDetailsViewModel!!.navigator = this
        binding = viewDataBinding

       announcementDate = requireArguments().getString("DATE")!!
       announcementTitle = requireArguments().getString("TITLE")!!
       announcementDescription = requireArguments().getString("DESCRIPTION")!!

        println("data : --Date--> "+announcementDate)
        println("data : --Title--> "+announcementTitle)
        println("data : --Description--> "+announcementDescription)

        setupDate()
    }

    private fun setupDate() {
        binding?.tvDate?.setText(CommonUtils.getFormatedDate(announcementDate))
        binding?.tvTittle?.setText(announcementTitle)
        binding?.tvDescription?.setText(announcementDescription)
    }
}