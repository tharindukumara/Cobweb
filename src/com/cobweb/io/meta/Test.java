package com.cobweb.io.meta;

import com.cobweb.io.service.CreateService;

public class Test{
	
	public static void main(String args[]){				
		User user = new User("Loxer", "loxology@gmail.com", "1qaz2wsx#", "123QIOY45","00001");
		CreateService createservice = new CreateService(user);			
	}
}
