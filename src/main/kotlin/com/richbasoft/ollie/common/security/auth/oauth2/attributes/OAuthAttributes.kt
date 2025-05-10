//package com.richbasoft.ollie.global.security.auth.oauth2.attributes
//
//import com.richbasoft.ollie.domain.member.entity.Member
//
//@Suppress("UNCHECKED_CAST")
//data class OAuthAttributes(
//    val attributes: Map<String, Any>,
//    val nameAttributesKey: String,
//    val email: String,
//    val oAuthProvider: OAuthProvider,
//) {
//
//    companion object {
//        fun of(registrationId: String, userNameAttributeName: String, attributes: Map<String, Any>): OAuthAttributes {
//            return when (registrationId) {
//                "google" -> {
//                    userNameAttributeName.ofGoogle(attributes)
//                }
//
//                "naver" -> {
//                    "id".ofNaver(attributes)
//                }
//
//                else -> {
//                    "id".ofKakao(attributes)
//                }
//            }
//        }
//
//        private fun String.ofGoogle(
//            attributes: Map<String, Any>
//        ): OAuthAttributes {
//            return OAuthAttributes(
//                attributes = attributes,
//                nameAttributesKey = this,
//                email = attributes["email"].toString(),
//                oAuthProvider = OAuthProvider.GOOGLE,
//            )
//        }
//
//        private fun String.ofNaver(
//            attributes: Map<String, Any>
//        ): OAuthAttributes {
//            val response: Map<String, Any> = attributes["response"] as Map<String, Any>
//            return OAuthAttributes(
//                attributes = attributes,
//                nameAttributesKey = this,
//                email = response["email"].toString(),
//                oAuthProvider = OAuthProvider.NAVER,
//            )
//        }
//
//        private fun String.ofKakao(
//            attributes: Map<String, Any>
//        ): OAuthAttributes {
//            val kakaoAccount: Map<String, Any> = attributes["kakao_account"] as Map<String, Any>
//
//            return OAuthAttributes(
//                attributes = attributes,
//                nameAttributesKey = this,
//                email = kakaoAccount["email"].toString(),
//                oAuthProvider = OAuthProvider.KAKAO,
//            )
//        }
//    }
//
//    fun toEntity(): Member {
//        return Member(
//            email = email,
//        )
//    }
//}
