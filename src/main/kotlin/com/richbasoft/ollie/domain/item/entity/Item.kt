package com.richbasoft.ollie.domain.item.entity

import com.richbasoft.ollie.common.base.entity.BaseTime
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity
@SQLDelete(sql = "UPDATE item SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
class Item(
    name: String,
    description: String,
    type: Type,
    image: String,
    price: Int
) : BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(length = 20, nullable = false)
    var name: String = name
        protected set

    @Column(length = 120, nullable = false)
    var description: String = description
        protected set

    @Column(length = 5, nullable = false)
    var type: Type = type
        protected set

    @Column(length = 255, nullable = false)
    var image: String = image
        protected set

    @Column(nullable = false)
    var price: Int = price
        protected set

    @Column(nullable = false)
    var isDeleted: Boolean = false
        protected set

    enum class Type(
        val type: String
    ) {
        ALL("전체"),
        HEAD("머리"),
        EYES("눈"),
        EAR("귀"),
        MOUTH("입"),
        CHEEK("볼"),
    }

    fun updateItem(
        name: String,
        description: String,
        type: String,
        image: String
    ) {
        this.name = name
        this.description = description
        this.type = Item.Type.valueOf(type)
        this.image = image
    }

    override fun toString(): String {
        return "Item(id=$id, name='$name', description='$description', type=$type, image='$image', isDeleted=$isDeleted)"
    }
}
