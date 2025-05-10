package com.richbasoft.ollie.domain.ollie.service

import com.richbasoft.ollie.domain.ollie.dto.OllieItemInfoDto
import com.richbasoft.ollie.domain.ollie.dto.OllieItemPostDto
import com.richbasoft.ollie.domain.ollie.dto.OllieTitleInfoDto

interface OllieCreateService {
    fun createOllieTitleList(titleIdList: List<Long>, memberId: Long): List<OllieTitleInfoDto>
    fun createOllieItem(ollieItemPostDto: OllieItemPostDto, memberId: Long): OllieItemInfoDto
}