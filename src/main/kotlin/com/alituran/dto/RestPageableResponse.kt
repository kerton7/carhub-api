package com.alituran.dto

data class RestPageableResponse<T>(

    var pageSize: Int,

    var pageNumber: Int,

    var totalPages: Int,

    var content:List<T>

)