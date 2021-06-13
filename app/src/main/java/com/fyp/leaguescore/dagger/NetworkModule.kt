package com.fyp.leaguescore.dagger

import com.fyp.leaguescore.network.APIInterface
import com.fyp.leaguescore.util.ServiceGenerator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    internal fun provideApiInterface(): APIInterface? {
        return ServiceGenerator.getClient()?.create(APIInterface::class.java)
    }

}
