package com.richbasoft.ollie.common.config

import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberDeviceIdResolver
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberIdResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.apply {
            add(LoginMemberIdResolver())
            add(LoginMemberDeviceIdResolver())
        }
    }
}