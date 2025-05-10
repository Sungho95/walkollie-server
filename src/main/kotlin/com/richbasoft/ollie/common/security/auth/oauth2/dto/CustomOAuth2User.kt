//package com.richbasoft.ollie.global.security.auth.oauth2.dto
//
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.oauth2.core.user.OAuth2User
//import java.io.Serializable
//
//class CustomOAuth2User(
//
//    val registrationId: String,
//    val customAttributes: Map<String, Any>,
//    val customAuthorities: List<GrantedAuthority>,
//    val email: String
//
//) : OAuth2User, Serializable {
//
//    override fun getName(): String {
//        return registrationId
//    }
//
//    override fun getAttributes(): Map<String, Any> {
//        return customAttributes
//    }
//
//    override fun getAuthorities(): List<GrantedAuthority> {
//        return customAuthorities
//    }
//}