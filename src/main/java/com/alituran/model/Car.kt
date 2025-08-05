package com.alituran.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import java.math.BigDecimal

@Entity
data class Car(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long?=null,

    var color:String?=null,

    var price: BigDecimal?=null,

    var plateName:String?=null,

    var fuelType:String?=null,

    var mileAge: Int?=null,

    @ManyToOne(fetch = FetchType.LAZY)
    var carModel:CarModel?=null

)
