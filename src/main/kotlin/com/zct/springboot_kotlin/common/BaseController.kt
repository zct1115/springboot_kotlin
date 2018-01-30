package com.zct.springboot_kotlin.common

import com.alibaba.fastjson.JSONObject
import com.zct.springboot_kotlin.app.model.User
import javax.servlet.http.HttpSession
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import javax.servlet.http.HttpServletRequest


open class BaseController {
    val CURRENT_USER = "current_user"

    /**
     * 获取当前登录的用户信息
     * @param request
     * @return
     */
    protected fun currentUser(request: HttpServletRequest): User {
        return currentUser(request.getSession())
    }

    protected fun currentUser(session: HttpSession): User {
        return session.getAttribute(CURRENT_USER) as User
    }

    /**
     * 设置当前登录的用户(登录）
     * @param cu
     * @param request
     */
    protected fun setCurrentUser(cu: User, request: HttpServletRequest) {
        setCurrentUser(cu, request.getSession())
    }

    protected fun setCurrentUser(cu: User, session: HttpSession) {
        session.setAttribute(CURRENT_USER, cu)
    }

    /**
     * 移除当前登录的用户（登出）
     * @param request
     */
    protected fun removeCurrentUser(request: HttpServletRequest) {
        removeCurrentUser(request.getSession())
    }

    protected fun removeCurrentUser(session: HttpSession) {
        session.removeAttribute(CURRENT_USER)
    }
}