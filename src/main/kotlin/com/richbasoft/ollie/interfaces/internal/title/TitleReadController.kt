package com.richbasoft.ollie.interfaces.internal.title

import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.domain.title.service.TitleReadService
import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.domain.title.dto.TitleSearchConditionDto
import com.richbasoft.ollie.common.utils.annotation.swagger.TitleReadApiResponses
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@Tag(name = "Title", description = "칭호 API")
@RestController
@RequestMapping("/api/v1/title")
class TitleReadController(
    private val titleReadService: TitleReadService
) : BaseController() {

    @Operation(summary = "사용자 칭호 정보 리스트 조회", description = "사용자의 칭호 정보 리스트를 조회합니다.")
    @TitleReadApiResponses
    @GetMapping
    fun getTitleInfoList(
        titleSearchCondition: TitleSearchConditionDto,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = titleReadService.getTitleInfoList(titleSearchCondition, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(response))
    }

    @Operation(summary = "사용자 칭호 정보 조회", description = "사용자의 칭호 아이디(titleId)를 통해 칭호의 정보를 조회합니다.")
    @TitleReadApiResponses
    @GetMapping("/info/{title-id}")
    fun getTitleInfo(
        @PathVariable("title-id") titleId: Long,
        @LoginMemberId loginMemberId: Long
    ): ResponseEntity<*> {
        val response = titleReadService.getTitleInfoDetail(titleId, loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    @Operation(summary = "획득 가능한 칭호 조회", description = "사용자의 획득 가능한 칭호 리스트를 조회합니다.")
    @TitleReadApiResponses
    @GetMapping("/obtainable/{member-id}")
    fun getObtainableTitle(
        @PathVariable("member-id") memberId: Long,
        @LoginMemberId loginMemberId: Long,
        @RequestParam lastCalendarDate: LocalDate
    ): ResponseEntity<*> {
        val responses = titleReadService.getObtainableTitleList(memberId, loginMemberId, lastCalendarDate)
        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(responses))
    }
}