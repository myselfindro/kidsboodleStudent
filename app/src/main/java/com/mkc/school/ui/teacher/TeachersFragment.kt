package com.mkc.school.ui.teacher

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.teachers.TeachersListResponse
import com.mkc.school.data.pojomodel.api.response.teachers.TeachersResponse
import com.mkc.school.data.pojomodel.model.TeachersModel
import com.mkc.school.databinding.FragmentTeachersBinding
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.teacher.adapter.TeachersAdapter
import com.mkc.school.utils.CommonUtils
import com.mkc.school.utils.CommonUtils.showSuccessSnackbar
import java.util.ArrayList


class TeachersFragment : BaseFragment<FragmentTeachersBinding, TeachersViewModel>(),
    TeachersNavigator, TeachersAdapter.OnTeachersItemClick {


    companion object {
        val TAG = TeachersFragment::class.java.simpleName
        fun newInstance(): TeachersFragment {
            val args = Bundle()
            val fragment = TeachersFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var teachersViewModel: TeachersViewModel? = null
    private var binding: FragmentTeachersBinding? = null
    private var layoutManager: LinearLayoutManager? = null
    private var teachersAdapter: TeachersAdapter? = null
    private var teachersList: ArrayList<TeachersListResponse> = ArrayList<TeachersListResponse>()
    private var pageSize: String? = "0"


    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_teachers
    override val viewModel: TeachersViewModel
        get() {
            teachersViewModel = ViewModelProvider(
                this,
                ViewModelFactory(requireActivity().application)
            ).get(
                TeachersViewModel::class.java
            )
            return teachersViewModel as TeachersViewModel
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        teachersViewModel!!.navigator = this
        binding = viewDataBinding

//        loaddummyData()
        initview()
        showLoading()
        viewModel.getTeachersList(pageSize!!)

    }

    private fun initview() {

        binding?.rvTeacher?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        layoutManager!!.setOrientation(LinearLayoutManager.VERTICAL)
        binding?.rvTeacher?.setLayoutManager(layoutManager)
        teachersAdapter = TeachersAdapter(activity, teachersList, this)
        binding?.rvTeacher?.setAdapter(teachersAdapter)


    }

    override fun onTeachersItemClick(position: Int, action: String?) {}
    override fun successTeachersResponse(teachersResponse: TeachersResponse?) {
        if (teachersResponse?.request_status == 1) {
            hideLoading()
            //showSuccessSnackbar(requireActivity(), binding?.mainLayout!!, teachersResponse.msg!!)
            if (teachersResponse.result?.size!! >0){
                teachersList.clear()
                teachersList.addAll(teachersResponse.result)
                teachersAdapter?.notifyDataSetChanged()
            }
        } else {
            hideLoading()
            CommonUtils.showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                teachersResponse?.msg!!
            )
        }
    }

    override fun errorTeachersResponse(throwable: Throwable?) {
        hideLoading()
        if (throwable?.message != null) {
            CommonUtils.showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                "Something went wrong"
            )
        }
    }

}