package cn.fpshop.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

import cn.fpshop.pojo.Address;
import cn.fpshop.pojo.Cart;
import cn.fpshop.pojo.CartItem;
import cn.fpshop.pojo.Order;
import cn.fpshop.pojo.OrderItem;
import cn.fpshop.pojo.User;
import cn.fpshop.service.OrderService;
import cn.fpshop.service.UserService;

@Controller
public class OrderController {
	@Autowired
	private OrderService service;
	
	@Autowired
	private UserService userservice;
	
	@RequestMapping("/user/userOrders")
	public String getOrderByUser(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Order> ordersList = service.getOrdersByUser(user.getUid());
		request.setAttribute("ordersList", ordersList);
		return "privilege/userOrder";
	}

	@RequestMapping("/addOrder")
	public String addOrder(HttpServletRequest reqest) {
		
		HttpSession session = reqest.getSession();
		User user = (User) session.getAttribute("user");
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
		}
		if(user == null) {
			return "redirect:/login.html";
		}
		Order order = new Order();
		order.setOid(UUID.randomUUID().toString());
		order.setState(0);
		order.setTotal(cart.getTotal());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String fmt = format.format(new Date());
		System.out.println("fmt:"+fmt);
		Date date = null;
		try {
			date = format.parse(fmt);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("date:"+date);
		order.setOrder_time(date);
		
		Address address = userservice.getDefaultAdd(user.getUid());
		order.setAid(address.getAid());
		order.setAddress(address);
		
		Map<String, CartItem> cartItems = cart.getCartItems();
		for(Map.Entry<String, CartItem> entry : cartItems.entrySet()) {
			CartItem cartitem = entry.getValue();
			System.out.println(cartitem);
			OrderItem orderitem = new OrderItem();
			System.out.println(orderitem);
			
			orderitem.setItemid(null);//由数据库递增自行创建id
			System.out.println(orderitem);
			
			orderitem.setOid(order.getOid()); 
			
			orderitem.setCount(cartitem.getBuyNum());
			
			orderitem.setPid(cartitem.getProduct().getPid());
			
			orderitem.setSubtotal(cartitem.getSubtotal());
			
			orderitem.setOrder(order);
			
			orderitem.setProduct(cartitem.getProduct());
			
			order.getOrderItems().add(orderitem);
		}
		
		service.addOrder(order);
		
		session.setAttribute("order", order);
		session.removeAttribute("cart");
		
		return "redirect:/user/OrderInfo.html";
	}
	
	//支付购物车
	@RequestMapping("/payOrder")
	public String payOrder(String mode,HttpServletRequest request ) {
		HttpSession session = request.getSession();
		Order order = (Order)session.getAttribute("order");
		List<OrderItem> orderItems = order.getOrderItems();
		for(OrderItem item:orderItems) {
			service.soldProduct(item.getProduct().getPid(),item.getCount());
		}
		
		
		if(mode.equals("money")) {
			User user = (User)session.getAttribute("user");
			double money = user.getMoney();
			double total = order.getTotal();
			if(money > total) {
				user.setMoney(money-total);
				userservice.modifyUserInfo(user);
				session.setAttribute("user", user);
				service.setPaid(order.getOid());
				session.removeAttribute("order");
				return "redirect:/user/user.html";
			}else {
				return "failToPay";
			}
		}else {
			service.setPaid(order.getOid());
			session.removeAttribute("order");
			return "redirect:/user/paySuccess";
		}
		
	}
	
	@RequestMapping("/payUnpayOrder")
	public String payUnpayOrder(String oid,HttpServletRequest request) {
		Order order = null;
		if(oid != null || !"".equals(oid)) {
			order = service.getOrder(oid);
		}else {
			return "/";
		}
		HttpSession session = request.getSession();
		session.setAttribute("order", order);
		
		return "redirect:/user/OrderInfo.html";
		
	}
	
	//确认收货
	@RequestMapping("/confirmRec")
	public String comfirmRec(String oid) {
		service.ConfirmRec(oid);
		return "redirect:/user/userOrders";
	}
	
}
