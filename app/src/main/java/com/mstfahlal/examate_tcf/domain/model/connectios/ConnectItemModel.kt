package com.mstfahlal.examate_tcf.domain.model.connectios

data class ConnectItemModel(
    val userName: String = "",
    val image: String = "",
    val target: String = "",
    val userInfo: List<UserModel> = listOf(),
    val languages: List<String> = listOf(),
)
