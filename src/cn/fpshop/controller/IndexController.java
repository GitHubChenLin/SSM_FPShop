package cn.fpshop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.fpshop.pojo.Category;
import cn.fpshop.pojo.Product;
import cn.fpshop.service.IndexService;

@Controller
public class IndexController {
	@Autowired
	private IndexService indexservice;
	
	@RequestMapping("/findCategory")
	public String categoryList(Model model) {
		List<Category> categoryList = indexservice.getCategoryList();
		model.addAttribute("categoryList", categoryList);
		return "header";
	}
	
	@RequestMapping(value = {"/","/index"})
	public String index(Model model) {
		
		List<Product> hotProductList = indexservice.getHotProducts();
		List<Product> newProductList = indexservice.getNewProducts();
		model.addAttribute("hotProductList", hotProductList);
		model.addAttribute("newProductList", newProductList);
		
		return "index";
	}
	
	@RequestMapping("/login.html")
	public String loginPage(){
		
		return "login";
	
	}
	
	@RequestMapping("/user/user.html")
	public String userPage() {
		return "privilege/user";
	}
	
	//购物车页面
	@RequestMapping("/cart.html")
	public String toCartPage() {
		return "cart";
	}
	
	//订单详情页面
	@RequestMapping("/user/OrderInfo.html")
	public String toOrderInfoPage() {
		
		return "privilege/orderInfo";
	}
	
}
