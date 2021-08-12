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
import com.mkc.school.data.pojomodel.model.AttendanceModel
import com.mkc.school.data.pojomodel.model.ExamModel
import com.mkc.school.data.pojomodel.model.GradeModel
import com.mkc.school.databinding.FragmentGradeBinding
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.grade.adapter.ExamSpinnerAdapter
import com.mkc.school.ui.grade.adapter.GradeAdapter
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
    private var gradeList: ArrayList<GradeModel> = ArrayList<GradeModel>()
    private var examList: ArrayList<ExamModel> = ArrayList<ExamModel>()
    private var examSpAdapter: ExamSpinnerAdapter? = null



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

        loaddummyData()
        initview()

    }

    private fun loaddummyData() {

        gradeList.add(GradeModel("Math", 90, "A+"))
        gradeList.add(GradeModel("Eng", 70, "A+"))
        gradeList.add(GradeModel("Hindi", 60, "B+"))

        examList.add(ExamModel("Half yearly Exam 1", 1))
        examList.add(ExamModel("Half yearly Exam 2", 2))
        examList.add(ExamModel("Half yearly Exam 3", 3))
        examList.add(ExamModel("Half yearly Exam 4", 4))

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

    override fun onClick() {}
    override fun onGradeItemClick(position: Int, action: String?) {}
    override fun spinnerSelectedItem(selectedItemName: String?, selectedItemId: String?) {
        Toast.makeText(activity,selectedItemName,Toast.LENGTH_SHORT).show()
        binding?.spExam?.onDetachedFromWindow()
    }

}