package com.zct.springboot_kotlin.app.service

import com.zct.springboot_kotlin.app.model.User


interface UserService {
    fun addUser(user:User):Boolean

    fun getUserById(paramId:Int):User

    fun modifyUser(user: User):Int

    fun getUserByMobile(mobile:String):User

    fun getUserByName(username:String):User

    fun login(username: String,password:String):Boolean
}