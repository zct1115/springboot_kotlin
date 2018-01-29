package com.zct.springboot_kotlin.common

import com.alibaba.fastjson.JSONObject
import com.zct.springboot_kotlin.app.model.User
import javax.servlet.http.HttpSession
import javax.servlet.http.HttpServletResponse
import java.io.IOException
import javax.servlet.http.HttpServletRequest


open class BaseController {
    val useJsonp = false
    val JSONP_FUNC = "callback"
    val CURRENT_USER = "current_user"

    private fun render(response: HttpServletResponse, data: String) {
        var data = data
        if (useJsonp) {
            data = "$JSONP_FUNC($data)"
        }
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        try {
            response.writer.write(data)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    /**
     * 对于需要数据的操作的返回，当数据为null时失败
     * @param response
     * @param data
     */
    protected fun renderJson(response: HttpServletResponse, data: Any?) {

        if (data == null) {
            renderMessage(response, CodeMessage.NOT_FOUND_DATA, "服务器找不到数据")
            return
        }

        val wrapper = JSONObject()
        wrapper.put("state", CodeMessage.SUCCESS)
        wrapper.put("data", data)
        render(response, wrapper.toJSONString())

    }


    /**
     * 通用消息返回
     * @param response
     * @param msg
     */
    protected fun renderMessage(response: HttpServletResponse, code:String, msg: String) {
        val wrapper = JSONObject()
        wrapper.put("state", code)
        wrapper.put("data", msg)
        render(response, wrapper.toJSONString())
    }


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