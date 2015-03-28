package com.cobweb.io.core;

import com.cobweb.io.service.CreateService;

public class Test{
	
	public static void main(String args[]){
		
		User user = new User("Yasith", "yasith1@gmail.com", "1qaz2wsx#", "123QIOY45");
		CreateService createservice = new CreateService();
		createservice.Create(user);		
	}
	

}
