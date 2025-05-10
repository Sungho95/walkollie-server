package com.richbasoft.ollie.domain.ollie.entity

import com.richbasoft.ollie.domain.member.entity.Member
import com.richbasoft.ollie.common.base.entity.BaseTime
import com.richbasoft.ollie.domain.ollie.enums.Status
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where

@Entity
@SQLDelete(sql = "UPDATE ollie SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
class Ollie(
    member: Member
) : BaseTime() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column(length = 10, nullable = false)
    var name: String = "올리"
        protected set

    @Column(nullable = false)
    var score: Int = 0
        protected set

    @Column(length = 6, nullable = false)
    @Enumerated(EnumType.STRING)
    var status: Status = Status.NORMAL
        protected set

    @Column(nullable = false)
    var point: Long = 0L
        protected set

    @MapsId
    @OneToOne
    @JoinColumn(name = "id")
    var member: Member = member
        protected set

    @OneToMany(mappedBy = "ollie", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val mutableOllieItems: MutableSet<OllieItem> = mutableSetOf()
    val ollieItems: Set<OllieItem> get() = mutableOllieItems.toSet()

    @OneToMany(mappedBy = "ollie", cascade = [CascadeType.ALL], orphanRemoval = true)
    protected val mutableOllieTitles: MutableSet<OllieTitle> = mutableSetOf()
    val ollieTitles: Set<OllieTitle> get() = mutableOllieTitles.toSet()

    @Column(nullable = false)
    var isDeleted: Boolean = false
        protected set

    fun purchaseItem(ollieItem: OllieItem, price: Int) {
        deductingPoint(price)
        ollieItem.assignOllie(this)
        mutableOllieItems.add(ollieItem)
    }

    fun addTitle(ollieTitle: OllieTitle) {
        ollieTitle.assignOllie(this)
        mutableOllieTitles.add(ollieTitle)
    }

    fun changeName(name: String) {
        this.name = name
    }

    fun updatePointAndScore(earnPoint: Int, score: Int) {
        this.point += earnPoint
        this.score += score
    }

    fun deductingPoint(price: Int) {
        this.point -= price
    }
}
