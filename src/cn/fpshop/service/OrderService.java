package cn.fpshop.service;

import java.util.List;

import cn.fpshop.pojo.Order;

public interface OrderService {

	List<Order> getOrdersByUser(String uid);

	void addOrder(Order order);

	void soldProduct(String pid, int count);

	void setPaid(String oid);

	Order getOrder(String oid);

	List<Order> getAllOrder();

	void setDelivery(String oid);

	void ConfirmRec(String oid);

}
