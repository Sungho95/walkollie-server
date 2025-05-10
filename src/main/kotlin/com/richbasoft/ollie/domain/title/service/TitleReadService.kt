package com.richbasoft.ollie.domain.title.service

import com.richbasoft.ollie.domain.title.dto.TitleInfoDto
import com.richbasoft.ollie.domain.title.dto.TitleSearchConditionDto
import java.time.LocalDate

interface TitleReadService {
    fun getTitleInfoList(titleSearchCondition: TitleSearchConditionDto, memberId: Long): List<TitleInfoDto>
    fun getTitleInfoDetail(titleId: Long, memberId: Long): TitleInfoDto
    fun getObtainableTitleList(
        memberId: Long,
        loginMemberId: Long,
        lastCalendarDate: LocalDate
    ): List<TitleInfoDto>
}