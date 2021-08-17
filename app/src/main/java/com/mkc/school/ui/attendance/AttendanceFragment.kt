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
import com.mkc.school.data.pojomodel.api.response.attendance.AttendanceResponse
import com.mkc.school.data.pojomodel.model.AttendanceModel
import com.mkc.school.databinding.FragmentAttendanceBinding
import com.mkc.school.ui.attendance.adapter.AttendanceAdapter
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.utils.CommonUtils
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
    private var attendanceList: ArrayList<AttendanceModel> = ArrayList<AttendanceModel>()
    private var balloon: Balloon? = null
    private var closeTooltip: ImageView? = null
    private var tvRemark: TextView? = null
    private var currentYear: Int ?= 2021
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

        loadDummyData()
        initview()

        viewModel.getAttendance(pageSize!!,"",currentMonth.toString(),currentYear.toString())

    }

    private fun loadDummyData() {
        attendanceList.add(AttendanceModel("02.08.2021", "Monday", 1, "Remarks"))
        attendanceList.add(AttendanceModel("03.08.2021", "Thusday", 0, "Remarks"))
        attendanceList.add(AttendanceModel("04.08.2021", "Wednesday", 1, "Remarks"))
        attendanceList.add(AttendanceModel("05.08.2021", "Thrusday", 1, "Remarks"))
        attendanceList.add(AttendanceModel("06.08.2021", "Friday", 0, "Remarks"))

    }

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
        currentMonth = d.month
        currentMonth = currentMonth!! +1

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

    override fun onAttendanceItemClick(position: Int, view: View, action: String?) {
        if (action.equals("REMARK")) {
            tvRemark?.setText("ajdhajkhnwhjhkjhoihihkhkhs")
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
                        binding?.selectMonth?.setText(getMonthName(selectedMonth+1))
                    }, 3, 5
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

    private fun getMonthName(selectedMonth: Int): String {
        var monthName : String= ""
        when (selectedMonth) {
            1 -> {
                monthName="January"
            }
            2 -> {
                monthName="February"
            }
            3 -> {
                monthName="March"
            }
            4 -> {
                monthName="April"
            }
            5 -> {
                monthName="May"
            }
            6 -> {
                monthName="June"
            }
            7 -> {
                monthName="July"
            }
            8 -> {
                monthName="August"
            }
            9 -> {
                monthName="Septembar"
            }
            10 -> {
                monthName="Octobar"
            }
            11 -> {
                monthName="Novembar"
            }
            12 -> {
                monthName="Decembar"
            }
        }

        return monthName
    }

    override fun successAttendanceResponse(attendanceResponse: AttendanceResponse?) {
        if (attendanceResponse?.request_status == 1) {
            showSuccessSnackbar(requireActivity(), binding?.mainLayout!!, attendanceResponse.msg!!)

            if (attendanceResponse.result?.size!! >0){

            }else{
                showErrorSnackbar(requireActivity(), binding?.mainLayout!!, attendanceResponse.msg!!)
            }
//            announcementList.addAll(announcementListResponse.result!!)
//            announcementAdapter?.notifyDataSetChanged()

        } else {showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                attendanceResponse?.msg!!
            )
        }
    }

    override fun errorAttendanceResponse(throwable: Throwable?) {
        if (throwable?.message != null) {
            CommonUtils.showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                "Something went wrong"
            )
        }
    }

}