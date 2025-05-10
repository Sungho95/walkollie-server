package com.richbasoft.ollie.domain.ollie.service

import com.richbasoft.ollie.domain.ollie.dto.OllieInfoDto
import com.richbasoft.ollie.domain.ollie.dto.SelectedTitleInfoDto
import com.richbasoft.ollie.domain.ollie.dto.WornItemInfoDto

interface OllieReadService {
    fun getOllieInfo(memberId: Long, loginMemberId: Long): OllieInfoDto

    fun getWornItemList(memberId: Long, loginMemberId: Long): List<WornItemInfoDto>

    fun getSelectedTitleList(memberId: Long, loginMemberId: Long): List<SelectedTitleInfoDto>
}