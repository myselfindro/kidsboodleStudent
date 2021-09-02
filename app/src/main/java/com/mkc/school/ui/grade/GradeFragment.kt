package com.mkc.school.ui.grade

import android.os.Bundle
import android.view.View
import android.widget.SpinnerAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.exam.ExamListResponse
import com.mkc.school.data.pojomodel.api.response.exam.ExamResponse
import com.mkc.school.data.pojomodel.api.response.grade.GradeListResponse
import com.mkc.school.data.pojomodel.api.response.grade.GradeResponse
import com.mkc.school.data.pojomodel.model.AttendanceModel
import com.mkc.school.data.pojomodel.model.ExamModel
import com.mkc.school.data.pojomodel.model.GradeModel
import com.mkc.school.databinding.FragmentGradeBinding
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.grade.adapter.ExamSpinnerAdapter
import com.mkc.school.ui.grade.adapter.GradeAdapter
import com.mkc.school.utils.CommonUtils
import com.mkc.school.utils.CommonUtils.getFormatedDateWithMonthName
import java.util.ArrayList


class GradeFragment : BaseFragment<FragmentGradeBinding, GradeViewModel>(),
    GradeNavigator, GradeAdapter.OnGradeItemClick, ExamSpinnerAdapter.OnSpinnerItemSelectListener {

    companion object {
        val TAG = GradeFragment::class.java.simpleName
        fun newInstance(): GradeFragment {
            val args = Bundle()
            val fragment = GradeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var gradeViewModel: GradeViewModel? = null
    private var binding: FragmentGradeBinding? = null
    private var layoutManager: LinearLayoutManager? = null
    private var gradeAdapter: GradeAdapter? = null
    private var gradeList: ArrayList<GradeListResponse> = ArrayList<GradeListResponse>()
    private var examList: ArrayList<ExamListResponse> = ArrayList<ExamListResponse>()
    private var examSpAdapter: ExamSpinnerAdapter? = null
    private var pageSize: String? = "0"



    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() =  R.layout.fragment_grade
    override val viewModel: GradeViewModel
        get() {
            gradeViewModel = ViewModelProvider(
                this,
                ViewModelFactory(requireActivity().application)
            ).get(
                GradeViewModel::class.java
            )
            return gradeViewModel as GradeViewModel
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gradeViewModel!!.navigator = this
        binding = viewDataBinding

        //loaddummyData()
        initview()
        showLoading()
        viewModel.getExamList()
    }


    private fun initview() {

        binding?.rvGrade?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        layoutManager!!.setOrientation(LinearLayoutManager.VERTICAL)
        binding?.rvGrade?.setLayoutManager(layoutManager)
        gradeAdapter = GradeAdapter(activity, gradeList, this)
        binding?.rvGrade?.setAdapter(gradeAdapter)


        examSpAdapter = ExamSpinnerAdapter(activity, examList, this)
        binding?.spExam?.setAdapter(examSpAdapter)

        binding?.rlExamSelector?.setOnClickListener {
            binding?.spExam?.performClick()
        }

    }

    override fun onGradeItemClick(position: Int, action: String?) {}

    override fun spinnerSelectedItem(selectedItemName: String?, selectedItemId: String?) {
        //Toast.makeText(activity,selectedItemName,Toast.LENGTH_SHORT).show()
        binding?.tvSelectExam?.setText(selectedItemName)
        binding?.spExam?.onDetachedFromWindow()

        viewModel.getGradeList(pageSize!!, selectedItemId.toString())
    }

    override fun successGradeResponse(gradeResponse: GradeResponse?) {
        if (gradeResponse?.request_status == 1) {
            //CommonUtils.showSuccessSnackbar(requireActivity(), binding?.mainLayout!!, gradeResponse.msg!!)
                hideLoading()
            if (gradeResponse.result?.size!! >0){
                gradeList.clear()
                gradeList.addAll(gradeResponse.result)
                gradeAdapter?.notifyDataSetChanged()

                binding?.cvGradeLayout?.visibility = View.VISIBLE
                binding?.ivNoDataFound?.visibility = View.GONE
            }
            else{
                binding?.cvGradeLayout?.visibility = View.GONE
                binding?.ivNoDataFound?.visibility = View.VISIBLE
            }
        } else {
            CommonUtils.showErrorSnackbar(requireActivity(), binding?.mainLayout!!, gradeResponse?.msg!!)
        }
    }

    override fun successExamResponse(examResponse: ExamResponse?) {
        if (examResponse?.request_status == 1) {
            hideLoading()
            //CommonUtils.showSuccessSnackbar(requireActivity(), binding?.mainLayout!!, examResponse.msg!!)
            if (examResponse.result?.size!! >0){
                examList.clear()
                examList.addAll(examResponse.result)
                examSpAdapter?.notifyDataSetChanged()

                binding?.tvSelectExam?.setText((examList.get(0).exam_type)+" - "+
                        getFormatedDateWithMonthName(examList.get(0).start_date!!)+" to "+
                        getFormatedDateWithMonthName(examList.get(0).end_date!!))
                viewModel.getGradeList(pageSize!!, examList.get(0).id.toString())
            }
        } else {
            CommonUtils.showErrorSnackbar(requireActivity(), binding?.mainLayout!!, examResponse?.msg!!)
        }
    }

    override fun errorGradeResponse(throwable: Throwable?) {
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