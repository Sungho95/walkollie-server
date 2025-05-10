package com.richbasoft.ollie.common.security.auth.loginresolver

import com.richbasoft.ollie.common.security.auth.memberdetails.MemberDetails
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

class LoginMemberIdResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        val hasAnnotation = parameter.hasParameterAnnotation(LoginMemberId::class.java)
        val hasLongType = Long::class.java.isAssignableFrom(parameter.parameterType)
        return hasAnnotation && hasLongType
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        val principal = SecurityContextHolder.getContext().authentication.principal

        if (principal is MemberDetails) {
            return principal.id
        }
        return null
    }
}