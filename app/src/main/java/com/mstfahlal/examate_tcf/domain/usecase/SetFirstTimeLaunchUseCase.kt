package com.mstfahlal.examate_tcf.domain.usecase

import com.mstfahlal.examate_tcf.domain.repository.PreferenceRepository
import javax.inject.Inject

class SetFirstTimeLaunchUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {
    suspend operator fun invoke(isFirstTime: Boolean) = preferenceRepository.setIsFirstTime(isFirstTime)
}