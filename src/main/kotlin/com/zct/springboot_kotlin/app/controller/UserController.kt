package com.zct.springboot_kotlin.app.controller

import com.zct.springboot_kotlin.app.model.User
import com.zct.springboot_kotlin.app.service.UserService
import com.zct.springboot_kotlin.common.BaseController
import com.zct.springboot_kotlin.common.BaseResp
import com.zct.springboot_kotlin.common.CodeMessage
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import javax.servlet.http.HttpServletResponse


@Controller
@RequestMapping(value = "/user")
@Api(value = "用户接口服务", description = "用户登录注册的api接口")
class UserController : BaseController() {
    @Autowired
    val userservice: UserService? = null

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    fun register(user: User):BaseResp {
        when {
            userservice?.getUserByMobile(user.userMobile) != null -> {
                return BaseResp(CodeMessage.FAILTURE,"账号已被注册")
            }
            userservice?.getUserByName(user.userName) != null -> {
                return BaseResp(CodeMessage.FAILTURE,"该用户名已存在！")
            }
            userservice?.getUserByMobile(user.userMobile) == null&& userservice?.getUserByName(user.userName) == null->{
                userservice?.addUser(user)
                return BaseResp(CodeMessage.SUCCESS,"注册成功！")
            }
            else->{
                return BaseResp(CodeMessage.FAILTURE,"注册失败！")
            }
        }

    }

    @ApiOperation("登录")
    @PostMapping(value = "/login")
    @ResponseBody
    fun login(user: User):BaseResp{
        if(userservice?.login(user.userName,user.userPwd)==true){
            return  BaseResp(CodeMessage.SUCCESS,"登录成功")
        }else{
            return BaseResp(CodeMessage.FAILTURE,"用户名或密码错误")
        }
    }


}