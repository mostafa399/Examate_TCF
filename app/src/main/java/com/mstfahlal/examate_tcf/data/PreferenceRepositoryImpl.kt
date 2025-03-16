package com.mstfahlal.examate_tcf.data

import com.mstfahlal.examate_tcf.domain.repository.PreferenceDataSource
import com.mstfahlal.examate_tcf.domain.repository.PreferenceRepository
import com.mstfahlal.examate_tcf.domain.utils.Constants.IS_FIRST_TIME_PREFS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PreferenceRepositoryImpl @Inject constructor(
    private val preferenceDataSource: PreferenceDataSource
) :
    PreferenceRepository {
    override suspend fun getIsFirstTime(): Flow<Boolean> {
        return flow {
            preferenceDataSource.getValue(IS_FIRST_TIME_PREFS, true).collect {
                emit(it as Boolean)
            }
        }
    }

    override suspend fun setIsFirstTime(firstTime: Boolean) {
        preferenceDataSource.setValue(IS_FIRST_TIME_PREFS, firstTime)
    }

}