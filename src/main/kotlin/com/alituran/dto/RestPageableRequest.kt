package com.alituran.dto

data class RestPageableRequest(

    var pageNumber: Int,

    var pageSize: Int,

    var columnName:String,

    var isAsc:Boolean

)