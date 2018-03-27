package com.liuss.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liuss.model.entity.sys.User;
import com.liuss.model.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModelApplicationTests {

	@Autowired
	private UserService userService;
	@Test
	public void addUser()
	{
		List<User>users=userService.findAllUsers();
		for (User user :
				users) {
			userService.deleteUser(user.getId());
		}
		User user=new User();
		user.setName("admin");
		user.setLoginName("admin");
		user.setPassword("123456");
		userService.addUserInfo(user);
	}
	@Test
	public void contextLoads() {
	}
}
