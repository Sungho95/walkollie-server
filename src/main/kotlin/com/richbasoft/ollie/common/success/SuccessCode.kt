package com.richbasoft.ollie.common.success

enum class SuccessCode(
    val statusCode: SuccessStatusCode,
    val message: String
) {
    SEARCH_OK(SuccessStatusCode.OK, "조회에 성공하였습니다."),
    CREATED_SUCCESS(SuccessStatusCode.CREATED, "생성에 성공하였습니다.")
}

enum class SuccessStatusCode(
    val status: Int
) {
    OK(200),
    CREATED(201),
    ACCEPTED(202),
    NO_CONTENT(204)
}