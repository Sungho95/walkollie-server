package com.richbasoft.ollie.domain.notification.entity

import com.richbasoft.ollie.common.base.entity.BaseTime
import jakarta.persistence.*

@Entity
class Notification(
    receiverId: Long,
    title: String,
    body: String,
    firebase: String,
    type: Type
) : BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column
    var receiverId: Long = receiverId
        protected set

    @Column
    var title: String = title
        protected set

    @Column
    var body: String = body
        protected set

    @Column
    var firebase: String = firebase
        protected set

    @Column
    @Enumerated(EnumType.STRING)
    var type: Type = type
        protected set

    enum class Type{
        REMINDER,
        TITLE,
        ITEM,
        NOTICE
    }
}