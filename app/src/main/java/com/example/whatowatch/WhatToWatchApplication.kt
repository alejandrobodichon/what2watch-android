package com.example.whatowatch

import ar.com.wolox.wolmo.core.WolmoApplication
import ar.com.wolox.wolmo.networking.di.DaggerNetworkingComponent
import ar.com.wolox.wolmo.networking.di.NetworkingComponent
import com.example.whatowatch.di.DaggerAppComponent
import com.example.whatowatch.BaseConfiguration.SHARED_PREFERENCES_NAME
import com.facebook.drawee.backends.pipeline.Fresco
import com.google.gson.FieldNamingPolicy
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.android.AndroidInjector
import okhttp3.logging.HttpLoggingInterceptor


class whatowatchApplication : WolmoApplication() {

    override fun onInit() {
        Fresco.initialize(this)
    }

    override fun applicationInjector(): AndroidInjector<whatowatchApplication> {
        return DaggerAppComponent.builder().networkingComponent(buildDaggerNetworkingComponent())
            .sharedPreferencesName(SHARED_PREFERENCES_NAME).application(this)
            .create(this)
    }

    private fun buildDaggerNetworkingComponent(): NetworkingComponent {
        //val builder = DaggerNetworkingComponent.builder().baseUrl("http://192.168.0.150:8080/")
        val builder = DaggerNetworkingComponent.builder().baseUrl("http://10.0.2.2:8080/")
            .gsonNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        builder.okHttpInterceptors(buildHttpLoggingInterceptor(HttpLoggingInterceptor.Level.BODY), ChuckInterceptor(this))
        return builder.build()
    }

    private fun buildHttpLoggingInterceptor(
        level: HttpLoggingInterceptor.Level
    ): HttpLoggingInterceptor? {
        val loggerInterceptor = HttpLoggingInterceptor()
        loggerInterceptor.level = level
        return loggerInterceptor
    }
}