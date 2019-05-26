package com.rolly

object SessionControllerImpl: SessionController {
    override fun sessionExists(session: UserSession?): String? {
        if (session != null)
            return session.username
        return null
    }
}