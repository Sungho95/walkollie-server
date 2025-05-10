package com.richbasoft.ollie.admin.interfaces.item

import com.richbasoft.ollie.admin.domain.item.dto.AdminItemPatchDto
import com.richbasoft.ollie.admin.domain.item.service.AdminItemUpdateService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.AdminItemUpdateApiResponses
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

@Tag(name = "Admin Item", description = "관리자 아이템 API")
@RestController
@RequestMapping("/api/v1/admin/item")
class AdminItemUpdateController(
    private val adminItemUpdateService: AdminItemUpdateService
) : BaseController() {

    @Operation(
        summary = "아이템 수정",
        description = "수정할 아이템 아이디(itemId)와 수정할 정보를 통해 아이템을 수정합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @AdminItemUpdateApiResponses
    @PatchMapping("/{item-id}")
    fun updateItem(
        @PathVariable("item-id") itemId: Long,
        @Valid @RequestBody adminItemPatchDto: AdminItemPatchDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = adminItemUpdateService.updateItem(itemId, adminItemPatchDto)
        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }
}