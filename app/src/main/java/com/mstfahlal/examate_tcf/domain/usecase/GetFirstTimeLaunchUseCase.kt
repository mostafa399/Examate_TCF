package com.mstfahlal.examate_tcf.domain.usecase

import com.mstfahlal.examate_tcf.domain.repository.PreferenceRepository
import javax.inject.Inject

class GetFirstTimeLaunchUseCase @Inject constructor(
    private val preferenceRepository: PreferenceRepository
) {
    suspend operator fun invoke() = preferenceRepository.getIsFirstTime()
}
