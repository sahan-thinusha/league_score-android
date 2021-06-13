package com.fyp.leaguescore.dagger


import com.fyp.leaguescore.ui.user.LoginActivity
import com.fyp.leaguescore.util.PresenterModule
import com.fyp.leaguescore.util.SharedPreferencesHandler
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, PresenterModule::class, DataSourceModule::class,FirebaseServiceModule::class])
interface AppComponent {
    fun inject(target: LoginActivity)
    fun inject(target: SharedPreferencesHandler)

}

