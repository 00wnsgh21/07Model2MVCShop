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
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { 
		
		"classpath:config/context-aspect.xml",
		"classpath:config/context-common.xml",
		"classpath:config/context-mybatis.xml",
		"classpath:config/context-transaction.xml"
		
		
})
public class ProductServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		
		
		product.setFileName("안녕~");
		product.setProdName("난정말괜찮아");
		product.setProdDetail("울지마바보야");
		product.setManuDate("1111112222");
		
		
		productService.addProduct(product);
		
		product = productService.getProduct(10001);

		//==> console 확인
		//System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("자전거", product.getProdName());
		Assert.assertEquals("자전거 좋아요~", product.getProdDetail());
		Assert.assertEquals("20120514", product.getManuDate());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("AHlbAAAAvetFNwAA.jpg", product.getFileName());
		
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		//==> 필요하다면...

		
		product = productService.getProduct(10001);

		//==> console 확인
		System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("자전거", product.getProdName());
		Assert.assertEquals("자전거 좋아요~", product.getProdDetail());
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
		
		Assert.assertEquals("뭘봐", product.getProdName());
		Assert.assertEquals("맞는다", product.getProdDetail());
		Assert.assertEquals("ㅋㅋ", product.getFileName());
		

		product.setProdName("뭘봐1");
		product.setProdDetail("맞는다1");
		product.setFileName("ㅋㅋ1");
		
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10002);
		Assert.assertNotNull(product);
		
		//==> console 확인
		System.out.println(product);
			
		//==> API 확인

	 }
//	 

	 //==>  주석을 풀고 실행하면....
	 @Test
	 public void testGetProductListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console 확인
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
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 }	 
