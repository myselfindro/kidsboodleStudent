package com.mkc.school.ui.UpcomingLiveFragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
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
import com.mkc.school.data.pojomodel.api.response.liveclass.LiveclassResponse
import com.mkc.school.data.pojomodel.api.response.liveclass.Result
import com.mkc.school.databinding.FragmentLiveclassBinding
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.ui.liveclass.LiveclassNavigator
import com.mkc.school.ui.liveclass.LiveclassViewModel
import com.mkc.school.ui.liveclass.UpcomingModel
import com.mkc.school.ui.liveclass.adapter.LiveclassAdapter
import com.mkc.school.ui.liveclass.adapter.UpcomingLiveAdapter
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject


class LiveclassFragment : BaseFragment<FragmentLiveclassBinding, LiveclassViewModel>(),
    LiveclassNavigator, LiveclassAdapter.OnLiveclassItemClick {

    companion object {
        val TAG = LiveclassFragment::class.java.simpleName
        fun newInstance(): LiveclassFragment {
            val args = Bundle()
            val fragment = LiveclassFragment()
            fragment.arguments = args
            return fragment
        }
    }

    var rvLiveClass: RecyclerView? = null
    var rvupcomingLiveclass: RecyclerView? = null
    private var liveclassViewModel: LiveclassViewModel? = null
    private var binding: FragmentLiveclassBinding? = null
    private var layoutManager: LinearLayoutManager? = null
    private var liveclassAdapter: LiveclassAdapter? = null
    private var upcomingLiveAdapter: UpcomingLiveAdapter? = null
    private var liveClassList: ArrayList<Result> = ArrayList<Result>()
    private var upcomingList: java.util.ArrayList<UpcomingModel>? = null

    val token: String = "Token " + ApplicationClass.instance!!.appSharedPref!!.accessToken



    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_liveclass
    override val viewModel: LiveclassViewModel
        get() {
            liveclassViewModel = ViewModelProvider(
                this,
                ViewModelFactory(requireActivity().application)
            ).get(
                LiveclassViewModel::class.java
            )
            return liveclassViewModel as LiveclassViewModel
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        liveclassViewModel!!.navigator = this
        binding = viewDataBinding
        val btnCompleted = view.findViewById(R.id.btnCompleted) as TextView
        val btnUpcoming = view.findViewById(R.id.btnUpcoming) as TextView
        rvLiveClass = view.findViewById (R.id.rvLiveClass) as RecyclerView
        rvupcomingLiveclass = view.findViewById(R.id.rvupcomingLiveclass) as RecyclerView


        //loadDummyData()
        initview()
        viewModel.getLiveClassList()


        btnCompleted.setOnClickListener(View.OnClickListener {

            viewModel.getLiveClassList()
            rvLiveClass?.visibility = View.VISIBLE
            rvupcomingLiveclass?.visibility = View.GONE

        })

        btnUpcoming.setOnClickListener(View.OnClickListener {

            upcomingList()
            rvLiveClass?.visibility = View.GONE
            rvupcomingLiveclass?.visibility = View.VISIBLE

        })

    }


    private fun upcomingList(){

        showLoading()
        val jsonRequest: JsonObjectRequest =
            object : JsonObjectRequest(
                Request.Method.GET,
                "http://3.17.248.213:8002/upcoming_live_class_list/",
                null,
                { response ->
                    Log.i("Response-->", java.lang.String.valueOf(response))
                    try {
                        val result = JSONObject(java.lang.String.valueOf(response))
                        upcomingList = java.util.ArrayList<UpcomingModel>()
                        val response_data: JSONArray = result.getJSONArray("results")
                        for (i in 0 until response_data.length()) {
                            val UpcomingModel = UpcomingModel()
                            val responseobj = response_data.getJSONObject(i)
                            val routineObj: JSONObject = responseobj.getJSONObject("routine")
                            val timeslotObj: JSONObject = routineObj.getJSONObject("time_slot_details")
                            UpcomingModel.date = responseobj.getString("date")
                            UpcomingModel.subjectname = routineObj.getString("subject_name")
                            UpcomingModel.startdate = routineObj.getString("start_time")
                            UpcomingModel.enddate = routineObj.getString("end_time")
                            UpcomingModel.zoomlink = responseobj.getString("class_link")
                            upcomingList?.add(UpcomingModel)
                        }
                        upcomingLiveAdapter = UpcomingLiveAdapter(context, upcomingList)
                        rvupcomingLiveclass?.setAdapter(upcomingLiveAdapter)
                        rvupcomingLiveclass?.setLayoutManager(
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

    /*private fun loadDummyData() {
        liveClassList.add(LiveclassModel("Math","Mr. Sujay Bose","https://theracquet.org/wp-content/uploads/2020/04/thumbnail_Image-2-1-900x675.jpg", 1))
    }*/

    private fun initview() {

        binding?.rvLiveClass?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        layoutManager!!.setOrientation(LinearLayoutManager.VERTICAL)
        binding?.rvLiveClass?.setLayoutManager(layoutManager)
        liveclassAdapter = LiveclassAdapter(activity, liveClassList, this)
        binding?.rvLiveClass?.setAdapter(liveclassAdapter)

//        binding?.tabliveclass?.addTab(binding?.tabliveclass?.newTab()?.setText("Upcoming")!!)
//        binding?.tabliveclass?.addTab(binding?.tabliveclass?.newTab()?.setText("Completed")!!)
    }

    override fun onClick() {}
    override fun successLiveclassResponse(liveclassResponse: LiveclassResponse) {
        if (liveclassResponse?.results != null) {
            //CommonUtils.showSuccessSnackbar(requireActivity(),binding?.mainLayout!!,timetableResponse.msg!!)
            hideLoading()
            if (liveclassResponse.results?.size!! > 0) {
                liveClassList.clear()
                liveClassList.addAll(liveclassResponse.results)
                liveclassAdapter?.notifyDataSetChanged()
                binding?.ivNoDataFound?.visibility = View.GONE
                binding?.rvLiveClass?.visibility = View.VISIBLE
            } else {
                binding?.rvLiveClass?.visibility = View.GONE
                binding?.ivNoDataFound?.visibility = View.VISIBLE
                //CommonUtils.showErrorSnackbar(requireActivity(), binding?.mainLayout!!, timetableResponse.msg!!)
            }

        } else {
            hideLoading()
            //CommonUtils.showErrorSnackbar(requireActivity(), binding?.lvmainLayout!!, liveclassResponse?.results.subject_name!!)
        }
    }

    /*fun successLiveclassResponse(liveclassResponse: TimetableResponse) {
        TODO("Not yet implemented")
    }
*/
    override fun errorLiveclassResponse(throwable: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun successUpcomingLiveclassResponse(liveclassResponse: LiveclassResponse) {
        TODO("Not yet implemented")
    }

    override fun errorUpcomingLiveclassResponse(throwable: Throwable?) {
        TODO("Not yet implemented")
    }

    override fun onLiveclassItemClick(position: Int, action: String?) {
    }


}