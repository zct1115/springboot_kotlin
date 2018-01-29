package com.zct.springboot_kotlin.app.mapper

import com.zct.springboot_kotlin.app.model.User

interface UserMapper {
    fun deleteByPrimaryKey(id: Int?): Int

    fun insert(record: User): Int

    fun insertSelective(record: User): Int

    fun selectByPrimaryKey(id: Int?): User

    fun updateByPrimaryKeySelective(record: User): Int

    fun updateByPrimaryKey(record: User): Int

    fun selectByModile(modile:String): User

    fun selectByName(username:String): User

}