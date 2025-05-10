package com.richbasoft.ollie.common.security.auth

import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.dto.TokenRequestDto
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.MemberReIssueApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.MemberLogoutApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "login-endpoint", description = "로그인 API")
@RestController
@RequestMapping("/api/v1/member")
class AuthController(

    private val authService: AuthService

) : BaseController() {

    @Operation(
        summary = "로그아웃",
        description = "로그인된 회원을 로그아웃시킵니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @MemberLogoutApiResponses
    @PostMapping("/logout")
    fun logout(@LoginMemberId loginMemberId: Long): ResponseEntity<Unit> {
        authService.logout(loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .build()
    }

    @Operation(
        summary = "액세스 토큰 재발급",
        description = "액세스 토큰 만료된 회원의 액세스 토큰을 재발급합니다.",
        security = [SecurityRequirement(name = "public")]
    )
    @MemberReIssueApiResponses
    @PostMapping("/refresh")
    fun accessTokenReIssue(@Valid @RequestBody tokenRequest: TokenRequestDto): ResponseEntity<*> {
        val response = authService.accessTokenReIssue(tokenRequest)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }
}