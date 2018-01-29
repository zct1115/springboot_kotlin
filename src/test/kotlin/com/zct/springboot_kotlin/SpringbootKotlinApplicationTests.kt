package com.zct.springboot_kotlin

import com.zct.springboot_kotlin.utils.IDUtil
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SpringbootKotlinApplicationTests {

	@Test
	fun contextLoads() {
		print(IDUtil.generateInvitationCode())
	}

}
