package com.richbasoft.ollie.admin.interfaces.inquiry

import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminAnswerPostDto
import com.richbasoft.ollie.admin.domain.inquiry.service.AdminInquiryCreateService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.AdminAnswerCreateApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Admin Inquiry", description = "관리자 문의 API")
@RestController
@RequestMapping("/api/v1/admin/inquiry")
class AdminInquiryCreateController(
    private val adminInquiryCreateService: AdminInquiryCreateService
) : BaseController() {

    @Operation(
        summary = "문의 답변 생성",
        description = "사용자 문의에 대한 답변을 작성합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @AdminAnswerCreateApiResponses
    @PostMapping("/{inquiry-id}")
    fun createAnswer(
        @PathVariable("inquiry-id") inquiryId: Long,
        @RequestBody adminAnswerPostDto: AdminAnswerPostDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = adminInquiryCreateService.createAnswer(inquiryId, adminAnswerPostDto, loginMemberId)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(response)
    }

}