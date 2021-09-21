package com.marvim.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.validation.constraints.Size

@Entity
data class User(
    @Id
    val cpf: Long,
    @Column
    @Size(min= 6)
    val password: String,
)
