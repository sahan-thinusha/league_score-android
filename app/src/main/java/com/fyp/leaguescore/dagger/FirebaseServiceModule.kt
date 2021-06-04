package com.fyp.leaguescore.dagger

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FirebaseServiceModule {

    @Provides
    @Singleton
    internal fun provideFirebaseAuth(): FirebaseAuth {
        // Initialize Firebase Auth
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    internal fun provideFireStore(): FirebaseFirestore {
        // Initialize Firebase db
        return FirebaseFirestore.getInstance()
    }

    @Provides
    @Singleton
    internal fun provideStorage(): FirebaseStorage {
        // Initialize Firebase db
        return FirebaseStorage.getInstance()

    }
}