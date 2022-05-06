package com.mkc.school.ui.grade

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.mkc.school.ApplicationClass
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.api.response.exam.ExamListResponse
import com.mkc.school.data.pojomodel.api.response.exam.ExamResponse
import com.mkc.school.data.pojomodel.api.response.grade.GradeListResponse
import com.mkc.school.data.pojomodel.api.response.grade.GradeResponse
import com.mkc.school.databinding.FragmentGradeBinding
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.ui.grade.adapter.ExamSpinnerAdapter
import com.mkc.school.ui.grade.adapter.GradeAdapter
import com.mkc.school.utils.CommonUtils
import com.mkc.school.utils.CommonUtils.getFormatedDateWithMonthName
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


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
    var rv_gradelist: RecyclerView? = null
    private var gradeAdapter2: GradeAdapter2? = null
    private var gradeModelArrayList: java.util.ArrayList<GradeModel>? = null
    private var subjectlist: java.util.ArrayList<SubjectModel>? = null


    val token: String = "Token " + ApplicationClass.instance!!.appSharedPref!!.accessToken


    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_grade
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
        rv_gradelist = view.findViewById<RecyclerView>(R.id.rv_gradelist)

        //loaddummyData()
        initview()
        viewgrade()
//        showLoading()
//        viewModel.getExamList()
    }

    private fun viewgrade() {

        showLoading()
        val jsonRequest: JsonObjectRequest =
            object : JsonObjectRequest(
                Request.Method.GET,
                "https://kidboodle.com/api/student_exam_grade_list/",
                null,
                { response ->
                    Log.i("Response-->", java.lang.String.valueOf(response))
                    try {
                        val result = JSONObject(java.lang.String.valueOf(response))
                        gradeModelArrayList = java.util.ArrayList<GradeModel>()
                        val response_data: JSONArray = result.getJSONArray("results")
                        for (i in 0 until response_data.length()) {
                            subjectlist = java.util.ArrayList<SubjectModel>()
                            val grademodel = GradeModel()
                            val responseobj = response_data.getJSONObject(i)
                            val subject_data: JSONArray = responseobj.getJSONArray("subject")
                            grademodel.exam_type_name = responseobj.getString("exam_type_name")
                            grademodel.examid = responseobj.getString("exam_id")
                            for (j in 0 until subject_data.length()) {
                                val SubjectModel = SubjectModel()
                                val subjectobj = subject_data.getJSONObject(j)
                                SubjectModel.subname = subjectobj.getString("subject_name")
                                SubjectModel.grade = subjectobj.getString("grade")
                                subjectlist?.add(SubjectModel)
                            }
                            grademodel.subjectModelList = subjectlist
                            gradeModelArrayList?.add(grademodel)
                        }
                        gradeAdapter2 = GradeAdapter2(context, gradeModelArrayList)
                        rv_gradelist?.setAdapter(gradeAdapter2)
                        rv_gradelist?.setLayoutManager(
                            LinearLayoutManager(
                                context, LinearLayoutManager.VERTICAL,
                                false
                            )
                        )

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    hideLoading()
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show()
                    }
                }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String>? {
                    val params: MutableMap<String, String> = HashMap()
                    params["Authorization"] = token.toString()
                    return params
                }
            }

        Volley.newRequestQueue(context).add(jsonRequest)
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
            if (gradeResponse.result?.size!! > 0) {
                gradeList.clear()
                gradeList.addAll(gradeResponse.result)
                gradeAdapter?.notifyDataSetChanged()

                binding?.cvGradeLayout?.visibility = View.VISIBLE
                binding?.ivNoDataFound?.visibility = View.GONE
            } else {
                binding?.cvGradeLayout?.visibility = View.GONE
//                binding?.ivNoDataFound?.visibility = View.VISIBLE
            }
        } else {
            CommonUtils.showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                gradeResponse?.msg!!
            )
        }
    }

    override fun successExamResponse(examResponse: ExamResponse?) {
        if (examResponse?.request_status == 1) {
            hideLoading()
            //CommonUtils.showSuccessSnackbar(requireActivity(), binding?.mainLayout!!, examResponse.msg!!)
            if (examResponse.result?.size!! > 0) {
                examList.clear()
                examList.addAll(examResponse.result)
                examSpAdapter?.notifyDataSetChanged()

                binding?.tvSelectExam?.setText(
                    (examList.get(0).exam_type) + " - " +
                            getFormatedDateWithMonthName(examList.get(0).start_date!!) + " to " +
                            getFormatedDateWithMonthName(examList.get(0).end_date!!)
                )
                viewModel.getGradeList(pageSize!!, examList.get(0).id.toString())
            }
        } else {
            CommonUtils.showErrorSnackbar(
                requireActivity(),
                binding?.mainLayout!!,
                examResponse?.msg!!
            )
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