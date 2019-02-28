package cn.fpshop.service;

import java.util.List;
import java.util.zip.Inflater;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fpshop.dao.ProductMapper;
import cn.fpshop.pojo.PageBean;
import cn.fpshop.pojo.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper mapper;
	
	@Override
	public PageBean getProductListByCid(String cid, int currentPage) {
		PageBean<Product> pagebean = new PageBean<Product>() ;
		Integer totalCount = mapper.getCount(cid);
		pagebean.setTotalCount(totalCount);
		
		pagebean.setCurrentPage(currentPage);
		
		int totalPage = (int) Math.ceil(1.0 * totalCount / 10);
		
		pagebean.setTotalPage(totalPage);
		int index = (currentPage-1)*10;
		List<Product> list= mapper.getProductListByPage(cid,index);
		pagebean.setList(list);
		return pagebean;
	}

	
	@Override
	public Product getProductInfo(String pid) {
		return mapper.getProductInfo(pid);
	}


	@Override
	public List<Product> getAllProduct() {
		return mapper.getAllproduct();
		
	}


	@Override
	public void modifyProduct(Product product) {
		mapper.modifyProduct(product);
	}

}
