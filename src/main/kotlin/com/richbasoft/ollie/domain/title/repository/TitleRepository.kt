package com.richbasoft.ollie.domain.title.repository

import com.richbasoft.ollie.domain.title.entity.Title
import com.richbasoft.ollie.domain.title.repository.dsl.CustomTitleRepository
import org.springframework.data.jpa.repository.JpaRepository

interface TitleRepository : JpaRepository<Title, Long>, CustomTitleRepository {
}