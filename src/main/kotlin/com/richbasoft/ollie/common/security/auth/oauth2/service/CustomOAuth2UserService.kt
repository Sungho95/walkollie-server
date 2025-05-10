//package com.richbasoft.ollie.global.security.auth.oauth2.service
//
//import com.richbasoft.ollie.domain.member.entity.Member
//import com.richbasoft.ollie.domain.member.repository.MemberRepository
//import com.richbasoft.ollie.global.security.auth.oauth2.dto.CustomOAuth2User
//import com.richbasoft.ollie.global.security.auth.oauth2.attributes.OAuthAttributes
//import com.richbasoft.ollie.global.security.auth.utils.CustomAuthorityUtils
//import com.richbasoft.ollie.global.utils.logger
//import org.slf4j.Logger
//import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
//import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
//import org.springframework.security.oauth2.core.user.OAuth2User
//import org.springframework.stereotype.Service
//import org.springframework.transaction.annotation.Transactional
//
//@Service
//@Transactional
//class CustomOAuth2UserService(
//
//    private val memberRepository: MemberRepository,
//    private val authorityUtils: CustomAuthorityUtils
//
//) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
//    val log: Logger = logger()
//
//    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
//        val service: OAuth2UserService<OAuth2UserRequest, OAuth2User> = DefaultOAuth2UserService()
//        val oAuth2User = service.loadUser(userRequest)
//        log.info("service={}", service)
//        log.info("oAuth2User={}", oAuth2User)
//
//        val registrationId = userRequest.clientRegistration.registrationId
//        val userNameAttributeName = userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName
//        val attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.attributes)
//
//        val member = saveOrUpdate(attributes)
//        val authorities = authorityUtils.createAuthorities(member.roles)
//
//        return CustomOAuth2User(registrationId, attributes.attributes, authorities, member.email)
//    }
//
//    private fun saveOrUpdate(authAttributes: OAuthAttributes): Member {
//        val email = authAttributes.email
//        val member: Member = memberRepository.findByEmail(email)
//            ?: authAttributes.toEntity().apply {
//                val roles = authorityUtils.createRoles(email)
//                this.setAuthorityRoles(roles.toMutableList())
//                this.setOAuth2Provider(authAttributes.oAuthProvider)
//
//                return memberRepository.save(this)
//            }
//
//        return member
//    }
//}