package com.rolly

import com.rolly.SessionControllerImpl.sessionExists
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.response.respondRedirect
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.route
import io.ktor.sessions.*

object Route {
     fun Route.registerCommentRoutes(controller: CommentController) {
        post("/article/{articleId}/comment/{id}") {
            val commentId = call.parameters["id"]!!.toInt()
            val articleId = call.parameters["articleId"]!!.toInt()
            controller.deleteComment(commentId)
            call.respondRedirect("/article/$articleId")
        }
    }

     fun Route.registerArticleRoutes(controller: ArticleController) {
        route("/article/{id}") {
            get {
                val articleId = call.parameters["id"]!!.toInt()
                val article = controller.displayArticle(articleId)
                val session = call.sessions.get<UserSession>()
                if (article != null)
                    call.respond(FreeMarkerContent("article.ftl", mapOf("article" to article, "session" to sessionExists(session)), "e"))
                else
                    call.respond(HttpStatusCode.NotFound)
            }
            post{
                val articleId = call.parameters["id"]!!.toInt()
                val post = call.receiveParameters()
                val com  = post["comment"]!!
                controller.postComment(articleId, com)
                call.respondRedirect("/article/$articleId")
            }
        }


        get("/") {
            val articles = controller.getListArticle()
            val session = call.sessions.get<UserSession>()
            println(session)
            call.respond(FreeMarkerContent("index.ftl", mapOf("articles" to articles, "session" to sessionExists(session)), "e"))
        }
    }

     fun Route.registerAdminArticleRoutes(controller: AdminArticleController) {
        route("/article/post"){

            get {
                val session = call.sessions.get<UserSession>()
                if (sessionExists(session) != null) {
                    call.respond(FreeMarkerContent("post.ftl",  mapOf("session" to sessionExists(session))))
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                }
            }

            post {
                val session = call.sessions.get<UserSession>()
                if (sessionExists(session) != null) {
                    val post = call.receiveParameters()
                    val article  = post["article"]!!
                    controller.postArticle(article)
                    call.respondRedirect("/")
                } else {
                    call.respond(HttpStatusCode.Forbidden)
                }
            }
        }
        post("/article/delete/{id}") {
            val session = call.sessions.get<UserSession>()
            if (sessionExists(session) != null) {
                val articleId = call.parameters["id"]!!.toInt()
                controller.deleteArticle(articleId)
                call.respondRedirect("/")
            } else {
                call.respond(HttpStatusCode.Forbidden)
            }
        }
    }

     fun Route.registerLoginRoutes(controller: UserController) {
        route("/login") {
            get {
                val session = call.sessions.get<UserSession>()
                if (sessionExists(session) != null) {
                    call.respondRedirect("/")
                } else {
                    call.respond(FreeMarkerContent("login.ftl", null))
                }
            }
            post {
                val post = call.receiveParameters()
                val session = call.sessions.get<UserSession>()
                val username = post["username"]!!
                val password = post["password"]!!

                if (sessionExists(session) != null){
                    call.respond(HttpStatusCode.Forbidden)
                } else {
                    if (controller.logUser(username, password)) {
                        call.sessions.set(UserSession(username))
                        call.respondRedirect("/")
                    } else {
                        call.respond(FreeMarkerContent("login.ftl", mapOf("error" to "Invalid login")))
                    }
                }

            }
        }
        get("/logout"){
            call.sessions.clear<UserSession>()
            call.respondRedirect("/")
        }
    }
}