package com.fyp.leaguescore.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides

import javax.inject.Singleton


/**
 * Dagger 2 app module class
 */
@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDefaultSharedPreference(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    internal fun provideSharedPreferencesEditor(preferences: SharedPreferences): SharedPreferences.Editor {
        return preferences.edit()
    }
}