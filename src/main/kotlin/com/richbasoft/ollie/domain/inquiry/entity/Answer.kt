package com.richbasoft.ollie.domain.inquiry.entity

import com.richbasoft.ollie.common.base.entity.BaseTime
import com.richbasoft.ollie.domain.ollie.entity.Ollie
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity
@SQLDelete(sql = "UPDATE ollie SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
class Answer(
    content: String,
    image: String,
    inquiry: Inquiry,
    ollie: Ollie
) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(nullable = false)
    var content: String = content
        protected set

    @Column(nullable = false)
    var image: String = image
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    var inquiry: Inquiry = inquiry
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    var admin: Ollie = ollie
        protected set

    fun updateAnswer(content: String, image: String) {
        this.content = content
        this.image = image
    }
}