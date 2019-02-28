package cn.fpshop.service;

import java.util.List;

import cn.fpshop.pojo.Product;

public interface AdminService {

	void modifyCategory(String cid, String cname);

	void addCaregory(String cname);

	void delCategory(String cid);

	List<Product> getProductByDetails(String cid, String price, String amount);

}
