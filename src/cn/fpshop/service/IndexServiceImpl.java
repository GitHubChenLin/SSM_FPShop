package cn.fpshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fpshop.dao.ProductMapper;
import cn.fpshop.pojo.Category;
import cn.fpshop.pojo.Product;
@Service
public class IndexServiceImpl implements IndexService {
	@Autowired
	private ProductMapper mapper;
	
	@Override
	public List<Product> getHotProducts() {
		return mapper.getHotProducts();
	}

	@Override
	public List<Product> getNewProducts() {
		return mapper.getNewProducts();
	}

	@Override
	public List<Category> getCategoryList() {
		return mapper.getCategoryList();
	}

}
