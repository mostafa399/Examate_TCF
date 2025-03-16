package com.mstfahlal.examate_tcf.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferenceRepository {
    suspend fun getIsFirstTime(): Flow<Boolean>
    suspend fun setIsFirstTime(firstTime: Boolean)
}