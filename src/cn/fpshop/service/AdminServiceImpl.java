package cn.fpshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fpshop.dao.ProductMapper;
import cn.fpshop.pojo.Product;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	private ProductMapper mapper;
	
	@Override
	public void modifyCategory(String cid, String cname) {
		mapper.modifyCategory(cid,cname);
	}

	@Override
	public void addCaregory(String cname) {
		mapper.addCategory(cname);
	}

	@Override
	public void delCategory(String cid) {
		mapper.delCategory(cid);
		
	}

	@Override
	public List<Product> getProductByDetails(String cid, String price, String amount) {
		return mapper.getProductByDetails(cid,price,amount);
		
	}

}
