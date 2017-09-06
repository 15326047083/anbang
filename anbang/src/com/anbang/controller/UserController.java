package com.anbang.controller;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.anbang.auth.po.User;
import com.anbang.auth.service.IPrivilegeService;
import com.anbang.auth.service.IUserService;
import com.anbang.auth.vo.AuthVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	IUserService userService;
	
	@Autowired
	IPrivilegeService prviService;
	
	@RequestMapping("/toMain.do")
	public String toMain(){
		
		return "maina";
	}
	
	/**
	 * 登录操作，为方便企业用户查看个人信息，将epid单独传入session
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public String login(@ModelAttribute("user") User user,HttpSession session){
		AuthVo userVo=userService.login(user);
		if(null!=userVo){
			
			session.setAttribute("user", userVo);
			if(userVo.getEpId()!=null)
				session.setAttribute("epId", userVo.getEpId());
			if(userVo.getUserType().equals("dept"))
				return "menu/welcome4";
			return "menu/main";
		}
		return "redirect:toLogin.do";
	}
	
	/**
	 * 根据用户类型获得左侧菜单
	 * @param request
	 * @return
	 */
	@RequestMapping("/getLeft.do")
	public String getLeft(HttpServletRequest request){
		AuthVo userVo = (AuthVo) request.getSession().getAttribute("user");
		return "menu/"+userVo.getUserType();
		
	}
	
	@RequestMapping("/toLogin.do")
	public ModelAndView toLogin(ModelMap model){
		User user = new User();
		model.addAttribute("user", user);
		return new ModelAndView("menu/login",model);
		
	}
	/**
	 * 注销
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.invalidate();
		return "redirect:toLogin.do";
		
	}
	
	@RequestMapping("/new.do")
	public ModelAndView newUser(@RequestParam("epId") String epId){
		User u = userService.getUserByEpId(epId)==null?new User():userService.getUserByEpId(epId);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("ep/epUser");
		mv.addObject("user", u);
		return mv;
	}
	
	@RequestMapping("/save.do")
	public String saveUser(@ModelAttribute("user") User user){
		userService.saveUser(user);
		return "redirect:new.do?epId="+user.getEpId();
	}
	
	
}
