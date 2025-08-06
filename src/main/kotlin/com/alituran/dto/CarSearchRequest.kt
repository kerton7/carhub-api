package com.alituran.dto

data class CarSearchRequest(

    var model: String?=null,
    var brand: String?=null,
    var fuelType: String?=null,
    var pageable: RestPageableRequest

)