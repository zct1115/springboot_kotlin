package com.zct.springboot_kotlin.utils

import java.math.BigInteger
import java.util.Random
import java.util.UUID


object IDUtil {

    /**
     * 十六进制映射
     */
    val NUMBER_MAPPER_HEX = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f')
    /**
     * 十六进制映射：大写字母
     */
    val NUMBER_MAPPER_HEX_CAPITAL = charArrayOf('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F')

    fun generateUuid(): String {
        return UUID.randomUUID().toString().replace("-", "")
    }

    /**
     * 生成用户ticket
     * @param id
     * @return
     */
    fun generateTicket(id: String): String {
        val ticket = generateUuid() + id
        return MD5Util().encode(ticket)
    }

    /**
     * 生成短信验证码
     * @return
     */
    fun generateSmsCode(): String {
        val builder = StringBuilder()
        val random = Random(System.nanoTime())
        for (i in 0..5) {
            builder.append(random.nextInt(10))
        }
        return builder.toString()
    }

    /**
     * 生成邀请码
     * 原理：将当前时间戳转换成高进制的字符串
     * @return
     */
    fun generateInvitationCode(): String {

        //乱序编码表
        val items = charArrayOf('w', 'I', '0', '1', '2', '3', '4', 'Q', 'R', 'S', 'T', '5', '6', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'a', 'b', '7', '8', '9', 'c', 'd', 'e', 'p', 'q', 'r', 's', 't', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'u', 'v', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'U', 'V', 'W', 'X', 'Y', 'Z')
        return toAnyMapper(System.currentTimeMillis(), items)


    }

    /**
     * 根据映射码将十进制数字源转换为任意进制形态的字符串
     * @param source
     * @param mapper
     * @return
     */
    fun toAnyMapper(source: Long, mapper: CharArray): String {
        var bi = BigInteger.valueOf(source)
        val l = mapper.size
        val target = StringBuilder()
        while (bi.toInt() != 0) {
            target.insert(0, mapper[bi.remainder(BigInteger.valueOf(l.toLong())).toInt()])
            bi = bi.divide(BigInteger.valueOf(l.toLong()))
        }
        return target.toString()
    }
}