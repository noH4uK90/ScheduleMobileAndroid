package com.example.schedulemobileandroid.data.mappers

import com.example.schedulemobileandroid.models.Account
import com.example.schedulemobileandroid.models.FullAccount

fun FullAccount.toAccount(): Account {
    return Account(
        id = id,
        name = name,
        surname = surname,
        middleName = middleName,
        email = email,
        role = role
    )
}