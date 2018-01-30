package com.zct.springboot_kotlin.app.service.impl

import com.zct.springboot_kotlin.app.mapper.UserMapper
import com.zct.springboot_kotlin.app.model.User
import com.zct.springboot_kotlin.app.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service(value = "userService")
class UserServiceImpl : UserService {

    @Autowired val userMapper: UserMapper? = null

    @Transactional
    override fun addUser(user: User): Boolean {
        userMapper!!.insert(user)
        return true
    }

    override fun getUserById(paramId: Int): User {
       return userMapper!!.selectByPrimaryKey(paramId)
    }

    override fun modifyUser(user: User): Int {
        return userMapper!!.updateByPrimaryKey(user)
    }

    override fun getUserByMobile(mobile: String): User {
        return userMapper!!.selectByModile(mobile)
    }

    override fun getUserByName(username: String): User {
        return userMapper!!.selectByName(username)
    }

    override fun login(username: String, password: String): Boolean {
        if(userMapper?.selectByName(username)!= null){
            return userMapper!!.selectByName(username).userPwd.equals(password)
        }else{
            return false
        }
    }
}