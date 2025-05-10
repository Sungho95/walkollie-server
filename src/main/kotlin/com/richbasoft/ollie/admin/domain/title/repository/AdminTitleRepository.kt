package com.richbasoft.ollie.admin.domain.title.repository

import com.richbasoft.ollie.admin.domain.title.repository.dsl.CustomAdminTitleRepository
import com.richbasoft.ollie.domain.title.entity.Title
import org.springframework.data.jpa.repository.JpaRepository

interface AdminTitleRepository : JpaRepository<Title, Long>, CustomAdminTitleRepository {
}