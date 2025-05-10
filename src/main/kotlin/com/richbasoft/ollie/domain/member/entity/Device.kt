package com.richbasoft.ollie.domain.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class Device(
    @Column
    var os: String,

    @Column
    var osVersion: String,

    @Column
    var deviceModel: String,
)