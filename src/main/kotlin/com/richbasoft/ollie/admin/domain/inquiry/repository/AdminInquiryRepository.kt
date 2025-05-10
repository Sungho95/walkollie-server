package com.richbasoft.ollie.admin.domain.inquiry.repository

import com.richbasoft.ollie.admin.domain.inquiry.repository.dsl.CustomAdminInquiryRepository
import com.richbasoft.ollie.domain.inquiry.entity.Inquiry
import org.springframework.data.jpa.repository.JpaRepository

interface AdminInquiryRepository : JpaRepository<Inquiry, Long>, CustomAdminInquiryRepository {
}