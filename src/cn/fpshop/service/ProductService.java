package cn.fpshop.service;

import java.util.List;

import cn.fpshop.pojo.PageBean;
import cn.fpshop.pojo.Product;

public interface ProductService {

	PageBean getProductListByCid(String cid, int currentPage);

	Product getProductInfo(String pid);

	List<Product> getAllProduct();

	void modifyProduct(Product product);

}
