package com.mkc.school.ui.home

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.events.calendar.views.EventsCalendar
import com.kwabenaberko.openweathermaplib.constant.Languages
import com.kwabenaberko.openweathermaplib.constant.Units
import com.kwabenaberko.openweathermaplib.implementation.OpenWeatherMapHelper
import com.kwabenaberko.openweathermaplib.implementation.callback.CurrentWeatherCallback
import com.kwabenaberko.openweathermaplib.model.currentweather.CurrentWeather
import com.mkc.school.BR
import com.mkc.school.R
import com.mkc.school.data.pojomodel.model.HorizontalOptionsModel
import com.mkc.school.databinding.FragmentHomeBinding
import com.mkc.school.ui.announcement.AnnouncementFragment
import com.mkc.school.ui.attendance.AttendanceFragment
import com.mkc.school.ui.base.BaseFragment
import com.mkc.school.ui.base.ViewModelFactory
import com.mkc.school.ui.dashboard.DashboardActivity
import com.mkc.school.ui.grade.GradeFragment
import com.mkc.school.ui.home.adapter.CallenderEventAdapter
import com.mkc.school.ui.home.adapter.HorizontalOptionsAdapter
import com.mkc.school.ui.home.adapter.NoticeAdapter
import com.mkc.school.ui.liveclass.LiveclassFragment
import com.mkc.school.ui.teacher.TeachersFragment
import com.mkc.school.ui.timetable.TimetableFragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(), HomeNavigator,
    EventsCalendar.Callback, NoticeAdapter.OnNoticeItemClick,
    CallenderEventAdapter.OnCalEventItemClick, HorizontalOptionsAdapter.OnHorizontalItemClick {

    companion object {
        val TAG = HomeFragment::class.java.simpleName
        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var homeViewModel: HomeViewModel? = null
    private var binding: FragmentHomeBinding? = null
    private var layoutManager: LinearLayoutManager? = null
    private var noticeAdapter: NoticeAdapter? = null
    private var horizontalOptionsAdapter: HorizontalOptionsAdapter? = null
    private var callenderEventAdapter: CallenderEventAdapter? = null
    private var myNoticeList: ArrayList<String> = ArrayList<String>()
    private var dateWiseCalEventList: ArrayList<String> = ArrayList<String>()
    private var horizontalOptionsList: ArrayList<HorizontalOptionsModel> = ArrayList<HorizontalOptionsModel>()

    var weatherTempareture : String ? =""
    var AppId = "2e65127e909e178d0af311a81f39948c"


    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.fragment_home
    override val viewModel: HomeViewModel
        get() {
            homeViewModel =
                ViewModelProvider(this, ViewModelFactory(requireActivity().application)).get(
                    HomeViewModel::class.java
                )
            return homeViewModel as HomeViewModel
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel!!.navigator = this
        binding = viewDataBinding

        dummyDataLoad()
        setupRecyclerView()
        setupCalender()

        if (weatherTempareture.equals("")){
            Handler().postDelayed(Runnable { //This method will be executed once the timer is over
                val lat = (activity as DashboardActivity?)?.latitude?.toDouble()
                val long = (activity as DashboardActivity?)?.longitude?.toDouble()
                println("loc : --- "+lat)
                println("loc : --- "+long)
                if (lat!=null && long !=null){
                    getWeather(lat,long)
                }

            }, 2000)
        }



        val timeFormat: DateFormat = SimpleDateFormat("hh:mm aa")
        val fullDayName: DateFormat = SimpleDateFormat("EEEE")
        val cal = Calendar.getInstance()

        binding?.tvDayTime?.setText(fullDayName.format(Date()) + ", " + timeFormat.format(cal.time))


    }

    private fun getWeather(lat: Double?, long: Double?) {
        val helper = OpenWeatherMapHelper(AppId)
        helper.setUnits(Units.METRIC);
        helper.setLanguage(Languages.ENGLISH);

        helper.getCurrentWeatherByGeoCoordinates(lat!!, long!!, object : CurrentWeatherCallback {
            override fun onSuccess(currentWeather: CurrentWeather) {
                Log.v(
                    TAG, """
     Coordinates: ${currentWeather.coord.lat}, ${currentWeather.coord.lon}
     Weather Description: ${currentWeather.weather[0].description}
     Temperature: ${currentWeather.main.tempMax}
     Wind Speed: ${currentWeather.wind.speed}
     City, Country: ${currentWeather.name}, ${currentWeather.sys.country}
     """.trimIndent()
                )

                println("TEMP : "+currentWeather.main.tempMax.roundToInt())
                val temp : String = currentWeather.main.tempMax.roundToInt().toString()
                binding?.tvWeather?.setText(temp+" °C")
            }

            override fun onFailure(throwable: Throwable) {
                Log.v(TAG, throwable.message!!)
            }
        })
    }


    private fun setupCalender() {
        val today = Calendar.getInstance()
        val end = Calendar.getInstance()
        end.add(Calendar.YEAR, 2)

        binding?.eventsCalendar?.setSelectionMode(binding?.eventsCalendar?.SINGLE_SELECTION!!)
            ?.setToday(today)
            ?.setMonthRange(today, end)
            ?.setWeekStartDay(Calendar.SUNDAY, false)
            ?.setIsBoldTextOnSelectionEnabled(true)
//            ?.setDatesTypeface(FontsManager.getTypeface(FontsManager.OPENSANS_REGULAR, this))
//            ?.setMonthTitleTypeface(FontsManager.getTypeface(FontsManager.OPENSANS_SEMIBOLD, this))
//            ?.setWeekHeaderTypeface(FontsManager.getTypeface(FontsManager.OPENSANS_SEMIBOLD, this))
            ?.setCallback(this)
            ?.build()

        calenderAddEvent("13-08-2021")

    }

    private fun dummyDataLoad() {

        myNoticeList.add("Planning to resume our weekly Innovation Meet “SFT INNO-MEET”")
        myNoticeList.add(" meeting is intended to be an open discussion on Trending Technologies")
        myNoticeList.add("All deviations and non applied attendance will be treated as company policy.")
        myNoticeList.add("The show features iconic Indian brands that are powering the Make in India initiative.")
        myNoticeList.add("We need to complete below work today")

        dateWiseCalEventList.add("School annual function")
        dateWiseCalEventList.add("Parent metting")


        horizontalOptionsList.add(
            HorizontalOptionsModel(
                activity?.resources?.getString(R.string.grade),
                R.drawable.ic_grade_icon,
                R.color.colorPrimary
            )
        )
        horizontalOptionsList.add(
            HorizontalOptionsModel(
                activity?.resources?.getString(R.string.live_class),
                R.drawable.ic_liveclass_icon,
                R.color.design_default_color_primary_dark
            )
        )
        horizontalOptionsList.add(
            HorizontalOptionsModel(
                activity?.resources?.getString(R.string.announcement),
                R.drawable.ic_announcement_icon,
                R.color.colorPrimary
            )
        )
        horizontalOptionsList.add(
            HorizontalOptionsModel(
                activity?.resources?.getString(R.string.teacher),
                R.drawable.ic_teacher_icon,
                R.color.colorPrimary
            )
        )
        horizontalOptionsList.add(
            HorizontalOptionsModel(
                activity?.resources?.getString(R.string.attendance),
                R.drawable.ic_attendance_icon,
                R.color.colorPrimary
            )
        )
        horizontalOptionsList.add(
            HorizontalOptionsModel(
                activity?.resources?.getString(R.string.timetable),
                R.drawable.ic_timetable_icon,
                R.color.colorPrimary
            )
        )
    }

    private fun setupRecyclerView() {

        binding?.rvHorizontalOptions?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        layoutManager!!.setOrientation(LinearLayoutManager.HORIZONTAL)
        binding?.rvHorizontalOptions?.setLayoutManager(layoutManager)
        horizontalOptionsAdapter = HorizontalOptionsAdapter(activity, horizontalOptionsList, this)
        binding?.rvHorizontalOptions?.setAdapter(horizontalOptionsAdapter)

        binding?.rvNotice?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        layoutManager!!.setOrientation(LinearLayoutManager.VERTICAL)
        binding?.rvNotice?.setLayoutManager(layoutManager)
        noticeAdapter = NoticeAdapter(activity, myNoticeList, this)
        binding?.rvNotice?.setAdapter(noticeAdapter)

        binding?.rvCalEvent?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(activity)
        layoutManager!!.setOrientation(LinearLayoutManager.VERTICAL)
        binding?.rvCalEvent?.setLayoutManager(layoutManager)
        callenderEventAdapter = CallenderEventAdapter(activity, dateWiseCalEventList, this)
        binding?.rvCalEvent?.setAdapter(callenderEventAdapter)
    }

    private fun calenderAddEvent(eventDate: String) {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val date: Date = sdf.parse(eventDate)
        val cal = Calendar.getInstance()
        cal.time = date
        binding?.eventsCalendar?.addEvent(cal)
    }

    override fun onClick() {}

    override fun onDayLongPressed(selectedDate: Calendar?) {
    }

    override fun onDaySelected(selectedDate: Calendar?) {
        Toast.makeText(activity, selectedDate.toString(), Toast.LENGTH_SHORT).show()
    }

    override fun onMonthChanged(monthStartDate: Calendar?) {
    }

    override fun onNoticeItemClick(position: Int, action: String?) {}

    override fun onCalEventItemClick(position: Int, action: String?) {}

    override fun onHorizontalItemClick(position: Int, action: String?) {
        if (action.equals(activity?.resources?.getString(R.string.grade))) {
            (activity as DashboardActivity?)?.ft =
                activity?.supportFragmentManager?.beginTransaction()
            (activity as DashboardActivity?)?.ft!!.replace(
                R.id.fragment_container,
                GradeFragment.newInstance()
            )
            (activity as DashboardActivity?)?.ft!!.commit()

            (activity as DashboardActivity?)?.isHomeFragmentVisible = false
            (activity as DashboardActivity?)?.ivBack?.visibility = View.VISIBLE
            (activity as DashboardActivity?)?.ivNav?.visibility = View.GONE
        } else if (action.equals(activity?.resources?.getString(R.string.announcement))) {
            (activity as DashboardActivity?)?.ft =
                activity?.supportFragmentManager?.beginTransaction()
            (activity as DashboardActivity?)?.ft!!.replace(
                R.id.fragment_container,
                AnnouncementFragment.newInstance()
            )
            (activity as DashboardActivity?)?.ft!!.commit()

            (activity as DashboardActivity?)?.isHomeFragmentVisible = false
            (activity as DashboardActivity?)?.ivBack?.visibility = View.VISIBLE
            (activity as DashboardActivity?)?.ivNav?.visibility = View.GONE
        } else if (action.equals(activity?.resources?.getString(R.string.live_class))) {
            (activity as DashboardActivity?)?.ft =
                activity?.supportFragmentManager?.beginTransaction()
            (activity as DashboardActivity?)?.ft!!.replace(
                R.id.fragment_container,
                LiveclassFragment.newInstance()
            )
            (activity as DashboardActivity?)?.ft!!.commit()

            (activity as DashboardActivity?)?.isHomeFragmentVisible = false
            (activity as DashboardActivity?)?.bottomNavigationView!!.setSelectedItemId(R.id.bottom_navigation_liveclass);

        } else if (action.equals(activity?.resources?.getString(R.string.teacher))) {
            (activity as DashboardActivity?)?.ft =
                activity?.supportFragmentManager?.beginTransaction()
            (activity as DashboardActivity?)?.ft!!.replace(
                R.id.fragment_container,
                TeachersFragment.newInstance()
            )
            (activity as DashboardActivity?)?.ft!!.commit()

            (activity as DashboardActivity?)?.isHomeFragmentVisible = false
            (activity as DashboardActivity?)?.ivBack?.visibility = View.VISIBLE
            (activity as DashboardActivity?)?.ivNav?.visibility = View.GONE
        } else if (action.equals(activity?.resources?.getString(R.string.attendance))) {
            (activity as DashboardActivity?)?.ft =
                activity?.supportFragmentManager?.beginTransaction()
            (activity as DashboardActivity?)?.ft!!.replace(
                R.id.fragment_container,
                AttendanceFragment.newInstance()
            )
            (activity as DashboardActivity?)?.ft!!.commit()

            (activity as DashboardActivity?)?.isHomeFragmentVisible = false
            (activity as DashboardActivity?)?.bottomNavigationView!!.setSelectedItemId(R.id.bottom_navigation_attendance);
        } else if (action.equals(activity?.resources?.getString(R.string.timetable))) {
            (activity as DashboardActivity?)?.ft =
                activity?.supportFragmentManager?.beginTransaction()
            (activity as DashboardActivity?)?.ft!!.replace(
                R.id.fragment_container,
                TimetableFragment.newInstance()
            )
            (activity as DashboardActivity?)?.ft!!.commit()

            (activity as DashboardActivity?)?.isHomeFragmentVisible = false
            (activity as DashboardActivity?)?.bottomNavigationView!!.setSelectedItemId(R.id.bottom_navigation_timetable);
        }
    }

}