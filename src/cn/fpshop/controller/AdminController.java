package cn.fpshop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.sun.xml.internal.ws.resources.HttpserverMessages;

import cn.fpshop.pojo.Category;
import cn.fpshop.pojo.Order;
import cn.fpshop.pojo.Product;
import cn.fpshop.service.AdminService;
import cn.fpshop.service.IndexService;
import cn.fpshop.service.OrderService;
import cn.fpshop.service.ProductService;

@Controller
public class AdminController {
	@Autowired
	private AdminService service;
	
	@Autowired
	private ProductService productservice;

	@Autowired
	private IndexService indexservice;

	
	@Autowired
	private OrderService orderservice;
	
	
	// ����Ϊ��̨ҳ��
	@RequestMapping("/admin")
	public String admin() {
		return "admin/index";

	}

	@RequestMapping("/admin/category")
	public String categoryManage(HttpServletRequest request) {
		List<Category> categoryList = indexservice.getCategoryList();

		request.setAttribute("categoryList", categoryList);
		return "admin/categoryManage";

	}
	
	@RequestMapping("/admin/addOrchange")
	public String categoryManage(String cid,String cname) {
		if(cid !=null && !"".equals(cid)) {	//�޸����
			System.out.println("�޸����");
			service.modifyCategory(cid,cname);
		}else {
			System.out.println("������");
			service.addCaregory(cname);
		}
		
		return "redirect:/admin/category";	
		
	}
	
	@RequestMapping("/admin/delCategory")
	public String delCategory(String cid) {
		service.delCategory(cid);
		
		return "redirect:/admin/category";
	
	}
	
	//��Ʒ����
	
	@RequestMapping("/admin/Product")
	public String productManage(HttpServletRequest request) {
		List<Product> productList = productservice.getAllProduct();
		List<Category> categoryList = indexservice.getCategoryList();
		request.setAttribute("productList", productList);
		request.setAttribute("categoryList", categoryList);
		
		return "admin/productManage";
	}
	
	//��Ʒ����
	@RequestMapping("/admin/orderBy")
	public String orderBy(String cid,String price,String amount,HttpServletRequest request) {
		List<Product> productList = null;
		System.out.println("cid:"+cid+"  price:"+price+ "  amount:"+amount);
		if(cid != null) {
			productList = service.getProductByDetails(cid,price,amount);
		}
		request.setAttribute("productList", productList);
		
		return "admin/newProductManage";
	}
	
	//����չʾ��õ���Ʒ����
	@RequestMapping("/admin/showProductInfo")
	public void showProduct(String pid,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Product product = productservice.getProductInfo(pid);
		Gson gson = new Gson();
		String json = gson.toJson(product);
		response.getWriter().write(json);
	}
	
	@RequestMapping("/admin/modifyProduct")
	public void modifyProduct(Product product,HttpServletResponse response) throws IOException {
		System.out.println(product.getPid());
		productservice.modifyProduct(product);
		response.getWriter().write("{\"isSuc\":"+true+"}");
	}
	
	@RequestMapping("/admin/orderManage")
	public String orderManage(HttpServletRequest request) {
		List<Order> orderList = orderservice.getAllOrder();

		Gson gson = new Gson();
		String json = gson.toJson(orderList);
		request.setAttribute("orderList", orderList);
		request.setAttribute("json", json);
		return "admin/orderManage";
	}
	
	@RequestMapping("/admin/setdelivery")
	public String  setdelivery(String oid) {
		orderservice.setDelivery(oid);
		
		return "redirect:/admin/orderManage";
	}
}
