package com.mkc.school.ui.grade

import android.app.Dialog
import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.mkc.school.ApplicationClass
import com.mkc.school.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

class Examreview : AppCompatActivity() {

    var subjectid: String? = null
    var examid: String? = null
    val token: String = "Token " + ApplicationClass.instance!!.appSharedPref!!.accessToken
    private var questionlist: java.util.ArrayList<ExamreviewModel>? = null

    var rvExamreview: RecyclerView? = null
    private var examreviewAdapter: ExamreviewAdapter? = null
    private val SHARED_PREFS = "sharedPrefs"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_examreview)
        val intent = intent
        subjectid = intent.getStringExtra("subjectid")
        val sharedPreferences = applicationContext.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        examid = sharedPreferences.getString("examid", "")
        rvExamreview = findViewById<RecyclerView>(R.id.rvExamreview)

        val btn_back = findViewById(R.id.btn_back) as ImageView
        btn_back.setOnClickListener(View.OnClickListener { view ->
            onBackPressed()
        })



        examreview()

    }

    private fun examreview() {

        showProgressDialog()

        val jsonRequest: JsonObjectRequest =
            object : JsonObjectRequest(
                Request.Method.GET,
                "https://kidboodle.com/api/student_exam_detail_view/?exam_id=" + examid + "&subject_id=" + subjectid,
                null,
                { response ->
                    Log.i("Response-->", java.lang.String.valueOf(response))
                    try {
                        val result = JSONObject(java.lang.String.valueOf(response))
                        val response_data: JSONArray = result.getJSONArray("results")
                        for (i in 0 until response_data.length()) {
                            questionlist = java.util.ArrayList<ExamreviewModel>()
                            val responseobj = response_data.getJSONObject(i)
                            val question_data: JSONArray = responseobj.getJSONArray("question_list")
                            for (j in 0 until question_data.length()) {
                                val ExamreviewModel = ExamreviewModel()
                                val questionobj = question_data.getJSONObject(j)
                                ExamreviewModel.question = questionobj.getString("question")
                                ExamreviewModel.answer = questionobj.getString("answer")
                                ExamreviewModel.comment = questionobj.getString("comment")
                                questionlist?.add(ExamreviewModel)
                            }
                        }
                        examreviewAdapter = ExamreviewAdapter(this, questionlist)
                        rvExamreview?.setAdapter(examreviewAdapter)
                        rvExamreview?.setLayoutManager(
                            LinearLayoutManager(
                                applicationContext, LinearLayoutManager.VERTICAL,
                                false
                            )
                        )

                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                    hideProgressDialog()
                },
                object : Response.ErrorListener {
                    override fun onErrorResponse(error: VolleyError) {
                        Toast.makeText(applicationContext, error.toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }) {
                @Throws(AuthFailureError::class)
                override fun getHeaders(): Map<String, String>? {
                    val params: MutableMap<String, String> = HashMap()
                    params["Authorization"] = token.toString()
                    return params
                }
            }

        Volley.newRequestQueue(applicationContext).add(jsonRequest)


    }


    fun viewcomment(examreviewModel: ExamreviewModel) {

        val dialog = Dialog(this)
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val params = WindowManager.LayoutParams()
        dialog.setContentView(R.layout.popup_comment)
        params.copyFrom(dialog.window!!.attributes)
        params.height = WindowManager.LayoutParams.MATCH_PARENT
        params.width = WindowManager.LayoutParams.MATCH_PARENT
        params.gravity = Gravity.BOTTOM
        dialog.window!!.attributes = params
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val tvComment = dialog.findViewById<View>(R.id.tvComment) as TextView
        val btn_back = dialog.findViewById<View>(R.id.btn_back) as ImageView
        tvComment.setText(examreviewModel.comment)

        btn_back.setOnClickListener { dialog.dismiss() }


    }


    var mProgressDialog: ProgressDialog? = null

    fun showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this)
            mProgressDialog!!.setMessage("Loading...")
            mProgressDialog!!.isIndeterminate = true
        }
        mProgressDialog!!.show()
    }

    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }
}