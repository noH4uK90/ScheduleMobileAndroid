package com.example.schedulemobileandroid.models

data class Grouped<TKey, TItem>(
    val key: TKey,
    val items: List<TItem>
)