package varadraj.unit.product.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import varadraj.product.service.ProductTypeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ProductGetInfoTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	ProductTypeService typeService;
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@Test
	public void getProductType_shouldReturnAllProductType() throws Exception{
		
		Mockito.when(typeService.getAllTypes())
			.thenReturn(Collections.emptyList());
		
		mockMvc.perform(get("/product/type")
				.accept(MediaType.APPLICATION_JSON) )
				.andDo(print())
				.andExpect(jsonPath("$.status", Matchers.is(200)))
				.andReturn();
		
		Mockito.verify(typeService).getAllTypes();
	}

}
