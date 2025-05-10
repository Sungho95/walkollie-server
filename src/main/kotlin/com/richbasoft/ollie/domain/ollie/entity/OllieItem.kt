package com.richbasoft.ollie.domain.ollie.entity

import com.richbasoft.ollie.domain.item.entity.Item
import com.richbasoft.ollie.common.base.entity.BaseTime
import jakarta.persistence.*

@Entity
class OllieItem(
    ollie: Ollie,
    item: Item
) : BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column
    var acquired: Boolean = true
        protected set

    @Column
    var worn: Boolean = false
        protected set

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ollie_id")
    var ollie: Ollie = ollie
        protected set

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    var item: Item = item
        protected set

    fun assignOllie(ollie: Ollie) {
        this.ollie = ollie
    }

    fun changeWorn(worn: Boolean) {
        this.worn = worn
    }

    override fun toString(): String {
        return "OllieItem(id=$id, acquired=$acquired, worn=$worn)"
    }
}
