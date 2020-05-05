package com.example.whatowatch.di


import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.login.LoginFragment
import com.example.whatowatch.ui.main.genreselection.GenreSelectionFragment
import com.example.whatowatch.ui.root.RootActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {


    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @ContributesAndroidInjector
    internal abstract fun rootActivity(): RootActivity

    @ContributesAndroidInjector
    internal abstract fun genreSelectionFragment(): GenreSelectionFragment

    @ContributesAndroidInjector
    internal abstract fun cityForecastFragment(): LoginFragment


}