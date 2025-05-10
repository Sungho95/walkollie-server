package com.richbasoft.ollie.domain.title.service

import com.richbasoft.ollie.domain.ollie.repository.OllieTitleRepository
import com.richbasoft.ollie.domain.title.dto.TitleInfoDto
import com.richbasoft.ollie.domain.title.dto.TitleSearchConditionDto
import com.richbasoft.ollie.domain.title.entity.Title
import com.richbasoft.ollie.domain.title.repository.TitleRepository
import com.richbasoft.ollie.common.exception.BusinessLogicException
import com.richbasoft.ollie.common.exception.ExceptionCode
import com.richbasoft.ollie.domain.calendar.repository.CalendarRepository
import com.richbasoft.ollie.domain.member.utils.MemberValidationUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class TitleReadServiceImpl(
    private val titleRepository: TitleRepository,
    private val ollieTitleRepository: OllieTitleRepository,
    private val memberValidationUtils: MemberValidationUtils,
    private val calendarRepository: CalendarRepository
) : TitleReadService {
    override fun getTitleInfoList(titleSearchCondition: TitleSearchConditionDto, memberId: Long): List<TitleInfoDto> {
        val findTitleInfoList = titleRepository.findTitleInfoList(titleSearchCondition, memberId)
        return findTitleInfoList
    }

    override fun getTitleInfoDetail(titleId: Long, memberId: Long): TitleInfoDto {
        val findTitle = getVerifiedTitle(titleId)
        val ollieTitle = ollieTitleRepository.findOllieTitle(titleId, memberId)
        return TitleInfoDto.from(findTitle, ollieTitle)
    }

    override fun getObtainableTitleList(
        memberId: Long,
        loginMemberId: Long,
        lastCalendarDate: LocalDate
    ): List<TitleInfoDto> {
        memberValidationUtils.verifyMemberIdentity(memberId, loginMemberId)
        val findCalendarInfoList = calendarRepository.findAllByLastCalendarDate(memberId, lastCalendarDate)
        val findTotalSteps = calendarRepository.findTotalStepsByMemberId(memberId)
        val findTitleInfoList = titleRepository.findObtainableTitleList(memberId, findCalendarInfoList, findTotalSteps)
        return findTitleInfoList
    }

    private fun getVerifiedTitle(titleId: Long): Title {
        return titleRepository.findByIdOrNull(titleId)
            ?: throw BusinessLogicException(ExceptionCode.TITLE_NOT_FOUND)
    }
}