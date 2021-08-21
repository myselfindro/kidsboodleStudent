package com.mkc.school.ui.account

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.profile.AccountProfileDataResponse
import com.mkc.school.data.pojomodel.api.response.profile.AccountProfileResponse
import com.mkc.school.databinding.FragmentAccountBinding
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.utils.CommonUtils
import com.mkc.school.utils.CommonUtils.getFormatedDate
import java.util.*


class AccountFragment : BaseFragment<FragmentAccountBinding, AccountViewModel>(),
    AccountNavigator {

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
    private var hobiesList: ArrayList<String> = ArrayList<String>()

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
        showLoading()
        viewModel.getProfileDetails()
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
            this?.tvDateOfJoining?.setText(result?.school?.get(0)?.school_name)
            this?.tvAttendancePercentage?.setText(result?.attendence.toString() + "%")
            this?.pbAttendance?.setProgress(result?.attendence!!)
            //this?.tvDateOfJoining?.setText(result?.school?.get(0)?.school_name)
        }


        for (i in 0 until result?.hobbies!!.size) {
            hobiesList.add(result?.hobbies.get(i).hobby!!)
        }

        println("hobiesList : " + hobiesList)
        setupHobies(hobiesList)

        hideLoading()
        binding?.llStudentDetailsLayout?.visibility = View.VISIBLE
    }

    private fun setupHobies(hobiesList: ArrayList<String>) {
        val inflater: LayoutInflater =
            activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val viewGroup: ViewGroup = requireView().findViewById(R.id.llHobiesLayout)
        viewGroup!!.removeAllViews()
        for (i in 0 until hobiesList.size) {
            val view: ViewGroup =
                inflater.inflate(R.layout.child_hobies_layout, viewGroup, false) as ViewGroup
            val tvHobies: TextView = view.findViewById(R.id.tvHobies)

            tvHobies.text = (i + 1).toString() + ". " + hobiesList.get(i)
            view.tag = i
            viewGroup.addView(view, i)
        }
    }
}