package com.witkey.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.witkey.po.HttpResult;
import com.witkey.po.User;
import com.witkey.service.MailService;
import com.witkey.service.UserService;

@Controller
public class UserController {
	//test test test
	// ע��UserService���� 
	
	@Autowired
	private UserService userService;
	@Autowired
	private MailService jmService;

	@RequestMapping(value = "/user/checkuser", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	@ResponseBody
	// ��ʶ��Ӧ�� �÷����ķ���ֵΪ��Ӧ������
	public HttpResult<User> doLogin(@RequestBody User user) {
		
		HttpResult<User> result = new HttpResult<User>();
		result.setCode(199);
		result.setMessage("����������");
		System.out.println(user.getUser_email());
		try {
			if(this.userService.checkUserByEmail(user.getUser_email()) != null) {
				if(this.userService.checkUserByEmailAndPwd(user)!=null) {
					result.setCode(200);
					user.setUser_id(this.userService.checkUserByEmailAndPwd(user));
					result.setData(user);
				}else{
					result.setCode(202);
					result.setMessage("�������");
				}

			} else {
				result.setCode(201);
				result.setMessage("���û������ڣ�");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult<User> findUserById(@PathVariable Integer id) {
		HttpResult<User> result = new HttpResult<User>();
		result.setCode(199);
		User user = this.userService.findUserById(id);
		if(user != null){
			result.setCode(200);
			result.setData(user);
			
		}

		return result;

	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	@ResponseBody
	public HttpResult<User> updateUser(@RequestBody User user) {
		System.out.println("id " + user.getUser_id());
		HttpResult<User> result = new HttpResult<User>();
		result.setCode(201);
		int row = this.userService.updateUser(user);
		if (row > 0) {
			result.setCode(202);
		}

		return result;

	}

	@RequestMapping(value = "/user/checkcode", method = RequestMethod.GET)
	@ResponseBody
	public HttpResult<String> getCheckCode(@RequestParam final String email) {
		System.out.println("email " + email);
		int checkCode = (int) (Math.random() * 1000000);
		while (checkCode == 0) {
			checkCode = (int) (Math.random() * 1000000);
		}
		HttpResult<String> result = new HttpResult<String>();
		result.setCode(199);
		result.setMessage("��֤�뷢��ʧ�ܣ�");
		String code = String.valueOf(checkCode);
		final String content = "��֤��Ϊ��" + code + "������60s�ڰ�ҳ����ʾ�ύ��֤�룬������֤��й¶�����ˡ�";
		System.out.println("email " + content);
		try {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						jmService.sendMail(email, content);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}).start();
			
			result.setCode(200);
			result.setMessage("��֤�뷢�ͳɹ���");
			result.setData(code);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		System.out.println("res " + email);
		return result;

	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	@ResponseBody
	public HttpResult<User> addUser(@RequestBody User user) {
		HttpResult<User> result = new HttpResult<User>();
		result.setCode(202);
		result.setMessage("�û�ע��ʧ�ܣ�");
		int flag = 0;
		try {
			
			if(this.userService.checkUserByEmail(user.getUser_email()) == null){
				flag = this.userService.addUser(user);
				if (flag > 0) {
					result.setCode(201);
					result.setMessage("�û�ע��ɹ���");
				
				}
			}else{
				result.setCode(203);
				result.setMessage("���û��Ѵ��ڣ�");
			}
			
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;

	}
	@RequestMapping(value = "/user/pwd", method = RequestMethod.POST)
	@ResponseBody
	public HttpResult<String> resetPwd(@RequestParam String email, @RequestParam String pwd) {
		HttpResult<String> result = new HttpResult<String>();
		result.setCode(202);
		result.setMessage("��������ʧ��!");
		int flag = 0;
		try {
			flag = this.userService.resetPwd(email, pwd);
			if (flag > 0) {
				result.setCode(201);
				result.setMessage("�������óɹ�!");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return result;

	}

	@RequestMapping(value = "/user/head/{id}", method = RequestMethod.GET)
	@ResponseBody
	public int getHead(@PathVariable int id, HttpServletRequest request,
			HttpServletResponse response) {
		String head = this.userService.findUserHeadById(id);
		response.setContentType("image/*");
		FileInputStream fis = null;
		OutputStream os = null;
		try {
			String realpath = request.getSession().getServletContext()
					.getRealPath(head);
			System.out.println("imgPath: " + realpath);
			fis = new FileInputStream(realpath);
			os = response.getOutputStream();
			int count = 0;
			byte[] buffer = new byte[1024 * 8];
			while ((count = fis.read(buffer)) != -1) {
				os.write(buffer, 0, count);
				os.flush();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			try {
				fis.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
		return 100;
	}

}
