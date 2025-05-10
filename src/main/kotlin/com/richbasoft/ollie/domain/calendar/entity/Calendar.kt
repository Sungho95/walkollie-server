package com.richbasoft.ollie.domain.calendar.entity

import com.richbasoft.ollie.domain.ollie.entity.Ollie
import com.richbasoft.ollie.common.base.entity.BaseTime
import com.richbasoft.ollie.domain.calendar.enums.Status
import jakarta.persistence.*
import java.time.LocalDate

@Entity
class Calendar(
    steps: Int,
    date: LocalDate,
    ollie: Ollie
) : BaseTime() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    @Column
    var steps: Int = steps
        protected set

    @Column
    var date: LocalDate = date
        protected set

    @Column
    var earnPoint: Int = Status.stepsToPoint(steps)
        protected set

    @Column
    @Enumerated(EnumType.STRING)
    var status: Status = Status.fromSteps(steps)
        protected set

    @ManyToOne
    @JoinColumn(name = "ollie_id")
    var ollie: Ollie = ollie
        protected set
}