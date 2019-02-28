package cn.fpshop.pojo;

import java.io.Serializable;
import java.util.List;

public class Address implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String aid;

	private String uid; // 该地址属于哪个用户
	private String name;
	private String tel;
	private String address;
	private Boolean defaultAdd;

	private User user;
	private List<Order> orders;

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getDefaultAdd() {
		return defaultAdd;
	}

	public void setDefaultAdd(Boolean defaultAdd) {
		this.defaultAdd = defaultAdd;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Address [aid=" + aid + ", uid=" + uid + ", name=" + name + ", tel=" + tel + ", address=" + address
				+ ", defaultAdd=" + defaultAdd + ", user=" + user + "]";
	}

}
