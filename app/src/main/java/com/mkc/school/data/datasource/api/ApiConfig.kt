package com.mkc.school.data.datasource.api

interface ApiConfig {
    companion object {

        //Timeout
        val READ_TIMEOUT: Long = 5000
        val CONNECT_TIMEOUT: Long = 4000
        val WRITE_TIMEOUT: Long = 4000

        //
        val API_KEY = "Authorization"
    }
}
