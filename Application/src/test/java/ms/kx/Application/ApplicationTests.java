package ms.kx.Application;

import ms.kx.Application.controller.ProductController;
import ms.kx.Application.entity.Product;
import ms.kx.Application.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ApplicationTests {
	@Autowired
	ProductService productService;
	@Test
	void contextLoads() {
	}

	@Test
	public void findAllProducts(){
		List<Product> list = productService.findAllProducts();
		if(list.isEmpty()){
			assert false;
		}
		assert true;
	}

}
