package com.richbasoft.ollie.common.base.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseTime {

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    var modifiedAt: LocalDateTime = LocalDateTime.now()

    @PrePersist
    fun onPrePersist() {
        createdAt = LocalDateTime.now()
        modifiedAt = LocalDateTime.now()
    }

    @PreUpdate
    fun onPreUpdate() {
        modifiedAt = LocalDateTime.now()
    }
}