package eraapps.bankasia.bdinternetbanking.apps.module

import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {


    val certificatePinner = CertificatePinner.Builder()
        .add("smartapp.bankasia-bd.com", "sha256/ARxkpm+9saee0FDjgR/4ixXKdKuie/e924Qgbi4QwH4=")
        .add("smartapp.bankasia-bd.com", "sha256/hETpgVvaLC0bvcGG3t0cuqiHvr4XyP2MTwCiqhgRWwU=")
        .add("smartapp.bankasia-bd.com", "sha256/cGuxAXyFXFkWm61cF4HPWX8S0srS9j0aSqN0k4AP+4A=")
        .build()




    // https://smartapp.bankasia-bd.com/
    var okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(70, TimeUnit.SECONDS)
        .readTimeout(70, TimeUnit.SECONDS)
        .writeTimeout(70, TimeUnit.SECONDS)
        .callTimeout(70, TimeUnit.SECONDS)
        .certificatePinner(certificatePinner)
        .build()

    var okHttpClientNid: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(120, TimeUnit.SECONDS)
        .readTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)
        .callTimeout(120, TimeUnit.SECONDS)
        .certificatePinner(certificatePinner)
        .build()

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            // .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
    }

    fun getRetrofitNid(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            // .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClientNid)
            .build()
    }


    fun getRetrofitWithInterceptor(): Retrofit {
        val clintWithInterceptor = okHttpClient
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(clintWithInterceptor)
            .build()
    }
}