package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;
	
	//회원가입 폼
	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("[UserController.joinForm]");
		
		return "/user/joinForm";
	}
	
	//아이디 중복체크
	@ResponseBody
	@RequestMapping("/idCheck")
	public String idCheck(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.idCheck]");

		String result = userService.idCheck(userVo);
		return result;
	}
	
	//회원가입 처리
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("[UserController.join]");
		
		//회원가입 처리
		userService.join(userVo);
		
		return "/user/joinSuccess";
	}
	
	//로그인 폼
	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("[UserController.loginForm]");
		
		return "/user/loginForm";
	}
	
	//로그인 처리
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("[UserController.login]");
		
		UserVo authUser = userService.login(userVo);
		
		if (authUser != null) {
			System.out.println("[로그인성공]");
			session.setAttribute("authUser", authUser);
			return "redirect:/";
		} else {
			System.out.println("[로그인실패]");
			return "redirect:/user/loginForm?result=fail";
		}
	}
	
	//로그아웃 처리
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("[UserController.logout]");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
}
