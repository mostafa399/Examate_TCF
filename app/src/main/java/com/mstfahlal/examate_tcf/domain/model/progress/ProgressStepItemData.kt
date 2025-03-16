package com.mstfahlal.examate_tcf.domain.model.progress

data class ProgressStepItemData(
    val stepNumber: Int,
    val title: String,
    val isCompleted: Boolean,
    val progress: Float,
    val isLocked: Boolean
)
