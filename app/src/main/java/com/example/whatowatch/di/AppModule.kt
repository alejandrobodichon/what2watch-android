package com.example.whatowatch.di


import com.example.whatowatch.ui.main.MainActivity
import com.example.whatowatch.ui.main.contentselection.ContentSelectionFragment
import com.example.whatowatch.ui.main.emotions.EmotionsFragment
import com.example.whatowatch.ui.main.login.LoginFragment
import com.example.whatowatch.ui.main.genreselection.GenreSelectionFragment
import com.example.whatowatch.ui.main.guest.GuestFragment
import com.example.whatowatch.ui.main.profileregister.ProfileRegisterFragment
import com.example.whatowatch.ui.main.recomendationdetail.RecomendationDetailFragment
import com.example.whatowatch.ui.main.register.RegisterFragment
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

    @ContributesAndroidInjector
    internal abstract fun recomendationDetailFragment(): RecomendationDetailFragment

    @ContributesAndroidInjector
    internal abstract fun contentSelectionFragment(): ContentSelectionFragment

    @ContributesAndroidInjector
    internal abstract fun registerFragment(): RegisterFragment

    @ContributesAndroidInjector
    internal abstract fun guestFragment(): GuestFragment

    @ContributesAndroidInjector
    internal abstract fun profileRegisterFragment(): ProfileRegisterFragment

    @ContributesAndroidInjector
    internal abstract fun emotionsFragment(): EmotionsFragment


}