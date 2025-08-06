package com.alituran.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.util.Date

@Entity
data class RefreshToken(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? =null,

    var refreshToken: String?=null,

    var expireDate: Date?=null,

    @ManyToOne(fetch = FetchType.LAZY)
    var user: User?=null
)