package com.richbasoft.ollie.domain.ollie.repository.dsl

import com.richbasoft.ollie.domain.ollie.dto.SelectedTitleInfoDto
import com.richbasoft.ollie.domain.ollie.entity.OllieTitle
import com.richbasoft.ollie.domain.title.dto.TitleSearchConditionDto
import com.richbasoft.ollie.domain.title.entity.Title

interface CustomOllieTitleRepository {
    fun findSelectedTitleList(memberId: Long): List<SelectedTitleInfoDto>

    fun findOllieTitle(titleId: Long, memberId: Long): OllieTitle?

    fun findSelectedTitle(titleType: Title.Type, memberId: Long): OllieTitle?

    fun findOllieTitleList(titleSearchCondition: TitleSearchConditionDto, memberId: Long): List<OllieTitle>

    fun bulkInsertOllieTitleList(ollieTitleList: List<OllieTitle>)
}