package com.richbasoft.ollie.admin.domain.inquiry.repository

import com.richbasoft.ollie.domain.inquiry.entity.Answer
import com.richbasoft.ollie.admin.domain.inquiry.repository.dsl.CustomAdminAnswerRepository
import org.springframework.data.jpa.repository.JpaRepository

interface AdminAnswerRepository : JpaRepository<Answer, Long>, CustomAdminAnswerRepository {
}