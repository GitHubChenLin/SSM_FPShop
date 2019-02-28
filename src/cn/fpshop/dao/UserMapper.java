package cn.fpshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.fpshop.pojo.Address;
import cn.fpshop.pojo.User;

public interface UserMapper {

	public User login(User check);

	public void modifyUser(User user);

	public User getUser(String uid);

	public List<Address> getAddress(String uid);

	public void updateAdd(Address address);

	public void addAddress(Address address);

	public void delAdd(Address address);

	public void setDefaultAdd(Address address);

	public Address getDefaultAdd(String uid);

	public List<User> getAllUser();

	public List<User> orderByMoney(@Param("money") String money);

	public List<User> orderByConsume(@Param("consume") String consume);

	
	
	
}
