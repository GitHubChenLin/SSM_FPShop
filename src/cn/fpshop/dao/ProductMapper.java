package cn.fpshop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.fpshop.pojo.Category;
import cn.fpshop.pojo.Product;

public interface ProductMapper {
	//获取最热商品
	List<Product> getHotProducts();
	
	//获取最新商品
	List<Product> getNewProducts();
	
	//获取类别
	List<Category> getCategoryList();

	//获得同类别商品数量
	Integer getCount(String cid);

	List<Product> getProductListByPage(String cid, int index);

	Product getProductInfo(String pid);

	void modifyCategory(String cid, String cname);

	void addCategory(String cname);

	void delCategory(String cid);

	List<Product> getAllproduct();

	List<Product> getProductByDetails(@Param("cid")String cid, @Param("price") String price, @Param("amount") String amount);

	void modifyProduct(Product product);


}
