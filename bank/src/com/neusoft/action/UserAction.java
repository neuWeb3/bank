package com.neusoft.action;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.neusoft.bo.UserManager;
import com.neusoft.dao.AccountDAO;
import com.neusoft.dao.UserDAO;
import com.neusoft.po.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private String realName;
	private String idNumber;
	private String cardNumber;
	private String userName;
	private String password;
	private UserManager userManager;
	private String email;
	private String address;
	private String postCode;
	
	public String info(){
		Map request = (Map) ActionContext.getContext().get("request");
		Map<String,Object> session = ActionContext.getContext().getSession();
		request.put("user",userManager.getUserInfo((String) session.get("loginInfo")));
		return "info";
	}
	public String changeUserInfo(){
		Map request = (Map) ActionContext.getContext().get("request");
		Map<String,Object> session = ActionContext.getContext().getSession();
		User user = userManager.changeUserInfo((String) session.get("loginInfo"), email, address, postCode);
		request.put("user",userManager.getUserInfo((String) session.get("loginInfo")));
		return "changeUserInfo";
	}
	
	public String signIn() {
		String result;
		Map<String,Object> session = ActionContext.getContext().getSession();
		if(userName!=null&&userManager.checkOutLogin(userName, password)){
			if(!(session.get("loginError")==null))
			{
				session.remove("loginError");
			}
				session.put("loginInfo",userName);
				result = "success";
		}else{
			session.put("loginError","�û��������벻��ȷ����");
			result = "signIn";
		}
		return result;
		
	}
	public String signUp(){
		System.out.println("1212"+realName);
		System.out.println("444"+idNumber);
		System.out.println("555"+cardNumber);
		Map request = (Map) ActionContext.getContext().get("request");
		ApplicationContext  ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		UserDAO userDao = (UserDAO) ctx.getBean("UserDAO");
		AccountDAO accountDao = (AccountDAO) ctx.getBean("AccountDAO");
		if(userManager.checkOutRegister(realName, idNumber, cardNumber,
				userName, password))
		{
			return "success";
		}
		else
		{
			Map<String,Object> session = ActionContext.getContext().getSession();
			session.put("ErrorMessage", "ע����Ϣ����");
			return "signUp";
		}
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdNumber() {
		return idNumber;
	}
	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserManager getUserManager() {
		return userManager;
	}
	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
}