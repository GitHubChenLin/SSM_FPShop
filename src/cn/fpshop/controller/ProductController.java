package cn.fpshop.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
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

import cn.fpshop.pojo.Address;
import cn.fpshop.pojo.Cart;
import cn.fpshop.pojo.CartItem;
import cn.fpshop.pojo.Order;
import cn.fpshop.pojo.PageBean;
import cn.fpshop.pojo.Product;
import cn.fpshop.pojo.User;
import cn.fpshop.service.OrderService;
import cn.fpshop.service.ProductService;
import cn.fpshop.service.UserService;

@Controller
public class ProductController {
	@Autowired
	private ProductService service;

	@Autowired
	private UserService userservice;

	@RequestMapping("/productByCategory")
	public String getOrderByUser(String cid, @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			HttpServletRequest request) {

		PageBean pagebean = service.getProductListByCid(cid, currentPage);

		request.setAttribute("pageBean", pagebean);
		request.setAttribute("cid", cid);

		return "productList";
	}

	
	//根据pid展示商品
	@RequestMapping("/product")
	public String productInfo(String pid, HttpServletRequest request) {

		Product product = service.getProductInfo(pid);
		request.setAttribute("product", product);

		return "productInfo";
	}

	// 加入购物车
	@RequestMapping("/addCart")
	public String addCart(String pid, int buyNum, HttpServletRequest request) {

		HttpSession session = request.getSession();

		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
		}

		CartItem cartitem = new CartItem();

		Product product = service.getProductInfo(pid);
		cartitem.setProduct(product);
		cartitem.setBuyNum(buyNum);

		double subtotal = product.getPrice() * buyNum;
		cartitem.setSubtotal(subtotal);
		
		double total = 0.0d;
		Map<String, CartItem> cartItems = cart.getCartItems();
		if(cartItems.containsKey(pid)) {
			CartItem item = cartItems.get(pid); 
			int oldNum = item.getBuyNum();
			int newNum = oldNum+buyNum;
			item.setBuyNum(newNum);
			item.setSubtotal(product.getPrice()*newNum);
			cart.setCartItems(cartItems);
		}else {
			cartItems.put(pid, cartitem);
		}
		
		total = cart.getTotal()+cartitem.getSubtotal();
		cart.setTotal(total);

		
		User user = (User) session.getAttribute("user");
		if (user != null) {
			List<Address> address = userservice.getAddress(user.getUid());
			System.out.println(address);
			session.setAttribute("addListCart", address);
		}
		
		
		
		session.setAttribute("cart", cart);

		return "redirect:/cart.html";

	}
	
	@RequestMapping("/delCartItem")
	public String delCartItem(String pid,HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart!=null) {
			Map<String, CartItem> cartItems = cart.getCartItems();
			//修改总价
			double  newTotal = cart.getTotal() - cartItems.get(pid).getSubtotal();
			cart.setTotal(newTotal);
			// 删除
			cartItems.remove(pid);
			cart.setCartItems(cartItems);
		}
		session.setAttribute("cart", cart);
		return "redirect:/cart.html";
	}
	
	@RequestMapping("/removeCart")
	public String removeCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("cart");
		
		return "redirect:/cart.html";
		
	}

}
