package cn.fpshop.service;

import java.util.List;

import cn.fpshop.pojo.Address;
import cn.fpshop.pojo.User;

public interface UserService {

	public User login(User user);

	public void modifyUserInfo(User user);

	public User getUser(String uid);

	public List<Address> getAddress(String uid);

	public void updateAdd(Address address);

	public void addAddress(Address address);

	public void delAdd(Address address);

	public void setDefaultAdd(Address address);

	public Address getDefaultAdd(String uid);

	public List<User> getAllUser();

	public List<User> orderByMoney(String money);

	public List<User> orderByConsume(String consume);


}
