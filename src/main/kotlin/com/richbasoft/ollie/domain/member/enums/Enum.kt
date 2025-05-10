package com.richbasoft.ollie.domain.member.enums

enum class Gender(
    val gender: String
) {
    SECRET("비밀"),
    MAN("남자"),
    WOMAN("여자")
}

enum class Role(
    val status: String
) {
    USER("유저"),
    ADMIN("관리자")
}

enum class Status(
    val status: String
) {
    ACTIVE("활동"),
    SLEEP("휴면"),
    WITHDRAWAL("탈퇴")
}