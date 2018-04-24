package com.witkey.test;

import com.witkey.service.MailService;

public class JavaMailTest {
	
	public static void main(String[] args) throws Exception {
		String mail = "1255490926@qq.com";
		String content = "652314";
		MailService jm = new MailService();
		jm.sendMail(mail, content);
		
	 

    }


}
