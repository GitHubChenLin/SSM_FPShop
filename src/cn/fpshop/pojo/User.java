package cn.fpshop.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String uid;
	private String uname;
	private String username;
	private String email;
	private String age;
	private Boolean sex;
	private String telephone;
	private String password;
	private double money;
	private Integer ustate;
	
	private List<Address> adds;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public Boolean getSex() {
		return sex;
	}

	public void setSex(Boolean sex) {
		this.sex = sex;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public Integer getUstate() {
		return ustate;
	}

	public void setUstate(Integer ustate) {
		this.ustate = ustate;
	}

	public List<Address> getAdds() {
		return adds;
	}

	public void setAdds(List<Address> adds) {
		this.adds = adds;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", uname=" + uname + ", username=" + username + ", email=" + email + ", age=" + age
				+ ", sex=" + sex + ", telephone=" + telephone +  ", money=" + money
				+ ", ustate=" + ustate + ", adds=" + adds + "]";
	}

	

	
	

}
