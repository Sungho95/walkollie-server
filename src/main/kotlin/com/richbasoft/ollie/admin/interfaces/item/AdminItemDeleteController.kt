package com.richbasoft.ollie.admin.interfaces.item

import com.richbasoft.ollie.admin.domain.item.service.AdminItemDeleteService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.AdminItemDeleteApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Admin Item", description = "관리자 아이템 API")
@RestController
@RequestMapping("/api/v1/admin/item")
class AdminItemDeleteController(
    private val adminItemDeleteService: AdminItemDeleteService
) : BaseController() {

    @Operation(
        summary = "아이템 삭제",
        description = "아이템 아이디(itemId)를 통해 아이템을 삭제합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @AdminItemDeleteApiResponses
    @DeleteMapping("/{item-id}")
    fun deleteItem(@PathVariable("item-id") itemId: Long, @LoginMemberId loginMemberId: Long): ResponseEntity<Unit> {
        adminItemDeleteService.deleteItem(itemId)
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
            .build()
    }
}