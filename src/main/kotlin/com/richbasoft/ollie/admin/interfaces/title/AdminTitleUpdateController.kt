package com.richbasoft.ollie.admin.interfaces.title

import com.richbasoft.ollie.admin.domain.title.dto.AdminTitlePatchDto
import com.richbasoft.ollie.admin.domain.title.service.AdminTitleUpdateService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.AdminTitleUpdateApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@Tag(name = "Admin Title", description = "관리자 칭호 API")
@RestController
@RequestMapping("/api/v1/admin/title")
class AdminTitleUpdateController(
    private val adminTitleUpdateService: AdminTitleUpdateService
) : BaseController() {

    @Operation(
        summary = "칭호 수정",
        description = "수정할 칭호 아이디(titleId)와 수정할 정보를 통해 칭호을 수정합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @AdminTitleUpdateApiResponses
    @PatchMapping("/{title-id}")
    fun updateTitle(
        @PathVariable("title-id") titleId: Long,
        @Valid @RequestBody adminTitlePatchDto: AdminTitlePatchDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = adminTitleUpdateService.updateTitle(titleId, adminTitlePatchDto)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(SingleResponse(response))
    }
}