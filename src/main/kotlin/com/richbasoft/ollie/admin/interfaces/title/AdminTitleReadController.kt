package com.richbasoft.ollie.admin.interfaces.title

import com.richbasoft.ollie.admin.domain.title.service.AdminTitleReadService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.AdminTitleReadApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Admin Title", description = "관리자 칭호 API")
@RestController
@RequestMapping("/api/v1/admin/title")
class AdminTitleReadController(
    private val adminTitleReadService: AdminTitleReadService
) : BaseController() {

    @Operation(
        summary = "칭호 상세 조회",
        description = "칭호 아이디(titleId)를 통해 칭호의 상세정보를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @AdminTitleReadApiResponses
    @GetMapping("/{title-id}")
    fun getTitle(
        @PathVariable("title-id") titleId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = adminTitleReadService.getTitle(titleId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    @Operation(summary = "칭호 상세 리스트 조회", description = "칭호의 상세정보 리스트를 조회합니다.")
    @AdminTitleReadApiResponses
    @GetMapping
    fun getTitleListByCondition(
        @RequestParam category: String,
        @RequestParam type: String
    ): ResponseEntity<*> {
        val responses = adminTitleReadService.getTitleListByCondition(category, type)

        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(responses))
    }
}