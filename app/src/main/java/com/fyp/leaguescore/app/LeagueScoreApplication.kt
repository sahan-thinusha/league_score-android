package com.fyp.leaguescore.app


import android.app.Application
import com.fyp.leaguescore.dagger.AppComponent
import com.fyp.leaguescore.dagger.AppModule
import com.fyp.leaguescore.dagger.DaggerAppComponent
import es.dmoral.toasty.Toasty

class LeagueScoreApplication : Application() {




    protected fun initDagger(application: LeagueScoreApplication): AppComponent {
        return DaggerAppComponent.builder()
            .appModule(AppModule(application))
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger(this)
        Toasty.Config.getInstance()
            .allowQueue(true) // optional (prevents several Toastys from queuing)
            .apply() // required
    }
    private var appComponent: AppComponent? = null

    fun getAppComponent(): AppComponent? {

        return appComponent
    }

}

