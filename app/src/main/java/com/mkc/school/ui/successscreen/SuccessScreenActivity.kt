package com.mkc.school.ui.successscreen

import android.Manifest
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.databinding.ActivityDashboardBinding
import com.mkc.school.databinding.ActivitySuccessScreenBinding
import com.mkc.school.ui.base.BaseActivity
import com.mkc.school.ui.dashboard.DashboardActivity
import com.mkc.school.ui.dashboard.DashboardNavigator
import com.mkc.school.ui.dashboard.DashboardViewModel
import com.permissionx.guolindev.PermissionX

class SuccessScreenActivity : BaseActivity<ActivitySuccessScreenBinding, SuccessScreenViewModel>(),
    SuccessScreenNavigator {

    private var successScreenViewModel: SuccessScreenViewModel? = null
    private var binding: ActivitySuccessScreenBinding? = null


    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_success_screen
    override val viewModel: SuccessScreenViewModel
        get() {
            successScreenViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(
                SuccessScreenViewModel::class.java
            )
            return successScreenViewModel as SuccessScreenViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        successScreenViewModel!!.navigator = this
        binding = viewDataBinding


        binding?.tvExplore?.setOnClickListener {
            val i = Intent(this, DashboardActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    override fun onClick() {}
}