package com.zct.springboot_kotlin.app.model

data class User(var id: Int=0,var userName: String="",var userPwd: String = "", var userMobile: String="",var userIcon: String = "",var userRealName: String ="",
                var userIdentityCard: String = "",var userNickName: String = "", var userGender: String ="", var userBirthday: String = "",
                var userAddress: String ="", var userSign: String ="", var pushId: String="" )
