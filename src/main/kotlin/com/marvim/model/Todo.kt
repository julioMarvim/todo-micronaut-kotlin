package com.marvim.model

import io.micronaut.core.annotation.Introspected
import javax.persistence.*

@Entity
@Introspected
data class Todo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    @Column
    val description: String,
    @Column
    val done: Boolean,
)

