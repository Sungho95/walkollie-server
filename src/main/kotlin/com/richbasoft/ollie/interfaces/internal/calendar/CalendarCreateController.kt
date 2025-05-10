package com.richbasoft.ollie.interfaces.internal.calendar

import com.richbasoft.ollie.common.base.controller.BaseController
import com.richbasoft.ollie.common.base.dto.MultiResponse
import com.richbasoft.ollie.common.security.auth.loginresolver.LoginMemberId
import com.richbasoft.ollie.common.utils.annotation.swagger.CalendarCreateApiResponses
import com.richbasoft.ollie.domain.calendar.dto.CalendarPostDto
import com.richbasoft.ollie.domain.calendar.service.CalendarCreateService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Calendar", description = "캘린더 API")
@RestController
@RequestMapping("/api/v1/calendar")
class CalendarCreateController(
    private val calendarCreateService: CalendarCreateService
) : BaseController() {

    @Operation(
        summary = "캘린더 리스트 생성",
        description = "날짜별 걸음수 리스트를 받아 마지막 데이터 이후부터 어제까지의 캘린더 정보를 생성합니다.",
        security = [SecurityRequirement(name = "bearerAuth")]
    )
    @CalendarCreateApiResponses
    @PostMapping("/list")
    fun createCalendarList(
        @LoginMemberId loginMemberId: Long,
        @RequestBody calendarPostDtoList: List<CalendarPostDto>
    ): ResponseEntity<*> {
        val responses = calendarCreateService.createCalendarList(loginMemberId, calendarPostDtoList)
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(MultiResponse(responses))
    }
}