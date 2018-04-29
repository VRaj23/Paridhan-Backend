package varadraj.unit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import varadraj.RetailShopController;

@RunWith(SpringJUnit4ClassRunner.class)
public class RetailShopControllerTest {
	
	private MockMvc mock;
	
	@InjectMocks
	RetailShopController controller;
	
	@Before
	public void setUp() throws Exception{
		mock = MockMvcBuilders.standaloneSetup(controller).build();
	}
	
	@Test
	public void test1() throws Exception{
		mock.perform(get("/").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.*", Matchers.hasSize(2)))
		.andExpect(jsonPath("$.status", Matchers.is(200)))
		.andExpect(jsonPath("$.message",Matchers.isA(String.class)));
	}

}
