package cn.fpshop.service;

import java.util.List;

import cn.fpshop.pojo.Category;
import cn.fpshop.pojo.Product;

public interface IndexService {

	List<Product> getHotProducts();
	List<Product> getNewProducts();
	List<Category> getCategoryList();

}
