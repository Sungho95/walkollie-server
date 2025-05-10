package com.richbasoft.ollie.domain.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
data class NotificationConsent(
    @Column
    var pushNotification: Boolean,

    @Column
    var marketingNotification: Boolean,

    @Column
    var accessToken: String
)