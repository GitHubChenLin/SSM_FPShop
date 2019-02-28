package cn.fpshop.controller;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthSpinnerUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import cn.fpshop.pojo.Address;
import cn.fpshop.pojo.User;
import cn.fpshop.service.UserService;

@Controller
public class UserController {
	@Autowired
	private UserService service;

	@RequestMapping("/login")
	public void login(String account, String password, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String email = "";
		String username = "";
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(account);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		if (flag) {
			email = account;
		} else {
			username = account;
		}

		System.out.println("email:" + email);
		System.out.println("username:" + username);
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);

		User logined = service.login(user);

		boolean isSuccess = true;
		System.out.println(logined);
		HttpSession session = request.getSession();
		if (logined != null) {
			session.setAttribute("user", logined);
			response.getWriter().write("{\"isSuccess\":" + isSuccess + "}");
		}else {
			isSuccess = false;
			response.getWriter().write("{\"isSuccess\":" + isSuccess + "}");
		}

	}

	@RequestMapping("/user/userInfo")
	public String toUserInfo() {
		return "privilege/userInfo";
	}

	// 修改用户信息
	@RequestMapping("/user/modify")
	public void modifyUserInfo(User user, HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println(user);

		service.modifyUserInfo(user);
		User newUser = service.getUser(user.getUid());
		HttpSession session = request.getSession();
		Boolean isSuccess = false;
		if (newUser != null) {
			isSuccess = true;
			session.setAttribute("user", newUser);
		}
		response.getWriter().write("{\"isSuccess\":" + isSuccess + "}");
	}

	// 前往充值余额页面
	@RequestMapping("/user/balance")
	public String toBalancePage() {

		return "privilege/balance";

	}

	// 充值余额
	@RequestMapping("/user/recharge")
	public void recharge(Double pay, HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Double money = user.getMoney();
		Double newMonney = 0.0;
		if (pay != 0.0 && pay != null) {
			newMonney = money + pay;
		}
		System.out.println("money:" + newMonney);
		user.setMoney(newMonney);

		service.modifyUserInfo(user);
		user = service.getUser(user.getUid());
		System.out.println(user);
		session.setAttribute("user", user);

		Boolean isSuccess = false;
		if (user.getUid() != "") {
			isSuccess = true;
		}
		response.getWriter().write("{\"isSuccess\":" + isSuccess + "}");

	}

	// 跳转到支付成功页面
	@RequestMapping("/user/paySuccess")
	public String topaySuccessPage() {
		return "privilege/recharge";
	}

	// getAddsByUser
	@RequestMapping("/user/address")
	public String addressManage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Address> addList = service.getAddress(user.getUid());
		request.setAttribute("addList", addList);
		
		
		return "privilege/addManage";
	}
	
	// 添加或修改用户地址
	@RequestMapping("/user/addManage")
	public String addManage(String aid,String tel,String address,String name,HttpServletRequest request) {
		System.out.println(aid);
		System.out.println(tel);
		System.out.println(address);
		System.out.println(name);
		
		Address address1 = new Address();
		address1.setAid(aid);
		address1.setTel(tel);
		address1.setAddress(address);
		address1.setName(name);
		
		System.out.println(address1);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		address1.setUid(user.getUid());
		System.out.println(address1);
		if(address1.getAid() != null && address1.getAid() != "") {
			service.updateAdd(address1);
		}else {
			service.addAddress(address1);
		}
		
		return "redirect:/user/address";
	}
	
	//删除地址
	@RequestMapping("/user/delAdd")
	public String delAdd(@RequestParam(value = "aid") String aid) {
		Address address = new Address();
		address.setAid(aid);
		
		service.delAdd(address);
		
		return "redirect:/user/address";
	}
	
	//设置默认地址
	@RequestMapping("/user/setDefaultAdd")
	public String setDefaultAdd(String aid,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		Address address = new Address();
		address.setAid(aid);
		address.setUid(user.getUid());
		
		service.setDefaultAdd(address);
		
		
		return  "redirect:/user/address";
	}
	
	@RequestMapping("/user/setDefaultAddFromCart")
	public String setDefaultAddFromCart(String aid,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Address address = new Address();
		address.setAid(aid);
		address.setUid(user.getUid());
		
		service.setDefaultAdd(address);
		
		List<Address> addList = service.getAddress(user.getUid());
		
		session.setAttribute("addListCart", addList);
		
		return "redirect:/cart.html";
		
	}
	
	
	@RequestMapping("/admin/userManage")
	public String adminUserManage(HttpServletRequest request) {
		List<User> userList = service.getAllUser();
		request.setAttribute("userList", userList);
		return "admin/userManage";
		
	}
	
	//按余额排列用户
	@RequestMapping("/admin/showMoney")
	public String userOrderByMoney(String money,HttpServletRequest request) throws IOException {
		List<User> userList = service.orderByMoney(money);
		request.setAttribute("userList", userList);
		return "admin/newUser";
	}
	
	
	@RequestMapping("/admin/showConsume")
	public String orderByConsume(String consume,HttpServletRequest request) throws IOException {
		List<User> userList = service.orderByConsume(consume);
		request.setAttribute("userList", userList);
		return "admin/newUser";
	}
}
