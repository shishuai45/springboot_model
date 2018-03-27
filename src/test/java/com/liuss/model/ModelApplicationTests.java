package com.liuss.model;

import com.liuss.model.entity.sys.User;
import com.liuss.model.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
