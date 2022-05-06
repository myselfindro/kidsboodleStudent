package com.mkc.school.ui.dashboard

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.app.ActivityCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.*
import com.mkc.school.ui.base.ViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.databinding.ActivityDashboardBinding
import com.mkc.school.ui.UpcomingLiveFragment.LiveclassFragment
import com.mkc.school.ui.account.AccountFragment
import com.mkc.school.ui.announcement.AnnouncementFragment
import com.mkc.school.ui.attendance.AttendanceFragment
import com.mkc.school.ui.base.BaseActivity
import com.mkc.school.ui.changepassword.ChangePasswordActivity
import com.mkc.school.ui.grade.GradeFragment
import com.mkc.school.ui.home.HomeFragment
import com.mkc.school.ui.login.LoginActivity
import com.mkc.school.ui.successscreen.SuccessScreenActivity
import com.mkc.school.ui.teacher.TeachersFragment
import com.mkc.school.ui.timetable.TimetableFragment
import com.mkc.school.utils.CommonUtils
import com.mkc.school.utils.CommonUtils.showErrorSnackbar
import com.mkc.school.utils.CommonUtils.showSuccessSnackbar
import com.mkc.school.utils.CommonUtils.showWarningSnackbar
import com.permissionx.guolindev.PermissionX


class  DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>(),
    DashboardNavigator,
    View.OnClickListener, NavigationBarView.OnItemSelectedListener,
    NavigationView.OnNavigationItemSelectedListener {

    private var dashboardViewModel: DashboardViewModel? = null
    private var binding: ActivityDashboardBinding? = null
    public var ft: FragmentTransaction? = null
    private var doubleBackToExitPressedOnce: Boolean? = false
    public var isHomeFragmentVisible: Boolean? = true
    public var bottomNavigationView: BottomNavigationView? = null
    public var ivNav: ImageView? = null
    public var ivBack: ImageView? = null
    public var tvHeaderSchoolName: AppCompatTextView? = null
    public var latitude : String ?= null
    public var longitude : String ?= null
    var weatherTempareture : String ? =""

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_dashboard
    override val viewModel: DashboardViewModel
        get() {
            dashboardViewModel = ViewModelProvider(this, ViewModelFactory(application)).get(
                DashboardViewModel::class.java
            )
            return dashboardViewModel as DashboardViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashboardViewModel!!.navigator = this
        binding = viewDataBinding



        ivNav = binding?.ivNav
        ivBack = binding?.ivBack
        tvHeaderSchoolName = binding?.tvHeaderSchoolName
        bottomNavigationView = binding?.bottomNavigation

        ft = supportFragmentManager.beginTransaction()
        ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft!!.replace(R.id.fragment_container, HomeFragment.newInstance())
        ft!!.commit()

        binding?.ivNav?.setOnClickListener(this)
        binding?.ivBack?.setOnClickListener(this)

        binding?.bottomNavigation?.setOnItemSelectedListener(this)
        binding?.nvView?.setNavigationItemSelectedListener(this);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        PermissionX.init(this)
            .permissions(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    Toast.makeText(this, "All permissions are granted", Toast.LENGTH_LONG).show()
                    getLocationUpdates()
                } else {
                    Toast.makeText(this, "These permissions are denied: $deniedList", Toast.LENGTH_LONG).show()
                }
            }


    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.ivNav -> {
                binding?.myDrawerLayout?.openDrawer(GravityCompat.START);
            }

            R.id.ivBack -> {
                onBackPressed()
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bottom_navigation_home -> {
                ft = supportFragmentManager.beginTransaction()
                //ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, HomeFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = true
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                true
            }
            R.id.bottom_navigation_timetable -> {
                ft = supportFragmentManager.beginTransaction()
                // ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, TimetableFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = false
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                true
            }
            R.id.bottom_navigation_attendance -> {
                ft = supportFragmentManager.beginTransaction()
                // ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, AttendanceFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = false
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                true
            }
            R.id.bottom_navigation_liveclass -> {
                ft = supportFragmentManager.beginTransaction()
                //ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, LiveclassFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = false
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                true
            }
            R.id.bottom_navigation_account -> {
                ft = supportFragmentManager.beginTransaction()
                //ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, AccountFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = false
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                true
            }

            //////////////////////////////Navigation Drawer////////////////////////////
            R.id.nav_grade -> {
                ft = supportFragmentManager.beginTransaction()
                //ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, GradeFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = false
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                binding?.myDrawerLayout?.closeDrawers()
                true
            }

            R.id.nav_liveclass -> {
                ft = supportFragmentManager.beginTransaction()
                //ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, LiveclassFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = false
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                binding?.myDrawerLayout?.closeDrawers()
                true
            }

            R.id.nav_announcement -> {
                ft = supportFragmentManager.beginTransaction()
                //ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, AnnouncementFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = false
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                binding?.myDrawerLayout?.closeDrawers()
                true
            }

            R.id.nav_attendance -> {
                ft = supportFragmentManager.beginTransaction()
                //ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, AttendanceFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = false
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                binding?.myDrawerLayout?.closeDrawers()
                true
            }

            R.id.nav_teacher -> {
                ft = supportFragmentManager.beginTransaction()
                //ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, TeachersFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = false
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                binding?.myDrawerLayout?.closeDrawers()
                true
            }

            R.id.nav_timetable -> {
                ft = supportFragmentManager.beginTransaction()
                //ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, TimetableFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = false
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                binding?.myDrawerLayout?.closeDrawers()
                true
            }

            R.id.nav_account -> {
                ft = supportFragmentManager.beginTransaction()
                //ft!!.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                ft!!.replace(R.id.fragment_container, AccountFragment.newInstance())
                ft!!.commit()

                isHomeFragmentVisible = false
                ivBack?.visibility = View.GONE
                ivNav?.visibility = View.VISIBLE
                binding?.myDrawerLayout?.closeDrawers()
                true
            }


            R.id.nav_change_password -> {
                binding?.myDrawerLayout?.closeDrawers()
                //showSuccessSnackbar(this, binding?.activityMain!!, "Success")
                val i = Intent(this, ChangePasswordActivity::class.java)
                i.putExtra("PAGE_FROM","SETTENGS_CHANGE_PASSWORD")
                startActivity(i)
                finish()
                true
            }

            R.id.nav_logout -> {
                binding?.myDrawerLayout?.closeDrawers()
                val i = Intent(this, LoginActivity::class.java)
                startActivity(i)
                finish()
                true
            }

            else -> false
        }
    }

    override fun onBackPressed() {
        if (isHomeFragmentVisible == true) {
            if (doubleBackToExitPressedOnce!!) {
                super.onBackPressed()
                return
            }
            doubleBackToExitPressedOnce = true
            showWarningSnackbar(this, binding?.activityMain!!, "Please click BACK again to exit")
            Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
        } else {
            ft = supportFragmentManager.beginTransaction()
            ft!!.setCustomAnimations(R.anim.slide_out_left, R.anim.slide_in_right);
            ft!!.replace(R.id.fragment_container, HomeFragment.newInstance())
            ft!!.commit()

            isHomeFragmentVisible = true
            bottomNavigationView?.setSelectedItemId(R.id.bottom_navigation_home);
            ivBack?.visibility = View.GONE
            ivNav?.visibility = View.VISIBLE
        }
    }

    private fun getLocationUpdates()
    {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest()
        locationRequest.interval = 50000
        locationRequest.fastestInterval = 50000
        locationRequest.smallestDisplacement = 170f // 170 m = 0.1 mile
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY //set according to your app function
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return

                if (locationResult.locations.isNotEmpty()) {
                    // get latest location
                    val location =
                        locationResult.lastLocation
                    // use your location object
                    // get latitude , longitude and other info from this

                    latitude = location.latitude.toString()
                    longitude = location.longitude.toString()
                    println("LOCATION :"+location.latitude)
                    println("LOCATION :"+location.longitude)
                }
            }
        }
    }

    //start location updates
    private fun startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            null /* Looper */
        )
    }

    // stop location updates
    private fun stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }

    // stop receiving location update when activity not visible/foreground
    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    // start receiving location update when activity  visible/foreground
    override fun onResume() {
        super.onResume()
        startLocationUpdates()
    }
}