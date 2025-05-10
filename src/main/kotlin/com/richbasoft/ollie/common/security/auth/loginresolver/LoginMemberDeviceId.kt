package com.richbasoft.ollie.common.security.auth.loginresolver

import io.swagger.v3.oas.annotations.Parameter

@Parameter(hidden = true)
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class LoginMemberDeviceId
