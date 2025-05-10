package com.richbasoft.ollie.interfaces.internal.inquiry

import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.InquiryAnswerReadApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.InquiryListReadApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.InquiryReadApiResponses
import com.richbasoft.ollie.domain.inquiry.service.InquiryReadService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Inquiry", description = "문의하기 API")
@RestController
@RequestMapping("/api/v1/inquiry")
class InquiryReadController(
    private val inquiryReadService: InquiryReadService
) {
    @Operation(
        summary = "문의 리스트 조회",
        description = "사용자 ID를 통해 사용자의 문의 리스트를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @InquiryListReadApiResponses
    @GetMapping
    fun getInquires(@LoginMemberId loginMemberId: Long): ResponseEntity<*> {
        val response = inquiryReadService.getInquiryList(loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(response))
    }

    @Operation(
        summary = "문의 상세 조회",
        description = "사용자 ID와 문의 ID를 통해 사용자의 문의 상세 정보를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @InquiryReadApiResponses
    @GetMapping("/{inquiry-id}")
    fun getInquiry(
        @PathVariable("inquiry-id") inquiryId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = inquiryReadService.getInquiry(inquiryId, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    @Operation(
        summary = "문의 답변 조회",
        description = "사용자 문의에 대한 관리자의 답변을 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @InquiryAnswerReadApiResponses
    @GetMapping("/{inquiry-id}/answer")
    fun getInquiryAnswer(
        @PathVariable("inquiry-id") inquiryId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = inquiryReadService.getInquiryAnswer(inquiryId, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }
}