package com.rolly

class UserControllerImpl(val model: UserModel) : UserController {

    override fun logUser(username: String, password: String) : Boolean {
        if (username.isNotBlank() && password.isNotBlank())
            return model.logUser(username, password)
        return false
    }
}