package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.dao.ProductService;
import com.model2.mvc.service.user.UserService;


/*
 *	FileName :  UserServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		
		"classpath:config/context-aspect.xml",
		"classpath:config/context-common.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml"
		
		
})
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		
		
		product.setFileName("�ȳ�~");
		product.setProdName("������������");
		product.setProdDetail("�������ٺ���");
		product.setManuDate("1111112222");
		
		
		productService.addProduct(product);
		
		product = productService.getProduct(10001);

		//==> console Ȯ��
		//System.out.println(product);
		
		//==> API Ȯ��
		Assert.assertEquals("������", product.getProdName());
		Assert.assertEquals("������ ���ƿ�~", product.getProdDetail());
		Assert.assertEquals("20120514", product.getManuDate());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("AHlbAAAAvetFNwAA.jpg", product.getFileName());
		
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		//==> �ʿ��ϴٸ�...

		
		product = productService.getProduct(10001);

		//==> console Ȯ��
		System.out.println(product);
		
		//==> API Ȯ��
		Assert.assertEquals("������", product.getProdName());
		Assert.assertEquals("������ ���ƿ�~", product.getProdDetail());
		Assert.assertEquals("20120514", product.getManuDate());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("AHlbAAAAvetFNwAA.jpg", product.getFileName());
		

		Assert.assertNotNull(productService.getProduct(10003));
		Assert.assertNotNull(productService.getProduct(10002));
	}
	
	//@Test
	 public void testUpdateUser() throws Exception{
		 
		Product product = productService.getProduct(10002);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("����", product.getProdName());
		Assert.assertEquals("�´´�", product.getProdDetail());
		Assert.assertEquals("����", product.getFileName());
		

		product.setProdName("����1");
		product.setProdDetail("�´´�1");
		product.setFileName("����1");
		
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10002);
		Assert.assertNotNull(product);
		
		//==> console Ȯ��
		System.out.println(product);
			
		//==> API Ȯ��

	 }
//	 

	 //==>  �ּ��� Ǯ�� �����ϸ�....
	 @Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 }	 
