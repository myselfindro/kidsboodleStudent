package com.mkc.school.utils


import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import android.util.Patterns
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.mkc.school.R
import java.io.IOException
import java.nio.charset.Charset
import java.text.DateFormat
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

    fun getFormatedDate(dateString: String): String {
        var formatedDate : String =""
        val formatter: DateFormat = SimpleDateFormat("dd-mm-yyyy")
        val date: Date = formatter.parse(dateString)
        val sm = SimpleDateFormat("mm-dd-yyyy")

        val strDate = sm.format(date)

        return strDate
    }
}