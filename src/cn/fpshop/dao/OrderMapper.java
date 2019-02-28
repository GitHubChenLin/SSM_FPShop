package cn.fpshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.fpshop.pojo.Category;
import cn.fpshop.pojo.Order;
import cn.fpshop.pojo.OrderItem;
import cn.fpshop.pojo.Product;

public interface OrderMapper {

	List<Order> getOrderDetails(String uid);

	void insertOrder(Order order);

	void insertOrderItems(@Param("orderItems") List<OrderItem> orderItems);

	void modifyProductCount(String pid, int count);

	void setPaid(String oid);

	Order getOrder(String oid);

	List<Order> getAllOrder();

	void setDelivery(String oid);

	void confiremRec(String oid);
	
	
	
}
