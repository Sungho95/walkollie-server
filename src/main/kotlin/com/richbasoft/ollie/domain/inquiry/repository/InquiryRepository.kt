package com.richbasoft.ollie.domain.inquiry.repository

import com.richbasoft.ollie.domain.inquiry.entity.Inquiry
import com.richbasoft.ollie.domain.inquiry.repository.dsl.CustomInquiryRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface InquiryRepository : JpaRepository<Inquiry, Long>, CustomInquiryRepository {

}