package com.rolly

import org.mindrot.jbcrypt.BCrypt

class UserMysqlModel(val pool: ConnectionPool) : UserModel {

    override fun logUser(username: String, password: String) : Boolean {
        pool.useConnection { conn ->
            conn.prepareStatement("SELECT * FROM user WHERE username = ?").use { stmt ->
                stmt.setString(1, username)
                stmt.execute()
                val rs = stmt.resultSet

                if (rs.next()) {
                    val hash = rs.getString("password")
                    val pass = BCrypt.hashpw("password", BCrypt.gensalt(12))
                    //Vérification du mot de passe avec Bcrypt
                    //if (BCrypt.checkpw(password, hash)) {
                    if(password == hash){
                        return true
                    }
                }
            }
        }
        return false
    }
}