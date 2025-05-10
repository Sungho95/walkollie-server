package com.richbasoft.ollie.admin.interfaces.item

import com.richbasoft.ollie.admin.domain.item.service.AdminItemReadService
import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.AdminItemReadApiResponses
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

@Tag(name = "Admin Item", description = "관리자 아이템 API")
@RestController
@RequestMapping("/api/v1/admin/item")
class AdminItemReadController(
    private val adminItemReadService: AdminItemReadService
) : BaseController() {

    @Operation(
        summary = "아이템 상세 조회",
        description = "아이템 아이디(itemId)를 통해 아이템의 상세정보를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @AdminItemReadApiResponses
    @GetMapping("/{item-id}")
    fun getItem(@PathVariable("item-id") itemId: Long, @LoginMemberId loginMemberId: Long): ResponseEntity<*> {
        val response = adminItemReadService.getItem(itemId)
        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    @Operation(summary = "아이템 상세 리스트 조회", description = "아이템의 상세정보 리스트를 조회합니다.")
    @AdminItemReadApiResponses
    @GetMapping
    fun getItemListByType(
        @RequestParam type: String
    ): ResponseEntity<*> {
        val responses = adminItemReadService.getItemListByType(type)
        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(responses))
    }
}