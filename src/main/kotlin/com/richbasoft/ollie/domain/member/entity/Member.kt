package com.richbasoft.ollie.domain.member.entity

import com.richbasoft.ollie.domain.member.enums.Gender
import com.richbasoft.ollie.domain.member.enums.Status
import com.richbasoft.ollie.common.base.entity.BaseTime
import com.richbasoft.ollie.common.security.auth.oauth2.attributes.OAuthProvider
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@SQLDelete(sql = "UPDATE member SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
class Member(
    deviceId: String,
    password: String,
    email: String,
    device: Device,
    notificationConsent: NotificationConsent
) : BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(length = 100, unique = true, nullable = false)
    var deviceId: String = deviceId
        protected set

    @Column(length = 320, unique = true, updatable = false)
    var email: String = email
        protected set

    @Column(length = 100, nullable = false)
    var password: String = password
        protected set

    @Column(length = 6, nullable = false)
    @Enumerated(EnumType.STRING)
    var gender: Gender = Gender.SECRET
        protected set

    @Column(nullable = false)
    var birthDate: LocalDate = LocalDate.of(1900, 1, 1)
        protected set

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    var roles: MutableList<String> = mutableListOf()
        protected set

    @Column
    @Enumerated(EnumType.STRING)
    var status: Status = Status.ACTIVE
        protected set

    @Column
    @Enumerated(EnumType.STRING)
    var oAuthProvider: OAuthProvider = OAuthProvider.NONE
        protected set

    @Embedded
    var device: Device = device
        protected set

    @Embedded
    var notificationConsent: NotificationConsent = notificationConsent
        protected set

    @Column(nullable = false)
    var isDeleted: Boolean = false
        protected set

    @Column(nullable = false)
    var lastLoggedInAt: LocalDateTime = LocalDateTime.now()

    fun setEncryptedPassword(encryptedPassword: String) {
        this.password = encryptedPassword
    }

    fun setAuthorityRoles(roles: MutableList<String>) {
        this.roles = roles
    }

    fun setOAuth2Provider(oAuthProvider: OAuthProvider) {
        this.oAuthProvider = oAuthProvider
    }

    fun setStatusWithdrawal() {
        status = Status.WITHDRAWAL
    }

    fun updateLastLoginTime() {
        lastLoggedInAt = LocalDateTime.now()
    }

    fun updateNotificationConsent(notificationConsent: NotificationConsent) {
        this.notificationConsent.pushNotification = notificationConsent.pushNotification
        this.notificationConsent.marketingNotification = notificationConsent.marketingNotification
        this.notificationConsent.accessToken = notificationConsent.accessToken
    }
}