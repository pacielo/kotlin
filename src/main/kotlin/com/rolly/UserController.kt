package com.rolly

interface UserController {
    fun logUser(username: String, password: String) : Boolean
}