package com.richbasoft.ollie.domain.ollie.repository

import com.richbasoft.ollie.domain.ollie.entity.OllieItem
import com.richbasoft.ollie.domain.ollie.repository.dsl.CustomOllieItemRepository
import org.springframework.data.jpa.repository.JpaRepository

interface OllieItemRepository : JpaRepository<OllieItem, Long>, CustomOllieItemRepository {
}