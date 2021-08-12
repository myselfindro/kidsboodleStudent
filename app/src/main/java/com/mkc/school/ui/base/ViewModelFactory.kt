package com.mkc.school.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mkc.school.ui.account.AccountViewModel
import com.mkc.school.ui.announcement.AnnouncementViewModel
import com.mkc.school.ui.attendance.AttendanceViewModel
import com.mkc.school.ui.dashboard.DashboardViewModel
import com.mkc.school.ui.grade.GradeViewModel
import com.mkc.school.ui.home.HomeViewModel
import com.mkc.school.ui.liveclass.LiveclassViewModel
import com.mkc.school.ui.login.LoginViewModel
import com.mkc.school.ui.splash.SplashViewModel
import com.mkc.school.ui.successscreen.SuccessScreenViewModel
import com.mkc.school.ui.teacher.TeachersViewModel
import com.mkc.school.ui.timetable.TimetableViewModel


@Suppress("UNCHECKED_CAST")
class ViewModelFactory(var context: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(SplashViewModel::class.java) -> {
                SplashViewModel(context) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(context) as T
            }

            modelClass.isAssignableFrom(SuccessScreenViewModel::class.java) -> {
                SuccessScreenViewModel(context) as T
            }

            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> {
                DashboardViewModel(context) as T
            }

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(context) as T
            }

            modelClass.isAssignableFrom(TimetableViewModel::class.java) -> {
                TimetableViewModel(context) as T
            }

            modelClass.isAssignableFrom(AttendanceViewModel::class.java) -> {
                AttendanceViewModel(context) as T
            }

            modelClass.isAssignableFrom(LiveclassViewModel::class.java) -> {
                LiveclassViewModel(context) as T
            }

            modelClass.isAssignableFrom(AccountViewModel::class.java) -> {
                AccountViewModel(context) as T
            }

            modelClass.isAssignableFrom(GradeViewModel::class.java) -> {
                GradeViewModel(context) as T
            }

            modelClass.isAssignableFrom(TeachersViewModel::class.java) -> {
                TeachersViewModel(context) as T
            }

            modelClass.isAssignableFrom(AnnouncementViewModel::class.java) -> {
                AnnouncementViewModel(context) as T
            }

            else -> {
                throw IllegalArgumentException("Unknown class name")

            }
        }
    }
}