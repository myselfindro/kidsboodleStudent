package com.mkc.school.ui.account

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.request.UpdateHobiesRequest
import com.mkc.school.data.pojomodel.api.response.exam.StudentExamsListResponse
import com.mkc.school.data.pojomodel.api.response.exam.StudentExamsResponse
import com.mkc.school.data.pojomodel.api.response.profile.AccountProfileDataResponse
import com.mkc.school.data.pojomodel.api.response.profile.AccountProfileResponse
import com.mkc.school.data.pojomodel.api.response.profile.HobbiesResponse
import com.mkc.school.data.pojomodel.api.response.profile.UpdateHobiesResponse
import com.mkc.school.databinding.FragmentAccountBinding
import com.mkc.school.ui.account.adapter.StudentExamsAdapter
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.utils.CommonUtils
import com.mkc.school.utils.CommonUtils.getFormatedDate


class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>(),
    AccountNavigator, StudentExamsAdapter.OnExamsItemClick {

    companion object {
        val TAG = AccountFragment::class.java.simpleName
        fun newInstance(): AccountFragment {
            val args = Bundle()
            val fragment = AccountFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var accountViewModel: AccountViewModel? = null
    private var binding: FragmentAccountBinding? = null
    private var layoutManager: LinearLayoutManager? = null
    private var studentExamsAdapter: StudentExamsAdapter? = null
    private var hobiesList: ArrayList<String> = ArrayList<String>()
    private var examsList: ArrayList<StudentExamsListResponse> = ArrayList<StudentExamsListResponse>()
    private var pageSize: String? = "0"
    private var hobbiesAction: String? = ""

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_account
    override val viewModel: AccountViewModel
        get() {
            accountViewModel = ViewModelProvider(
                this,
                ViewModelFactory(requireActivity().application)
            ).get(
                AccountViewModel::class.java
            )
            return accountViewModel as AccountViewModel
        }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accountViewModel!!.navigator = this
        binding = viewDataBinding

        binding?.llStudentDetailsLayout?.visibility = View.INVISIBLE
        initview()

    }

    private fun initview() {

        binding?.rvExam?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        layoutManager!!.setOrientation(LinearLayoutManager.VERTICAL)
        binding?.rvExam?.setLayoutManager(layoutManager)
        studentExamsAdapter = StudentExamsAdapter(activity, examsList, this)
        binding?.rvExam?.setAdapter(studentExamsAdapter)

        binding?.ivAddHobies?.setOnClickListener {

            addHobbyDialog()

        }
        showLoading()
        viewModel.getProfileDetails()
    }

    private fun addHobbyDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.add_hobby_layout)
        val etHobby = dialog.findViewById(R.id.etHobby) as EditText
        val tvAdd = dialog.findViewById(R.id.tvAdd) as TextView
        val tvCancel = dialog.findViewById(R.id.tvCancel) as TextView
        tvAdd.setOnClickListener {

            if (etHobby.text.toString().trim().equals("")){
                etHobby.error = "Please enter your hobby!"
            }
            else{
                hobbiesAction = "ADD"
                var updateHobiesRequest = UpdateHobiesRequest()
                updateHobiesRequest.hobby= etHobby.text.toString().trim()
                viewModel.callAddHobies(updateHobiesRequest)
                dialog.dismiss()
            }

        }
        tvCancel.setOnClickListener { dialog.dismiss() }
        dialog.show()
        val window: Window = dialog.getWindow()!!
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    }

    override fun successAccountProfileResponse(accountProfileResponse: AccountProfileResponse?) {

        if (accountProfileResponse?.request_status == 1) {
            // CommonUtils.showSuccessSnackbar(requireActivity(), binding?.mainLayout!!, accountProfileResponse.msg!!)
            setupUI(accountProfileResponse.result)

        } else {
            CommonUtils.showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                accountProfileResponse?.msg!!
            )
        }
    }

    override fun successStudentExamResponse(studentExamsResponse: StudentExamsResponse?) {
        if (studentExamsResponse?.request_status == 1) {
            if (studentExamsResponse?.result?.size!! >0){
                examsList.clear()
                examsList.addAll(studentExamsResponse.result!!)
                studentExamsAdapter?.notifyDataSetChanged()
            }
        }
    }

    override fun successUpdateHobbiesResponse(updateHobiesResponse: UpdateHobiesResponse?) {
        if (updateHobiesResponse?.request_status == 1) {

            if (hobbiesAction.equals("ADD")){
                CommonUtils.showSuccessSnackbar(
                    requireActivity(),
                    binding?.mainLayout!!,
                    "Successfully Added"
                )
            }
            else if (hobbiesAction.equals("DELETE")){
                CommonUtils.showSuccessSnackbar(
                    requireActivity(),
                    binding?.mainLayout!!,
                    "Successfully Delete"
                )
            }


            viewModel.getProfileDetails()
        }
    }

    override fun errorAccountProfileResponse(throwable: Throwable?) {
        hideLoading()
        if (throwable?.message != null) {
            CommonUtils.showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                "Something went wrong"
            )
        }
    }

    private fun setupUI(result: AccountProfileDataResponse?) {

        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(requireActivity())
            .load(result?.image)
            .apply(options)
            .into(binding?.ivProfileImage!!)


        with(binding) {
            this?.tvName?.setText("Name - " + result?.student_fname + " " + result?.student_lname)
            this?.tvClass?.setText("Class - " + result?.class_name + "-" + result?.section)

            if (result?.gender.equals("M")) {
                this?.tvGender?.setText("Male")
            } else if (result?.gender.equals("F")) {
                this?.tvGender?.setText("Female")
            } else {
                this?.tvGender?.setText("Other")
            }

            this?.tvDob?.setText(getFormatedDate(result?.dob!!))
            this?.tvFatherName?.setText(result?.father_name)
            this?.tvMotherName?.setText(result?.mother_name)
            this?.tvEmailId?.setText(result?.parent_email)
            this?.tvClassTeacher?.setText(result?.class_teacher)
            this?.tvContactNo?.setText("+" + result?.dial_code + "-" + result?.local_guardian_phone)
            this?.tvPermanentAddress?.setText(result?.address1)
            this?.tvSeconderyAddress?.setText(result?.address2)
            this?.tvSchoolName?.setText(result?.school?.get(0)?.school_name)
            this?.tvDateOfJoining?.setText(getFormatedDate(result?.doj!!))
            this?.tvAttendancePercentage?.setText(result?.attendence.toString() + "%")
            this?.pbAttendance?.setProgress(result?.attendence!!)
//            this?.tvAvaragePerfomance?.setText(result?.performance_details?.performance)
//            this?.rbPerformance?.setProgress(result?.performance_details?.star!!)
        }


        for (i in 0 until result?.hobbies!!.size) {
            hobiesList.add(result?.hobbies.get(i).hobby!!)
            setupHobies(result?.hobbies)
        }

        println("hobiesList : " + hobiesList)
//        setupHobies(hobiesList)

        hideLoading()
        binding?.llStudentDetailsLayout?.visibility = View.VISIBLE


        viewModel?.getStudentExams(pageSize!!)
    }

    private fun setupHobies(hobiesList: ArrayList<HobbiesResponse>?) {
        val inflater: LayoutInflater =
            activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewGroup: ViewGroup = requireView().findViewById(R.id.llHobiesLayout)
        viewGroup!!.removeAllViews()
        for (i in 0 until hobiesList?.size!!) {
            val view: ViewGroup =
                inflater.inflate(R.layout.child_hobies_layout, viewGroup, false) as ViewGroup
            val tvHobiesSrNo: TextView = view.findViewById(R.id.tvHobiesSrNo)
            val tvHobies: TextView = view.findViewById(R.id.tvHobies)
            val ivDeleteHobies: ImageView = view.findViewById(R.id.ivDeleteHobies)

            ivDeleteHobies.setOnClickListener {

                for (i in 0 until hobiesList?.size!!){
                    if (tvHobies.text.toString().trim().equals(hobiesList.get(i).hobby)){
                        deleteDialog(hobiesList.get(i).id.toString())
                    }
                }
            }

            tvHobiesSrNo.text = (i + 1).toString() + ". "
            tvHobies.text = hobiesList.get(i).hobby
            view.tag = i
            viewGroup.addView(view, i)
        }
    }

    private fun deleteDialog(hobiesId: String) {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Delete Hobby")
        builder.setMessage("Are you sure you want to delete this hobby?")
       // builder.setIcon(R.drawable.ic_delete)

        builder.setPositiveButton("Yes") { dialog, which ->

            hobbiesAction = "DELETE"
            var updateHobiesRequest = UpdateHobiesRequest()
            updateHobiesRequest.hobby= ""
            viewModel.callDeleteHobies(hobiesId, "delete", updateHobiesRequest)

            dialog.cancel()
           // Toast.makeText(activity,hobiesId,Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { dialog, which ->
        }
        builder.show()
    }

    override fun onExamsItemClick(position: Int, action: String?) {}
}