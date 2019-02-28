package cn.fpshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fpshop.dao.OrderMapper;
import cn.fpshop.dao.UserMapper;
import cn.fpshop.pojo.Order;
import cn.fpshop.pojo.OrderItem;
import cn.fpshop.pojo.User;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper mapper;

	@Override
	public List<Order> getOrdersByUser(String uid) {
		return mapper.getOrderDetails(uid);
	}

	@Override
	public void addOrder(Order order) {
		mapper.insertOrder(order);
		List<OrderItem> orderItems = order.getOrderItems();
		mapper.insertOrderItems(orderItems);
		
	}

	@Override
	public void soldProduct(String pid, int count) {
		mapper.modifyProductCount(pid,count);
		
	}

	@Override
	public void setPaid(String oid) {
		mapper.setPaid(oid);
	}

	@Override
	public Order getOrder(String oid) {

		return mapper.getOrder(oid);
	}

	@Override
	public List<Order> getAllOrder() {
		return mapper.getAllOrder();
	}

	@Override
	public void setDelivery(String oid) {
		mapper.setDelivery(oid);
	}

	@Override
	public void ConfirmRec(String oid) {
		mapper.confiremRec(oid);
	}
	
	
}
