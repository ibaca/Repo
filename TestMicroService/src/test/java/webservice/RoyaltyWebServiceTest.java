package webservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.royalty.Application;
import com.royalty.service.PaymentService;
import com.royalty.service.ViewingService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class RoyaltyWebServiceTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Mock
	private PaymentService paymentService;
	
	@Mock
	private ViewingService viewingService;
	

	private MockMvc mvc;

	@Before
	public void setup() {
		this.mvc = webAppContextSetup(context).build();
	}
	
	@Test
	public void testGetAllPayments() throws Exception {
		
		this.mvc.perform(get("/royaltymanager/payments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}

	@Test
	public void testPaymentFound() throws Exception {
		this.mvc.perform(get("/royaltymanager/payments/665115721c6f44e49be3bd3e26606026")
				 .contentType(MediaType.APPLICATION_JSON))
				 .andExpect(status().isOk());		
	}
	
	
	@Test
	public void testPaymentNotFound() throws Exception {
		this.mvc.perform(get("/royaltymanager/payments/123") 
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	
	@Test
	public void testCreateViewing() throws Exception {
		this.mvc.perform(post("/royaltymanager/viewing")
                .contentType(MediaType.APPLICATION_JSON)
                .param("episode", "6a1db5d6610a4c048d3df9a6268c68dc")
                .param("customer", "customer1"))
                .andExpect(status().isOk());
	}
	
	@Test
	public void testCreateViewingNoCustomer() throws Exception {
		this.mvc.perform(post("/royaltymanager/viewing")
                .contentType(MediaType.APPLICATION_JSON)
                .param("episode", "6a1db5d6610a4c048d3df9a6268c68dc"))
                .andExpect(status().isBadRequest());
	}
	
		
	@Test
	public void testCreateViewingNoEpisode() throws Exception {
		this.mvc.perform(post("/royaltymanager/viewing")
                .contentType(MediaType.APPLICATION_JSON)               
                .param("customer", "customer1"))
                .andExpect(status().isBadRequest());
	}
	
	
	@Test
	public void testReset() throws Exception {
		this.mvc.perform(post("/royaltymanager/reset").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
