package com.mkc.school.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.databinding.ActivitySplashBinding
import com.mkc.school.ui.base.BaseActivity
import com.mkc.school.ui.login.LoginActivity


class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    private var splashViewModel: SplashViewModel? = null
    private var binding: ActivitySplashBinding? = null


    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_splash
    override val viewModel: SplashViewModel
        get() {
            splashViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(SplashViewModel::class.java)
            return splashViewModel as SplashViewModel
        }


    override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)
        splashViewModel!!.navigator = this
        binding = viewDataBinding

        initView()
    }

    private fun initView() {
        Handler().postDelayed(Runnable { //This method will be executed once the timer is over
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }, 2000)
    }


    override fun onClick() {
    }
}