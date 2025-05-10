package com.richbasoft.ollie.interfaces.internal.inquiry

import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.InquiryDeleteApiResponses
import com.richbasoft.ollie.domain.inquiry.service.InquiryDeleteService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Inquiry", description = "문의하기 API")
@RestController
@RequestMapping("/api/v1/inquiry")
class InquiryDeleteController(
    private val inquiryDeleteService: InquiryDeleteService
) : BaseController() {

    @Operation(
        summary = "문의 삭제",
        description = "회원 ID와 문의 ID를 통해 문의를 삭제합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @InquiryDeleteApiResponses
    @DeleteMapping("/{inquiry-id}")
    fun deleteInquiry(
        @PathVariable("inquiry-id") inquiryId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<Unit> {
        inquiryDeleteService.deleteInquiry(inquiryId, loginMemberId)

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }
}