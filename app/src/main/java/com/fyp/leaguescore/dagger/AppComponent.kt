package com.fyp.leaguescore.dagger


import com.fyp.leaguescore.util.PresenterModule
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, PresenterModule::class, DataSourceModule::class,FirebaseServiceModule::class])
interface AppComponent {


}

