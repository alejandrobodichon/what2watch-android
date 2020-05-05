package com.example.whatowatch.di

import android.app.Application
import ar.com.wolox.wolmo.core.di.modules.ContextModule
import ar.com.wolox.wolmo.core.di.modules.DefaultModule
import ar.com.wolox.wolmo.core.di.scopes.ApplicationScope
import ar.com.wolox.wolmo.networking.di.NetworkingComponent
import com.example.whatowatch.whatowatchApplication

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@ApplicationScope
@Component(dependencies = [NetworkingComponent::class],
        modules = [AndroidSupportInjectionModule::class, DefaultModule::class, ContextModule::class,
            AppModule::class])

interface AppComponent : AndroidInjector<whatowatchApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<whatowatchApplication>() {

        @BindsInstance
        abstract fun application(application: Application): Builder

        @BindsInstance
        abstract fun sharedPreferencesName(sharedPrefName: String): Builder

        abstract fun networkingComponent(networkingComponent: NetworkingComponent): Builder
    }
}
