package com.mkc.school.utils


import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings
import android.util.Patterns
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.mkc.school.R
import java.io.IOException
import java.nio.charset.Charset
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


object CommonUtils {

    val timestamp: String
        get() = SimpleDateFormat(AppConstants.TIMESTAMP_FORMAT, Locale.US).format(Date())

    @SuppressLint("all")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }

    fun isEmailValid(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    @Throws(IOException::class)
    fun loadJSONFromAsset(context: Context, jsonFileName: String): String {
        val manager = context.assets
        val `is` = manager.open(jsonFileName)

        val size = `is`.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()

        return String(buffer, Charset.forName("UTF-8"))
    }

//    fun showLoadingDialog(context: Context): Dialog {
//        val progressDialog = Dialog(context)
//        progressDialog.show()
//        if (progressDialog.window != null) {
//            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
//        }
//        progressDialog.setContentView(R.layout.progress_dialog)
//        progressDialog.setCancelable(false)
//        progressDialog.setCanceledOnTouchOutside(false)
//        return progressDialog
//    }

    fun showSuccessSnackbar(context: Context, view: View, message: String){
        val snackbar: Snackbar
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        val snackBarView: View = snackbar.view
        snackBarView.setBackgroundColor(context.resources.getColor(R.color.colorSuccess))
        snackbar.show()
    }

    fun showErrorSnackbar(context: Context, view: View, message: String){
        val snackbar: Snackbar
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        val snackBarView: View = snackbar.view
        snackBarView.setBackgroundColor(context.resources.getColor(R.color.colorError))
        snackbar.show()
    }

    fun showWarningSnackbar(context: Context, view: View, message: String){
        val snackbar: Snackbar
        snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        val snackBarView: View = snackbar.view
        snackBarView.setBackgroundColor(context.resources.getColor(R.color.colorWarning))
        snackbar.show()
    }

    fun showLoadingDialog(context: Context): Dialog {
        val progressDialog = Dialog(context)
        progressDialog.show()
        if (progressDialog.window != null) {
            progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        progressDialog.setContentView(R.layout.progress_dialog)
        progressDialog.setCancelable(false)
        progressDialog.setCanceledOnTouchOutside(false)
        return progressDialog
    }

   fun getMonthName(selectedMonth: Int): String {
        var monthName : String= ""
        when (selectedMonth) {
            1 -> {
                monthName = "January"
            }
            2 -> {
                monthName = "February"
            }
            3 -> {
                monthName = "March"
            }
            4 -> {
                monthName = "April"
            }
            5 -> {
                monthName = "May"
            }
            6 -> {
                monthName = "June"
            }
            7 -> {
                monthName = "July"
            }
            8 -> {
                monthName = "August"
            }
            9 -> {
                monthName = "Septembar"
            }
            10 -> {
                monthName = "Octobar"
            }
            11 -> {
                monthName = "Novembar"
            }
            12 -> {
                monthName = "Decembar"
            }
        }

        return monthName
    }

    fun getFormatedDate(dateString: String): String {

        var outputDate :String = ""
        var dateValue : Date? = null

        val input = SimpleDateFormat("yyyy-MM-dd")
        val output = SimpleDateFormat("dd-MM-yyyy")
        dateValue = input.parse(dateString)
        outputDate = output.format(dateValue)
        return outputDate
    }

    fun getFormatedDateWithDayName(dateString: String): String {

        var outputDate :String = ""
        var dateValue : Date? = null

        val input = SimpleDateFormat("yyyy-MM-dd")
        val output = SimpleDateFormat("EEE dd")
        dateValue = input.parse(dateString)
        outputDate = output.format(dateValue)
        return outputDate
    }

    fun formateServerDateFromstring(inputDate: String?): String? {
        var parsed: Date? = null
        var outputDate = ""
        var inputFormat = "yyyy-MM-dd'T'HH:mm:ss"
        var outputFormat = "dd-MM-yyyy"
        val df_input =
            SimpleDateFormat(inputFormat, Locale.getDefault())
        val df_output =
            SimpleDateFormat(outputFormat, Locale.getDefault())
        try {
            parsed = df_input.parse(inputDate)
            outputDate = df_output.format(parsed)
        } catch (e: ParseException) {
        }
        return outputDate
    }

    fun formateServerDateToTime(inputDate: String?): String? {
        var parsed: Date? = null
        var outputDate = ""
        var inputFormat = "yyyy-MM-dd'T'HH:mm:ss"
        var outputFormat = "HH:mm aa"
        val df_input =
            SimpleDateFormat(inputFormat, Locale.getDefault())
        val df_output =
            SimpleDateFormat(outputFormat, Locale.getDefault())
        try {
            parsed = df_input.parse(inputDate)
            outputDate = df_output.format(parsed)
        } catch (e: ParseException) {
        }
        return outputDate
    }
}