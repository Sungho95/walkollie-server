package com.richbasoft.ollie.common.security.auth.oauth2.attributes

enum class OAuthProvider(
    val type: String
) {
    NONE("사용안함"),
    GOOGLE("구글"),
    NAVER("네이버"),
    KAKAO("카카오")
}
