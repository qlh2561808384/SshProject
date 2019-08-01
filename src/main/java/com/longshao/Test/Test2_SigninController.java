/**
 * ‎Hangzhou Lejian Technology Co., Ltd.
 * Copyright (c) 2019 All Rights Reserved.
 */
package com.longshao.Test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用户注册入口
 * 
 * @author qilonghui
 *
 */
@Controller
public class Test2_SigninController {

	/**
	 * TODO 请从这里开始补充代码
	 * 
	 * 测试1：138 1234 1234
	 *	结果：通过此手机号注册成功
	 *
	 * 测试2：13812345abc
	 *	结果：通知本手机号无法注册，提示为非法手机号
	 *
	 * 测试3：13812345678
	 *	结果：通知此手机号注册成功
	 *
	 * 测试4：138 1234 5678
	 *	结果：提示此手机号已经被其他用户注册
	 *
	 * 测试5：98765432101
	 *	结果：此手机号码为中国大陆非法手机号码
	 */
	private static final int NumOfPho = 11;

	public String checkPhone(String str, HttpSession session) {
		String checkmsg = "";
//        int NumOfPho = 11;
		//手机号去空格
		String phone = str.replaceAll("\\s+", "");
		String ssionpho = session.getAttribute(phone) == null ? "" : session.getAttribute(phone).toString();
		//校验手机号正则表达式
		String PHONE_NUMBER_REG = "^(1[3-9])\\d{9}$";
		Pattern pattern = null;
		Matcher matcher = null;
		boolean b = false;
		//判断手机号是否包含非法字符
		boolean checkletter = Pattern.compile("[^0-9]").matcher(phone).find();
		if (!checkletter) {
			if (NumOfPho == phone.length()) {
				if (phone.equals(ssionpho)) {
					checkmsg += "此手机号已经被其他用户注册";
				} else {
					pattern = Pattern.compile(PHONE_NUMBER_REG);
					matcher = pattern.matcher(phone);
					b = matcher.matches();
					if (b) {
						checkmsg += "手机号注册成功";
						session.setAttribute(phone, phone);
					} else {
						checkmsg += "此手机号为中国大陆非法手机号";
					}
				}
			} else {
				checkmsg += "手机号应为11位";
			}
		} else {
			checkmsg += "手机号" + str + "为非法手机号，无法注册";
		}
		return checkmsg;
	}

	@RequestMapping("check")
	@ResponseBody
	public void checkphone(HttpSession session) {

		//TODO 测试1
		String phone1 = "14567";//校验手机位数

		//TODO 测试2
		String phone2 = "138 1234 1234";//手机号注册成功

		//TODO 测试3
		String phone3 = "13812345abc";//记号无法注册 非法手机号

		//TODO 测试4
		String phone4 = "13812345678";//手机号注册成功

		//TODO 测试5
		String phone5 = "138 1234 5678";//手机号已经被其他用户注册

		//TODO 测试6
		String phone6 = "98765432101";//此手机号为中国大陆非法手机号

		//测试
		String phonCheck = checkPhone(phone1, session);
		System.out.println(phonCheck);
	}
}
