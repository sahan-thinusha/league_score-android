package com.fyp.leaguescore.dagger


import com.fyp.leaguescore.ui.prediction.ChampionListActivity
import com.fyp.leaguescore.ui.prediction.ChampionSelectActivity
import com.fyp.leaguescore.ui.prediction.TeamPrediction
import com.fyp.leaguescore.ui.user.LoginActivity
import com.fyp.leaguescore.ui.user.SignUpActivity
import com.fyp.leaguescore.util.PresenterModule
import com.fyp.leaguescore.util.SharedPreferencesHandler
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, PresenterModule::class, DataSourceModule::class,FirebaseServiceModule::class])
interface AppComponent {
    fun inject(target: LoginActivity)
    fun inject(target: SharedPreferencesHandler)
    fun inject(target: SignUpActivity)
    fun inject(target: ChampionSelectActivity)
    fun inject(target: ChampionListActivity)

}

