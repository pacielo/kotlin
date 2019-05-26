package com.rolly


import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import com.rolly.ConnectionPool
import com.rolly.UserMysqlModel
import junit.framework.Assert
import org.mindrot.jbcrypt.BCrypt

class UserModelTests {
    @After
    fun clear(){
        val pool = ConnectionPool("jdbc:h2:mem:testd;MODE=MYSQL", null, null)
        pool.useConnection { connection ->
            connection.prepareStatement("DROP TABLE user; ").use { it.execute() }
        }
    }

    @Test
    fun logValidUser(){
        val pool = ConnectionPool("jdbc:h2:mem:testd;MODE=MYSQL", null, null)
        val model = UserMysqlModel(pool)

        pool.useConnection { connection ->

            connection.prepareStatement("CREATE TABLE user (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  username TEXT NOT NULL,\n" +
                    "  password TEXT NOT NULL,\n" +
                    "PRIMARY KEY (`id`));"
            ).use { it.execute() }


            val pass = BCrypt.hashpw("password", BCrypt.gensalt(12))
            connection.prepareStatement("INSERT INTO user (id, username, password) VALUES (1, 'admin', '$pass');").use { it.execute() }
            val res = model.logUser("admin", "password")

            assertEquals(true, res)
        }
    }
    @Test
    fun logInvalidMdp(){
        val pool = ConnectionPool("jdbc:h2:mem:testd;MODE=MYSQL", null, null)
        val model = UserMysqlModel(pool)

        pool.useConnection { connection ->


            connection.prepareStatement("CREATE TABLE user (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  username TEXT NOT NULL,\n" +
                    "  password TEXT NOT NULL,\n" +
                    "PRIMARY KEY (`id`));"
            ).use { it.execute() }

            val pass = BCrypt.hashpw("password", BCrypt.gensalt(12))
            connection.prepareStatement("INSERT INTO user (id, username, password) VALUES (1, 'admin', '$pass');").use { it.execute() }
            val res = model.logUser("admin", "pass")


            assertEquals(false, res)
        }
    }
    @Test
    fun logInvalidUsername(){
        val pool = ConnectionPool("jdbc:h2:mem:testd;MODE=MYSQL", null, null)
        val model = UserMysqlModel(pool)

        pool.useConnection { connection ->

            connection.prepareStatement("CREATE TABLE user (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  username TEXT NOT NULL,\n" +
                    "  password TEXT NOT NULL,\n" +
                    "PRIMARY KEY (`id`));"
            ).use { it.execute() }

            val pass = BCrypt.hashpw("password", BCrypt.gensalt(12))
            connection.prepareStatement("INSERT INTO user (id, username, password) VALUES (1, 'admin', '$pass');").use { it.execute() }
            val res = model.logUser("admi", "password")

            assertEquals(false, res)
        }
    }
    @Test
    fun logInvalidUser(){
        val pool = ConnectionPool("jdbc:h2:mem:testd;MODE=MYSQL", null, null)
        val model = UserMysqlModel(pool)

        pool.useConnection { connection ->

            connection.prepareStatement("CREATE TABLE user (\n" +
                    "  id INT NOT NULL AUTO_INCREMENT,\n" +
                    "  username TEXT NOT NULL,\n" +
                    "  password TEXT NOT NULL,\n" +
                    "PRIMARY KEY (`id`));"
            ).use { it.execute() }

            val pass = BCrypt.hashpw("password", BCrypt.gensalt(12))
            connection.prepareStatement("INSERT INTO user (id, username, password) VALUES (1, 'admin', '$pass');").use { it.execute() }
            val res = model.logUser("admi", "pass")

            assertEquals(false, res)
        }
    }

}