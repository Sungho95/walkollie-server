package com.richbasoft.ollie.admin.interfaces.title

import com.richbasoft.ollie.admin.domain.title.dto.AdminTitlePostDto
import com.richbasoft.ollie.admin.domain.title.service.AdminTitleCreateService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.AdminTitleCreateApiResponses
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
class AdminTitleCreateController(
    private val adminTitleCreateService: AdminTitleCreateService
) : BaseController() {

    @Operation(
        summary = "칭호 생성",
        description = "칭호 정보와 얻기 위한 조건을 통해 생성할 수 있습니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @AdminTitleCreateApiResponses
    @PostMapping
    fun createTitle(
        @Valid @RequestBody adminTitlePostDto: AdminTitlePostDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = adminTitleCreateService.createTitle(adminTitlePostDto)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(SingleResponse(response))
    }
}