package com.richbasoft.ollie.interfaces.internal.ollie

import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.domain.ollie.dto.OlliePatchDto
import com.richbasoft.ollie.domain.ollie.dto.SelectTitlePatchDto
import com.richbasoft.ollie.domain.ollie.dto.WearItemPatchDto
import com.richbasoft.ollie.domain.ollie.service.OllieUpdateService
import com.richbasoft.ollie.common.utils.annotation.swagger.ChangeItemApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.ChangeNameApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.ChangeTitleApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Ollie", description = "올리 API")
@RestController
@RequestMapping("/api/v1/ollie")
class OllieUpdateController(
    private val ollieUpdateService: OllieUpdateService
) : BaseController() {

    @Operation(
        summary = "올리 이름 변경",
        description = "사용자 올리의 이름을 변경합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @ChangeNameApiResponses
    @PatchMapping("/name")
    fun changeOllieName(
        @Valid @RequestBody olliePatchDto: OlliePatchDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = ollieUpdateService.changeOllieName(olliePatchDto, loginMemberId)
        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    @Operation(
        summary = "올리의 아이템 착용/변경",
        description = "올리의 아이템을 착용 및 변경합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @ChangeItemApiResponses
    @PatchMapping("/item")
    fun changeWearItem(
        @Valid @RequestBody wearItemPatchDto: WearItemPatchDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val responses = ollieUpdateService.changeWearItem(wearItemPatchDto, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(responses))
    }

    @Operation(
        summary = "올리의 착용중인 아이템 해제",
        description = "올리의 착용 아이템을 해제합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @ChangeItemApiResponses
    @PatchMapping("/take-off/item")
    fun takeOffWearItem(
        @Valid @RequestBody wearItemPatchDto: WearItemPatchDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = ollieUpdateService.takeOffWearItem(wearItemPatchDto, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    @Operation(
        summary = "올리의 칭호 사용/변경",
        description = "올리의 칭호를 사용 및 변경합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @ChangeTitleApiResponses
    @PatchMapping("/title")
    fun changeSelectTitle(
        @Valid @RequestBody selectTitlePatchDto: SelectTitlePatchDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = ollieUpdateService.changeSelectTitle(selectTitlePatchDto, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    @Operation(
        summary = "올리의 사용중인 칭호 해제",
        description = "올리의 사용중인 칭호를 해제합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @ChangeTitleApiResponses
    @PatchMapping("/remove/title")
    fun removeSelectTitle(
        @Valid @RequestBody selectTitlePatchDto: SelectTitlePatchDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = ollieUpdateService.removeSelectTitle(selectTitlePatchDto, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }
}