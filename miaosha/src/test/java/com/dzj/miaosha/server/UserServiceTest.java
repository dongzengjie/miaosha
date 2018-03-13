package com.dzj.miaosha.server;

import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.dzj.miaosha.service.UserService;
import com.dzj.miaosha.vo.LoginVo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userservice;
	@Test
	public void testlogin() {
		LoginVo loginVo =new LoginVo();
		loginVo.setMobile("15901870552");
		loginVo.setPassword("d3b1294a61a07da9b49b6e22b2cbd7f9");
		System.out.println(userservice.login(null, loginVo));
		
	}
}

