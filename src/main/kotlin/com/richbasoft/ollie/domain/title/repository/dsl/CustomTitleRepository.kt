package com.richbasoft.ollie.domain.title.repository.dsl

import com.richbasoft.ollie.domain.calendar.dto.CalendarInfoDto
import com.richbasoft.ollie.domain.title.dto.TitleInfoDto
import com.richbasoft.ollie.domain.title.dto.TitleSearchConditionDto
import com.richbasoft.ollie.domain.title.entity.Title

interface CustomTitleRepository {
    fun findTitleList(titleSearchCondition: TitleSearchConditionDto, memberId: Long): List<Title>
    fun findTitleInfoList(titleSearchCondition: TitleSearchConditionDto, memberId: Long): List<TitleInfoDto>
    fun findCreateTargetTitleList(titleIdList: List<Long>): List<Title>
    fun findObtainableTitleList(
        memberId: Long,
        calendarInfoList: List<CalendarInfoDto>,
        totalSteps: Long
    ): List<TitleInfoDto>
}