package com.mkc.school.ui.attendance

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.attendance.AttendanceDetails
import com.mkc.school.data.pojomodel.api.response.attendance.AttendanceListResponse
import com.mkc.school.data.pojomodel.api.response.attendance.AttendanceResponse
import com.mkc.school.data.pojomodel.model.AttendanceModel
import com.mkc.school.databinding.FragmentAttendanceBinding
import com.mkc.school.ui.attendance.adapter.AttendanceAdapter
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.utils.CommonUtils
import com.mkc.school.utils.CommonUtils.getMonthName
import com.mkc.school.utils.CommonUtils.showErrorSnackbar
import com.mkc.school.utils.CommonUtils.showSuccessSnackbar
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.whiteelephant.monthpicker.MonthPickerDialog
import java.util.*


class AttendanceFragment : BaseFragment<FragmentAttendanceBinding, AttendanceViewModel>(),
    AttendanceNavigator, AttendanceAdapter.OnAttendanceItemClick, View.OnClickListener {

    companion object {
        val TAG = AttendanceFragment::class.java.simpleName
        fun newInstance(): AttendanceFragment {
            val args = Bundle()
            val fragment = AttendanceFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var attendanceViewModel: AttendanceViewModel? = null
    private var binding: FragmentAttendanceBinding? = null
    private var layoutManager: LinearLayoutManager? = null
    private var attendanceAdapter: AttendanceAdapter? = null
    private var attendanceList: ArrayList<AttendanceListResponse> = ArrayList<AttendanceListResponse>()
    private var balloon: Balloon? = null
    private var closeTooltip: ImageView? = null
    private var tvRemark: TextView? = null
    private var currentYear: Int ?= 2022
    private var curMon: Int ?= null
    private var currentMonth: Int ?= null
    private var pageSize: String? = "0"

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_attendance
    override val viewModel: AttendanceViewModel
        get() {
            attendanceViewModel = ViewModelProvider(
                this,
                ViewModelFactory(requireActivity().application)
            ).get(
                AttendanceViewModel::class.java
            )
            return attendanceViewModel as AttendanceViewModel
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attendanceViewModel!!.navigator = this
        binding = viewDataBinding

        initview()

        //showLoading()
        viewModel.getAttendance(pageSize!!,"",currentMonth.toString(),currentYear.toString())

    }

/*    private fun loadDummyData() {
        attendanceList.add(AttendanceModel("02.08.2021", "Monday", 1, "Remarks"))
        attendanceList.add(AttendanceModel("03.08.2021", "Thusday", 0, "Remarks"))
        attendanceList.add(AttendanceModel("04.08.2021", "Wednesday", 1, "Remarks"))
        attendanceList.add(AttendanceModel("05.08.2021", "Thrusday", 1, "Remarks"))
        attendanceList.add(AttendanceModel("06.08.2021", "Friday", 0, "Remarks"))

    }*/

    private fun initview() {

        binding?.rvAttendance?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        layoutManager!!.setOrientation(LinearLayoutManager.VERTICAL)
        binding?.rvAttendance?.setLayoutManager(layoutManager)
        attendanceAdapter = AttendanceAdapter(activity, attendanceList, this)
        binding?.rvAttendance?.setAdapter(attendanceAdapter)

        binding?.selectMonth?.setOnClickListener(this)
        binding?.selectYear?.setOnClickListener(this)

        val d = Date()
        curMon = d.month
        currentMonth = curMon!! +1
        binding?.selectMonth?.setText("Month : "+getMonthName(currentMonth!!))

        balloon = Balloon.Builder(requireActivity())
            .setLayout(R.layout.layout_custom_tooltip)
            .setArrowSize(0)
            .setMarginRight(16)
            //.setArrowOrientation(ArrowOrientation.TOP)
            .setArrowPosition(0.5f)
            .setWidthRatio(0.55f)
            //.setHeight(250)
            .setCornerRadius(9f)
            .setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.transparent))
            .setBalloonAnimation(BalloonAnimation.CIRCULAR)
            //.setLifecycleOwner(lifecycleOwner)
            .build()

        closeTooltip = balloon!!.getContentView().findViewById(R.id.ivCloseTooltip)
        tvRemark = balloon!!.getContentView().findViewById(R.id.tvRemark)
        closeTooltip!!.setOnClickListener {
            balloon!!.dismiss()
        }
    }

    override fun onAttendanceItemClick(position: Int, view: View, remarks: String, action: String?) {
        if (action.equals("REMARK")) {
            tvRemark?.setText(remarks)
            balloon?.showAlignBottom(view)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.selectMonth -> {
                val builder = MonthPickerDialog.Builder(
                    activity,
                    { selectedMonth, selectedYear ->
                        println("selected__MONTH : " + selectedMonth)
                        currentMonth = selectedMonth+1
                        binding?.selectMonth?.setText("Month : "+getMonthName(selectedMonth+1))

                        viewModel.getAttendance(pageSize!!,"",currentMonth.toString(),currentYear.toString())
                    }, 3, curMon!!
                )

                builder.showMonthOnly()
                    .build()
                    .show()
            }

            R.id.selectYear -> {
                val builder = MonthPickerDialog.Builder(
                    activity,
                    { selectedMonth, selectedYear ->
                        println("selected__YEAR : " + selectedYear)
                        binding?.selectYear?.setText(selectedYear.toString())
                    }, currentYear!!, 0
                )

                builder.showYearOnly()
                    .setYearRange(1990, 2030)
                    .build()
                    .show()
            }
        }
    }


    override fun successAttendanceResponse(attendanceResponse: AttendanceResponse?) {
        if (attendanceResponse?.request_status == 1) {
            //showSuccessSnackbar(requireActivity(), binding?.mainLayout!!, attendanceResponse.msg!!)
                hideLoading()
            if (attendanceResponse.result?.size!! >0){
                if (attendanceResponse.result?.get(0)?.attendence_details?.size!! >0){
                    attendanceList.clear()
                    attendanceList.addAll(attendanceResponse.result)
                    attendanceAdapter?.notifyDataSetChanged()

                    binding?.ivNoDataFound?.visibility= View.GONE
                    binding?.cvAttendacneCard?.visibility= View.VISIBLE
                }
                else{
                    binding?.ivNoDataFound?.visibility= View.VISIBLE
                    binding?.cvAttendacneCard?.visibility= View.GONE
                    //showErrorSnackbar(requireActivity(), binding?.mainLayout!!, attendanceResponse.msg!!)
                }
            }
            else{
                binding?.ivNoDataFound?.visibility= View.VISIBLE
                binding?.cvAttendacneCard?.visibility= View.GONE
            }



        } else {
            hideLoading()
            showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                attendanceResponse?.msg!!
            )
        }
    }

    override fun errorAttendanceResponse(throwable: Throwable?) {
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