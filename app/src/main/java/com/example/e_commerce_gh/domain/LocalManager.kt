package com.example.e_commerce_gh.domain

import kotlinx.coroutines.flow.Flow

interface LocalManager {
   suspend fun savedAppEntry()
    fun readAppEntry():Flow<Boolean>
}