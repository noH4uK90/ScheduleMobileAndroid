package com.example.schedulemobileandroid.models

data class PagedList<T>(
    val pageSize: Int,
    val pageNumber: Int,
    val totalCount: Int,
    val totalPages: Int,
    val items: List<T>
)
