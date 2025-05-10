package com.richbasoft.ollie.domain.title.entity

import com.richbasoft.ollie.common.base.entity.BaseTime
import jakarta.persistence.*
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity
@SQLDelete(sql = "UPDATE title SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
class Title(
    name: String,
    description: String,
    type: Type,
    category: Category,
    todayStep: Int,
    totalStep: Long
) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(length = 10, nullable = false)
    var name: String = name
        protected set

    @Column(length = 120, nullable = false)
    var description: String = description
        protected set

    @Column(length = 10, nullable = false)
    @Enumerated(value = EnumType.STRING)
    var type: Type = type
        protected set

    @Column(length = 10, nullable = false)
    @Enumerated(value = EnumType.STRING)
    var category: Category = category
        protected set

    @Column(nullable = false)
    var todayStep: Int = todayStep
        protected set

    @Column(nullable = false)
    var totalStep: Long = totalStep
        protected set

    @Column
    var isDeleted: Boolean = false
        protected set

    enum class Type(
        val type: String
    ) {
        ALL("전체"),
        ADJECTIVE("형용사"),
        NOUN("명사")
    }

    enum class Category(
        val value: String
    ) {
        ALL("전체"),
        DAILY("데일리"),
        CUMULATIVE("누적"),
        HIDDEN("히든")
    }

    fun updateTitle(
        name: String,
        description: String,
        type: String,
        category: String,
        todayStep: Int,
        totalStep: Long,
    ) {
        this.name = name
        this.description = description
        this.type = Title.Type.valueOf(type)
        this.category = Title.Category.valueOf(category)
        this.todayStep = todayStep
        this.totalStep = totalStep
    }
}
