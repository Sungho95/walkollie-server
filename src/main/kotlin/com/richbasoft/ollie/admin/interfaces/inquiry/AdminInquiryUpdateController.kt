package com.richbasoft.ollie.admin.interfaces.inquiry

import com.richbasoft.ollie.admin.domain.inquiry.dto.AdminAnswerPatchDto
import com.richbasoft.ollie.admin.domain.inquiry.service.AdminInquiryUpdateService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.AdminAnswerUpdateApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Admin Inquiry", description = "관리자 문의 API")
@RestController
@RequestMapping("/api/v1/admin/inquiry")
class AdminInquiryUpdateController(
    private val adminInquiryUpdateService: AdminInquiryUpdateService
) : BaseController() {

    @Operation(
        summary = "문의 답변 삭제",
        description = "고객 문의에 대한 답변을 삭제합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @AdminAnswerUpdateApiResponses
    @PatchMapping("/{inquiry-id}/{answer-id}")
    fun updateAnswer(
        @PathVariable("inquiry-id") inquiryId: Long,
        @PathVariable("answer-id") answerId: Long,
        @RequestBody answerPatchDto: AdminAnswerPatchDto,
        @LoginMemberId loginMemberId: Long
    ) : ResponseEntity<*> {
        val response = adminInquiryUpdateService.updateAnswer(inquiryId, answerId, answerPatchDto)
        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }
}