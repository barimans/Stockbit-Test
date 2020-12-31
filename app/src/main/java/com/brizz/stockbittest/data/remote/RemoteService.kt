package com.brizz.stockbittest.data.remote

import com.brizz.stockbittest.data.models.WatchlistResponse
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface RemoteService {

    @GET("data/top/totaltoptiervolfull")
    fun getDataWatchList(
        @Query("limit") limit: Int? = 10,
        @Query("tsym") tysm: String? = "USD"
    ): Observable<WatchlistResponse>

    companion object {
        fun create(): RemoteService {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val builder = OkHttpClient().newBuilder()
            builder.readTimeout(30, TimeUnit.SECONDS)
            builder.connectTimeout(30, TimeUnit.SECONDS)
            builder.writeTimeout(30, TimeUnit.SECONDS)
            builder.addInterceptor(httpLoggingInterceptor)
            builder.addInterceptor(CustomInterceptor())

            val client = builder.build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://min-api.cryptocompare.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return retrofit.create(RemoteService::class.java)
        }
    }

    class CustomInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            val builder = request.newBuilder()
            builder.addHeader("Content-Type", "application/json")
            builder.addHeader("authorization", "Apikey 45865879bb2bfba3b1ce8c17ede64b3281ea5fbf7e779d3d3d90df0519ba8f9c")

            return chain.proceed(builder.build())
        }

    }
}