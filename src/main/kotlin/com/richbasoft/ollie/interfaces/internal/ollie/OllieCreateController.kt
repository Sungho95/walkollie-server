package com.richbasoft.ollie.interfaces.internal.ollie

import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.OllieItemCreateApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.OllieTitleCreateApiResponses
import com.richbasoft.ollie.domain.ollie.dto.OllieItemPostDto
import com.richbasoft.ollie.domain.ollie.service.OllieCreateService
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

@Tag(name = "Ollie", description = "올리 API")
@RestController
@RequestMapping("/api/v1/ollie")
class OllieCreateController(
    private val ollieCreateService: OllieCreateService
) {

    @Operation(
        summary = "올리 칭호 획득",
        description = "사용자가 걸음 수 조건에 부합하는 칭호를 얻습니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @OllieTitleCreateApiResponses
    @PostMapping("/title")
    fun createOllieTitleList(
        @Valid @RequestBody titleIdList: List<Long>,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val responses = ollieCreateService.createOllieTitleList(titleIdList, loginMemberId)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(MultiResponse(responses))
    }

    @Operation(
        summary = "올리 아이템 구매",
        description = "사용자가 포인트를 사용하여 아이템을 구매합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @OllieItemCreateApiResponses
    @PostMapping("/item")
    fun createOllieItem(
        @Valid @RequestBody ollieItemPostDto: OllieItemPostDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = ollieCreateService.createOllieItem(ollieItemPostDto, loginMemberId)

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(SingleResponse(response))
    }
}