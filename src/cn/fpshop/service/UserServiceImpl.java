package cn.fpshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fpshop.dao.UserMapper;
import cn.fpshop.pojo.Address;
import cn.fpshop.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper usermapper;

	@Override
	public User login(User check) {
		User user = usermapper.login(check);
		return user;
	}

	@Override
	public void modifyUserInfo(User user) {
		usermapper.modifyUser(user);
	}

	@Override
	public User getUser(String uid) {
		User user = usermapper.getUser(uid);

		return user;
	}

	@Override
	public List<Address> getAddress(String uid) {

		return usermapper.getAddress(uid);
	}

	@Override
	public void updateAdd(Address address) {
		usermapper.updateAdd(address);
	}

	@Override
	public void addAddress(Address address) {
		usermapper.addAddress(address);
	}

	@Override
	public void delAdd(Address address) {

		usermapper.delAdd(address);
	}

	@Override
	public void setDefaultAdd(Address address) {

		usermapper.setDefaultAdd(address);
	}

	@Override
	public Address getDefaultAdd(String uid) {
		return usermapper.getDefaultAdd(uid);
	}

	@Override
	public List<User> getAllUser() {
		return usermapper.getAllUser();
	}

	@Override
	public List<User> orderByMoney(String money) {
		return usermapper.orderByMoney(money);
	}

	@Override
	public List<User> orderByConsume(String consume) {
		return usermapper.orderByConsume(consume);
	}


}
