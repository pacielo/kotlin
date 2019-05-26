package com.rolly

interface SessionController {
    fun sessionExists(session: UserSession?): String?
}