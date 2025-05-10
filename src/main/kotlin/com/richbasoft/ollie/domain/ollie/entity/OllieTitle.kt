package com.richbasoft.ollie.domain.ollie.entity

import com.richbasoft.ollie.domain.title.entity.Title
import com.richbasoft.ollie.common.base.entity.BaseTime
import jakarta.persistence.*

@Entity
class OllieTitle(
    ollie: Ollie,
    title: Title
) : BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column
    var acquired: Boolean = true
        protected set

    @Column
    var selected: Boolean = false
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ollie_id")
    var ollie: Ollie = ollie
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "title_id")
    var title: Title = title
        protected set

    fun assignOllie(ollie: Ollie) {
        this.ollie = ollie
    }

    fun changeSelected(selected: Boolean) {
        this.selected = selected
    }
}
