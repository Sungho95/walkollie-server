package com.richbasoft.ollie.interfaces.internal.item

import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.domain.item.dto.ItemSearchConditionDto
import com.richbasoft.ollie.domain.item.service.ItemReadService
import com.richbasoft.ollie.common.utils.annotation.swagger.ItemReadApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Item", description = "아이템 API")
@RestController
@RequestMapping("/api/v1/item")
class ItemReadController(
    private val itemReadService: ItemReadService
) : BaseController() {

    @Operation(summary = "사용자 아이템 리스트 조회", description = "사용자의 아이템 리스트 정보를 조회합니다.")
    @ItemReadApiResponses
    @GetMapping
    fun getItemInfoList(
        searchCondition: ItemSearchConditionDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val responses = itemReadService.getItemInfoList(searchCondition, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(responses))
    }

    @Operation(
        summary = "사용자 아이템 상세 정보 조회",
        description = "아이템 아이디(itemId)를 통해 사용자에게 보여줄 아이템의 상세 정보를 조회합니다."
    )
    @ItemReadApiResponses
    @GetMapping("/{item-id}")
    fun getIteminfo(
        @PathVariable("item-id") itemId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = itemReadService.getItemInfoDetail(itemId, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }
}