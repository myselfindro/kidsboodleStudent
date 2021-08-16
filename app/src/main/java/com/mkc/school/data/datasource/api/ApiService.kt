package com.mkc.school.data.datasource.api


import com.mkc.school.data.pojomodel.api.request.LoginRequest
import com.mkc.school.data.pojomodel.api.response.announcement.AnnouncementListResponse
import com.mkc.school.data.pojomodel.api.response.home.HomeResponse
import com.mkc.school.data.pojomodel.api.response.login.LoginResponse
import com.mkc.school.data.pojomodel.api.response.profile.AccountProfileResponse
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface ApiService {


//    @GET("Lookup/GetCountryList")
//    fun getStateList(): Single<StatelistResponse>
//

    @POST("login/")
    fun apiLogin(@Body loginRequest: LoginRequest): Single<LoginResponse>

    @GET("student_dashboard_details/")
    fun getDashboardDetails(): Single<HomeResponse>

    @GET("student_announcement_list/")
    fun getAnnouncementList(@Query(value = "page_size") page_size: String): Single<AnnouncementListResponse>

    @GET("student_profile/")
    fun getProfileDetails(): Single<AccountProfileResponse>

}
