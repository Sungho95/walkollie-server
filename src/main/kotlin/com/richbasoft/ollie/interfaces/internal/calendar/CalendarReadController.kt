package com.richbasoft.ollie.interfaces.internal.calendar

import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.base.dto.SingleResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.CalendarListReadApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.CalendarReadApiResponses
import com.richbasoft.ollie.common.utils.annotation.swagger.TotalStepsReadApiResponses
import com.richbasoft.ollie.domain.calendar.service.CalendarReadService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Calendar", description = "캘린더 API")
@RestController
@RequestMapping("/api/v1/calendar")
class CalendarReadController(
    private val calendarReadService: CalendarReadService
) : BaseController() {

    @Operation(
        summary = "마지막 캘린더 정보 조회",
        description = "사용자의 마지막 캘린더 정보를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @CalendarReadApiResponses
    @GetMapping("/last-info")
    fun getLastCalendarInfo(@LoginMemberId loginMemberId: Long): ResponseEntity<*> {
        val response = calendarReadService.getLastCalendarInfo(loginMemberId)

        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(response))
    }

    @Operation(
        summary = "월별 캘린더 정보 조회",
        description = "해당하는 월의 캘린더 정보 리스트를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @CalendarListReadApiResponses
    @GetMapping("/monthly")
    fun getMonthlyCalendar(
        @LoginMemberId loginMemberId: Long,
        @RequestParam year: Int,
        @RequestParam month: Int
    ): ResponseEntity<*> {
        val responses = calendarReadService.getMonthlyCalendar(loginMemberId, year, month)
        return ResponseEntity.status(HttpStatus.OK)
            .body(MultiResponse(responses))
    }

    @Operation(
        summary = "총 걸음수 조회",
        description = "사용자의 총 걸음수를 조회합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @TotalStepsReadApiResponses
    @GetMapping("/steps/total")
    fun getTotalSteps(@LoginMemberId loginMemberId: Long): ResponseEntity<*> {
        val totalSteps = calendarReadService.getTotalSteps(loginMemberId)
        return ResponseEntity.status(HttpStatus.OK)
            .body(SingleResponse(totalSteps))
    }
}