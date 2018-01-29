package com.zct.springboot_kotlin.app.controller

import com.zct.springboot_kotlin.app.model.User
import com.zct.springboot_kotlin.app.service.UserService
import com.zct.springboot_kotlin.common.BaseController
import com.zct.springboot_kotlin.common.CodeMessage
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletResponse


@Controller
@RequestMapping(value = "/user")
@Api(value = "用户接口服务", description = "用户登录注册的api接口")
class UserController : BaseController() {
    @Autowired
    val userservice: UserService? = null

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    @Transactional
    fun register(user: User, httpresponse: HttpServletResponse) {
        when {
            userservice?.getUserByMobile(user.userMobile) != null -> {
                renderMessage(httpresponse,CodeMessage.FAILTURE, "账号已被注册")
            }
            userservice?.getUserByName(user.userName) != null -> {
                renderMessage(httpresponse, CodeMessage.FAILTURE,"该用户名已存在！")
            }
            userservice?.getUserByMobile(user.userMobile) == null&& userservice?.getUserByName(user.userName) == null->{
                userservice?.addUser(user)
                renderMessage(httpresponse, CodeMessage.SUCCESS,"注册成功")
            }
        }

    }

    @ApiOperation("登录")
    @PostMapping(value = "/login")
    @Transactional
    fun login(user: User, httpresponse: HttpServletResponse){
        if(userservice?.login(user.userName,user.userPwd)==true){
            renderMessage(httpresponse, CodeMessage.SUCCESS,"登录成功")
        }else{
            renderMessage(httpresponse, CodeMessage.FAILTURE,"用户名或密码错误")
        }
    }


}