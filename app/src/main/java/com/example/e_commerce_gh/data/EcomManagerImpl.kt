package com.example.e_commerce_gh.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.e_commerce_gh.domain.LocalManager
import com.example.e_commerce_gh.presentation.utils.Constants
import com.example.e_commerce_gh.presentation.utils.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.prefs.Preferences

class EcomManagerImpl(
    private val context :Context
):LocalManager {
    override suspend fun savedAppEntry() {
        context.dataStore.edit {
            settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {
            preferencesKey -> preferencesKey[PreferencesKeys.APP_ENTRY] ?:false
        }
    }
}

private val readOnlyProperty = preferencesDataStore(name = USER_SETTINGS)

val Context.dataStore: DataStore<androidx.datastore.preferences.core.Preferences> by readOnlyProperty

private object  PreferencesKeys{
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}