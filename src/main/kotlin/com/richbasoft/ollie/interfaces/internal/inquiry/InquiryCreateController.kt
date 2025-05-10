package com.richbasoft.ollie.interfaces.internal.inquiry

import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.InquiryCreateApiResponses
import com.richbasoft.ollie.domain.inquiry.dto.request.InquiryPostDto
import com.richbasoft.ollie.domain.inquiry.service.InquiryCreateService
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

@Tag(name = "Inquiry", description = "문의하기 API")
@RestController
@RequestMapping("/api/v1/inquiry")
class InquiryCreateController(
    private val inquiryCreateService: InquiryCreateService
) : BaseController() {

    @Operation(
        summary = "문의하기",
        description = "문의할 제목, 내용, 카테고리, 이미지 정보와 회원 ID를 통해 문의합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @InquiryCreateApiResponses
    @PostMapping
    fun postInquiry(
        @Valid @RequestBody inquiryPostDto: InquiryPostDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = inquiryCreateService.createInquiry(inquiryPostDto, loginMemberId)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(SingleResponse(response))
    }
}