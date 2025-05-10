package com.richbasoft.ollie.admin.interfaces.item

import com.richbasoft.ollie.admin.domain.item.dto.ItemPostDto
import com.richbasoft.ollie.admin.domain.item.service.AdminItemCreateService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.AdminItemCreateApiResponses
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

@Tag(name = "Admin Item", description = "관리자 아이템 API")
@RestController
@RequestMapping("/api/v1/admin/item")
class AdminItemCreateController(
    private val adminItemCreateService: AdminItemCreateService
) : BaseController() {

    @Operation(
        summary = "아이템 생성",
        description = "아이템 정보와 얻기 위한 조건을 통해 생성할 수 있습니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @AdminItemCreateApiResponses
    @PostMapping
    fun createItem(
        @Valid @RequestBody itemPostDto: ItemPostDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = adminItemCreateService.createItem(itemPostDto)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(SingleResponse(response))
    }
}