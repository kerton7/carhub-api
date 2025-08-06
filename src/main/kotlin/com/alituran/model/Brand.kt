package com.alituran.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Brand (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null,

    var brandName:String?=null,

    var country:String?=null






)