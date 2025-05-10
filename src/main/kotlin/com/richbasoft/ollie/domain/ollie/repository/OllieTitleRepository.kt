package com.richbasoft.ollie.domain.ollie.repository

import com.richbasoft.ollie.domain.ollie.entity.OllieTitle
import com.richbasoft.ollie.domain.ollie.repository.dsl.CustomOllieTitleRepository
import org.springframework.data.jpa.repository.JpaRepository

interface OllieTitleRepository : JpaRepository<OllieTitle, Long>, CustomOllieTitleRepository {
}