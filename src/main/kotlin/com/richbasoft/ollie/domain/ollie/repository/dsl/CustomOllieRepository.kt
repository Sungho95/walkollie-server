package com.richbasoft.ollie.domain.ollie.repository.dsl

import com.richbasoft.ollie.domain.ollie.dto.OllieInfoDto

interface CustomOllieRepository {
    fun findOllieInfo(memberId: Long): OllieInfoDto
}