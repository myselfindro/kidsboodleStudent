package com.mkc.school.data.datasource.api


import com.mkc.school.data.pojomodel.api.request.LoginRequest
import com.mkc.school.data.pojomodel.api.response.login.LoginResponse
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

}
