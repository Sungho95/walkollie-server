package com.richbasoft.ollie.interfaces.internal.member

import com.richbasoft.ollie.domain.member.dto.MemberPostDto
import com.richbasoft.ollie.domain.member.service.MemberCreateService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.utils.annotation.swagger.MemberCreateApiResponses
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

@Tag(name = "Member", description = "회원 API")
@RestController
@RequestMapping("/api/v1/member")
class MemberCreateController(

    private val memberCreateService: MemberCreateService

) : BaseController() {

    @Operation(
        summary = "회원가입",
        description = "이메일(email)과 패스워드(password) 정보를 통해 회원가입합니다.",
        security = [SecurityRequirement(name = "public")]
    )
    @MemberCreateApiResponses
    @PostMapping
    fun createMember(
        @Valid @RequestBody memberPostDto: MemberPostDto
    ): ResponseEntity<*> {
        val response = memberCreateService.createMember(memberPostDto)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(SingleResponse(response))
    }
}