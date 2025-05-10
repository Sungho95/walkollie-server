package com.richbasoft.ollie.domain.inquiry.entity

import com.richbasoft.ollie.common.base.entity.BaseTime
import com.richbasoft.ollie.domain.ollie.entity.Ollie
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity
@SQLDelete(sql = "UPDATE ollie SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
class Inquiry(
    title: String,
    content: String,
    category: Category,
    image: String,
    ollie: Ollie
) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(length = 30, nullable = false)
    var title: String = title
        protected set

    @Column(length = 255, nullable = false)
    var content: String = content
        protected set

    @Column(length = 8, nullable = false)
    @Enumerated(EnumType.STRING)
    var category: Category = category
        protected set

    @Column(nullable = true)
    var image: String = image
        protected set

    @Column(nullable = false)
    var isAnswered: Boolean = false
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ollie_id")
    var ollie: Ollie = ollie
        protected set

    @Column(nullable = false)
    var isDeleted: Boolean = false
        protected set

    enum class Category(
        val value: String
    ) {
        ALL("전체"),
        ACCOUNT("계정 관련 문의"),
        USE("이용 문의"),
        ERROR("버그/오류 제보"),
        SUGGEST("앱 개선사항"),
        OTHERS("기타")
    }

    fun updateInquiry(title: String, content: String, category: String, image: String?) {
        this.title = title
        this.content = content
        this.category = Category.valueOf(category)
        this.image = image ?: ""
    }

    fun updateIsAnswered(isAnswered: Boolean) {
        this.isAnswered = isAnswered
    }
}