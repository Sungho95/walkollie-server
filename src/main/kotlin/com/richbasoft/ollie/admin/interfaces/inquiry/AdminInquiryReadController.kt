package com.richbasoft.ollie.admin.interfaces.inquiry

import com.richbasoft.ollie.admin.domain.inquiry.service.AdminInquiryReadService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.AdminInquiryListReadApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.AdminInquiryReadApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Admin Inquiry", description = "관리자 문의 API")
@RestController
@RequestMapping("/api/v1/admin/inquiry")
class AdminInquiryReadController(
    private val adminInquiryReadService: AdminInquiryReadService
) : BaseController() {

    @Operation(
        summary = "관리자 문의 리스트 조회",
        description = "관리자용 사용자 문의 리스트를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @AdminInquiryListReadApiResponses
    @GetMapping
    fun getAdminInquiryList(
        @RequestParam condition: String,
        @RequestParam sort: String,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val responses = adminInquiryReadService.getAdminInquiryList(condition, sort)

        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(responses))
    }

    @Operation(
        summary = "관리자 문의 상세 조회",
        description = "관리자용 사용자 문의 상세 정보를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @AdminInquiryReadApiResponses
    @GetMapping("/{inquiry-id}")
    fun getAdminInquiry(
        @PathVariable("inquiry-id") inquiryId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = adminInquiryReadService.getAdminInquiry(inquiryId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }
}