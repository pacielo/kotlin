package com.rolly

import com.rolly.Route.registerAdminArticleRoutes
import com.rolly.Route.registerArticleRoutes
import com.rolly.Route.registerCommentRoutes
import com.rolly.Route.registerLoginRoutes
import io.ktor.application.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import freemarker.cache.*

import io.ktor.freemarker.*
import io.ktor.http.content.resources
import io.ktor.http.content.static

import io.ktor.sessions.*



fun main(args: Array<String>) {

    val pool = ConnectionPool("jdbc:mysql://localhost:8889/kotlin?serverTimezone=UTC", "root", "root")

    val articleModel: ArticleModel = ArticleMysqlModel(pool)
    val articleController: ArticleController = ArticleControllerImpl(articleModel)

    val adminArticleModel: AdminArticleModel = AdminArticleMysqlModel(pool)
    val adminArticleController: AdminArticleController = AdminArticleControllerImpl(adminArticleModel)

    val commentModel: CommentModel = CommentMysqlModel(pool)
    val commentController: CommentController = CommentControllerImpl(commentModel)

    val userModel: UserModel = UserMysqlModel(pool)
    val userController: UserController = UserControllerImpl(userModel)

    val server = embeddedServer(Netty, port = System.getenv("PORT")?.toInt() ?: 8080) {

        install(FreeMarker) {
            templateLoader = ClassTemplateLoader(this::class.java.classLoader, "templates")
        }

        install(Sessions) {
            cookie<UserSession>("SESSION"){
                cookie.path = "/"
            }

        }

        routing {
            registerArticleRoutes(articleController)
            registerAdminArticleRoutes(adminArticleController)
            registerCommentRoutes(commentController)
            registerLoginRoutes(userController)

            //Static ressources (like css files)
            static("/static") {
                resources("static")
            }
        }

    }
    server.start(wait = true)
}
