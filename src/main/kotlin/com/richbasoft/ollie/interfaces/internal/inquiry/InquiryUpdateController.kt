package com.richbasoft.ollie.interfaces.internal.inquiry

import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.InquiryUpdateApiResponses
import com.richbasoft.ollie.domain.inquiry.dto.request.InquiryPatchDto
import com.richbasoft.ollie.domain.inquiry.service.InquiryUpdateService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Inquiry", description = "문의하기 API")
@RestController
@RequestMapping("/api/v1/inquiry")
class InquiryUpdateController(
    private val inquiryUpdateService: InquiryUpdateService
) : BaseController() {

    @Operation(
        summary = "문의 수정",
        description = "문의할 제목, 내용, 카테고리, 이미지 정보와 회원 ID를 통해 문의를 수정합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @InquiryUpdateApiResponses
    @PatchMapping("/{inquiry-id}")
    fun updateInquiry(
        @PathVariable("inquiry-id") inquiryId: Long,
        @Valid @RequestBody inquiryPatchDto: InquiryPatchDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = inquiryUpdateService.updateInquiry(inquiryId, inquiryPatchDto, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }
}