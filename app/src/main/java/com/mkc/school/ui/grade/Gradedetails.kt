package com.mkc.school.ui.grade

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
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

class Gradedetails : AppCompatActivity() {

    var examid: String? = null
    val token: String = "Token " + ApplicationClass.instance!!.appSharedPref!!.accessToken
    private var subjectlist: java.util.ArrayList<SubjectModel>? = null
    var rvdetails: RecyclerView? = null
    private var gradedetailsAdapter: GradedetailsAdapter? = null
    private val SHARED_PREFS = "sharedPrefs"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gradedetails)
        val intent = intent
        examid = intent.getStringExtra("examid")
        //SharedPref

        //SharedPref
        val sharedPreferences = applicationContext.getSharedPreferences(SHARED_PREFS, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("examid", examid)
        editor.apply()

        val btn_back = findViewById(R.id.btn_back) as ImageView
        rvdetails = findViewById<RecyclerView>(R.id.rvdetails)

        btn_back.setOnClickListener(View.OnClickListener { view ->
            onBackPressed()
        })

        gradedetails()
    }

    private fun gradedetails() {

        showProgressDialog()
        val jsonRequest: JsonObjectRequest =
            object : JsonObjectRequest(
                Request.Method.GET,
                "https://kidboodle.com/api/student_exam_grade_list/?exam_id=" + examid,
                null,
                { response ->
                    Log.i("Response-->", java.lang.String.valueOf(response))
                    try {
                        val result = JSONObject(java.lang.String.valueOf(response))
                        val response_data: JSONArray = result.getJSONArray("results")
                        for (i in 0 until response_data.length()) {
                            subjectlist = java.util.ArrayList<SubjectModel>()
                            val SubjectModel = SubjectModel()
                            val responseobj = response_data.getJSONObject(i)
                            SubjectModel.examid = responseobj.getString("exam_id")
                            val subject_data: JSONArray = responseobj.getJSONArray("subject")
                            for (j in 0 until subject_data.length()) {
                                val SubjectModel = SubjectModel()
                                val subjectobj = subject_data.getJSONObject(j)
                                SubjectModel.subname = subjectobj.getString("subject_name")
                                SubjectModel.grade = subjectobj.getString("grade")
                                SubjectModel.totalmarks = subjectobj.getString("total_marks_obtained")
                                SubjectModel.subid = subjectobj.getString("subject_id")
                                subjectlist?.add(SubjectModel)
                            }
                        }
                        gradedetailsAdapter = GradedetailsAdapter(this, subjectlist)
                        rvdetails?.setAdapter(gradedetailsAdapter)
                        rvdetails?.setLayoutManager(
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