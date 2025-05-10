package com.richbasoft.ollie.domain.ollie.repository;

import com.richbasoft.ollie.domain.ollie.entity.Ollie
import com.richbasoft.ollie.domain.ollie.repository.dsl.CustomOllieRepository
import org.springframework.data.jpa.repository.JpaRepository

interface OllieRepository : JpaRepository<Ollie, Long>, CustomOllieRepository {
}
