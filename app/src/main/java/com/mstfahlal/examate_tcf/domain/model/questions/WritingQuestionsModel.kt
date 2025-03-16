package com.mstfahlal.examate_tcf.domain.model.questions

data class WritingQuestionsModel(
    val question: String = "",
    val type: String = "",
    val progress: Float,
    val icon: Int,
    val answersCount: Int = 0,
    val questionsCount: Int = 0
)
