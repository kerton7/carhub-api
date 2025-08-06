package com.alituran.model

import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
data class CarModel(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null,

    var modelName:String?=null,

    var modelYear:Int?=null,

    @ManyToOne(fetch = FetchType.LAZY)
    var brand: Brand?=null

)